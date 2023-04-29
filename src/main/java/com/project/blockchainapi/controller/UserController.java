package com.project.blockchainapi.controller;

import com.project.blockchainapi.config.security.SecurityUtils;
import com.project.blockchainapi.constant.Constant;
import com.project.blockchainapi.entity.Metadata;
import com.project.blockchainapi.entity.UserInfo;
import com.project.blockchainapi.repo.MetadataRepository;
import com.project.blockchainapi.request.form.CertificateFormRequest;
import com.project.blockchainapi.request.form.ExperienceFormRequest;
import com.project.blockchainapi.request.form.SpecialityFormRequest;
import com.project.blockchainapi.request.user.UserProfileUpdateRequest;
import com.project.blockchainapi.response.MessageResponse;
import com.project.blockchainapi.response.user.UserDetailResponse;
import com.project.blockchainapi.service.UserInfoService;
import com.project.blockchainapi.util.mapper.MetadataMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Api
@RestController
@RequestMapping("/api/me")
public class UserController {

    private final MetadataMapper metadataMapper;
    private final MetadataRepository metadataRepository;
    private final UserInfoService userInfoService;

    @GetMapping("/profile")
    public ResponseEntity<UserDetailResponse> getUserProfile() {
        return ResponseEntity.ok(UserDetailResponse.builder().build());
    }

    @PostMapping("/profile")
    public ResponseEntity<MessageResponse> updateProfile(@RequestBody UserProfileUpdateRequest request) {

        UserInfo userInfo = SecurityUtils.currentLogin();
        userInfo.setLastName(request.getLastName());
        userInfo.setLastName(request.getLastName());
        userInfo.setPhoneNumber(request.getPhoneNumber());
        userInfo.setDateOfBirth(request.getDateOfBirth());
        userInfo.setGender(request.getGender());

        userInfoService.saveUser(userInfo);

        return ResponseEntity.ok(MessageResponse.builder()
                .internalMessage(Constant.SUCCESS)
                .internalMessage("Profile updated successfully")
                .build());
    }

    @PostMapping("profile/specialty")
    public ResponseEntity<MessageResponse> addSpecialty(@RequestBody SpecialityFormRequest request) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        List<Metadata> entityList = metadataMapper.mapToEntity(request);
        metadataRepository.saveAll(entityList);
        return ResponseEntity.ok(
                MessageResponse.builder()
                        .internalStatus(Constant.SUCCESS)
                        .internalMessage("Specialty added successfully")
                        .build()
        );
    }

    @PostMapping("profile/experience")
    public ResponseEntity<MessageResponse> addExperience(@RequestBody ExperienceFormRequest request) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        List<Metadata> entityList = metadataMapper.mapToEntity(request);
        metadataRepository.saveAll(entityList);
        return ResponseEntity.ok(MessageResponse.builder()
                .internalStatus(Constant.SUCCESS)
                .internalMessage("Experience added successfully")
                .build());
    }

    @PostMapping("profile/certificate")
    public ResponseEntity<MessageResponse> addCertificate(@RequestBody CertificateFormRequest request) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        List<Metadata> entityList = metadataMapper.mapToEntity(request);
        metadataRepository.saveAll(entityList);
        return ResponseEntity.ok(MessageResponse.builder()
                .internalStatus(Constant.SUCCESS)
                .internalMessage("Certificate added successfully")
                .build());

    }
}