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
import com.project.blockchainapi.request.user.UserWalletAddressUpdateRequest;
import com.project.blockchainapi.response.MessageResponse;
import com.project.blockchainapi.response.user.UserProfileSummaryResponse;
import com.project.blockchainapi.service.FileUploadService;
import com.project.blockchainapi.service.UserInfoService;
import com.project.blockchainapi.util.mapper.CommonUtils;
import com.project.blockchainapi.util.mapper.MetadataMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Api
@RestController
@RequestMapping("/api/me")
public class UserController {

    private final MetadataMapper metadataMapper;
    private final MetadataRepository metadataRepository;
    private final UserInfoService userInfoService;
    private final FileUploadService fileUploadService;

    @GetMapping("/profile")
    public ResponseEntity<MessageResponse> getUserProfile() {
        UserInfo userInfo = SecurityUtils.currentLogin();
        var metadata = "USER".equals(userInfo.getRole()) ? userInfoService.getUserProfileMetadata(userInfo.getEmail()) : new HashMap<>();
        UserProfileSummaryResponse profileDetailResponse = userInfoService.userProfileSummary(userInfo.getEmail());
        return ResponseEntity.ok(MessageResponse.builder()
                .internalStatus(Constant.SUCCESS)
                .internalMessage("Profile retrieve successfully")
                .data(Map.of(
                        "userInfo", profileDetailResponse,
                        "metadata", metadata))
                .build());
    }


    @PutMapping("/profile/wallet-address")
    public ResponseEntity<MessageResponse> update(@RequestBody @Valid UserWalletAddressUpdateRequest request) {
        UserInfo userInfo = SecurityUtils.currentLogin();
        userInfo.setBlockChainAddress(request.getBlockChainAddress());
        userInfoService.saveUser(userInfo);

        return ResponseEntity.ok(MessageResponse.builder()
                .internalStatus(Constant.SUCCESS)
                .internalMessage("Profile updated successfully")
                .build());
    }

    @PostMapping("/profile")
    public ResponseEntity<MessageResponse> updateProfile(@RequestBody @Valid UserProfileUpdateRequest request) throws Exception {

        UserInfo user = SecurityUtils.currentLogin();
        if ("USER".equals(user.getRole())) {
            user.setFirstName(CommonUtils.nullOrDefault(request.getFirstName(), user.getFirstName()));
            user.setLastName(CommonUtils.nullOrDefault(request.getLastName(), user.getLastName()));
            user.setGender(CommonUtils.nullOrDefault(request.getGender(), user.getGender()));
            user.setDateOfBirth(CommonUtils.nullOrDefault(request.getDateOfBirth(), user.getDateOfBirth()));
            user.setPhoneNumber(CommonUtils.nullOrDefault(request.getPhoneNumber(), user.getPhoneNumber()));
            user.setAddress(CommonUtils.nullOrDefault(request.getAddress(), user.getAddress()));
            user.setDescription(CommonUtils.nullOrDefault(request.getDescription(), user.getDescription()));
        } else {
            user.setCompanyName(CommonUtils.nullOrDefault(request.getCompanyName(), user.getCompanyName()));
            user.setTaxNumber(CommonUtils.nullOrDefault(request.getTaxNumber(), user.getTaxNumber()));
            user.setJobTitle(CommonUtils.nullOrDefault(request.getJobTitle(), user.getJobTitle()));
            user.setNumberOfEmployee(CommonUtils.nullOrDefault(request.getNumberOfEmployee(), user.getNumberOfEmployee()));
            user.setPhoneNumber(CommonUtils.nullOrDefault(request.getPhoneNumber(), user.getPhoneNumber()));
            user.setAddress(CommonUtils.nullOrDefault(request.getAddress(), user.getAddress()));
            user.setLink(CommonUtils.nullOrDefault(request.getLink(), user.getLink()));
            user.setDescription(CommonUtils.nullOrDefault(request.getDescription(), user.getDescription()));
        }
//        String fileLocation = fileUploadService.uploadFile(request.getAvatar(), SecurityUtils.currentLogin().getEmail());
//        userInfo.setAvatar(fileLocation);
        userInfoService.saveUser(user);

        return ResponseEntity.ok(MessageResponse.builder()
                .internalStatus(Constant.SUCCESS)
                .internalMessage("Profile updated successfully")
                .data(request)
                .build());
    }


    @PostMapping("/test")
    public ResponseEntity<MessageResponse> updateProfile() throws Exception {
        return ResponseEntity.ok(MessageResponse.builder()
                .internalStatus(Constant.SUCCESS)
                .internalMessage("Profile updated successfully")
                .data(new UserProfileUpdateRequest())
                .build());
    }

    @PostMapping("profile/specialty")
    public ResponseEntity<MessageResponse> addSpecialty(@RequestBody @Valid SpecialityFormRequest request) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
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
    public ResponseEntity<MessageResponse> addExperience(@RequestBody @Valid ExperienceFormRequest request) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        List<Metadata> entityList = metadataMapper.mapToEntity(request);
        metadataRepository.saveAll(entityList);
        return ResponseEntity.ok(MessageResponse.builder()
                .internalStatus(Constant.SUCCESS)
                .internalMessage("Experience added successfully")
                .build());
    }

    @PostMapping("profile/certificate")
    public ResponseEntity<MessageResponse> addCertificate(@RequestBody @Valid CertificateFormRequest request) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        List<Metadata> entityList = metadataMapper.mapToEntity(request);
        metadataRepository.saveAll(entityList);
        return ResponseEntity.ok(MessageResponse.builder()
                .internalStatus(Constant.SUCCESS)
                .internalMessage("Certificate added successfully")
                .build());

    }
}
