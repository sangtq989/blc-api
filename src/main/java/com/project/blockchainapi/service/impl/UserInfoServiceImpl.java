package com.project.blockchainapi.service.impl;

import com.project.blockchainapi.config.security.JwtTokenProvider;
import com.project.blockchainapi.constant.Constant;
import com.project.blockchainapi.constant.FormType;
import com.project.blockchainapi.entity.Metadata;
import com.project.blockchainapi.entity.UserInfo;
import com.project.blockchainapi.exception.ClientRequestException;
import com.project.blockchainapi.exception.InternalServerException;
import com.project.blockchainapi.repo.MetadataRepository;
import com.project.blockchainapi.repo.UserInfoRepository;
import com.project.blockchainapi.repo.projection.MetadataProjection;
import com.project.blockchainapi.request.user.UserRegisterRequest;
import com.project.blockchainapi.response.user.UserProfileSummaryResponse;
import com.project.blockchainapi.service.UserInfoService;
import com.project.blockchainapi.util.mapper.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final JwtTokenProvider tokenProvider;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final MetadataRepository metadataRepository;

    @Value("${blockchain-app.url}")
    public String url;
    @Value("${blockchain-app.front-end}")
    public String frontEndUrl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userInfoRepository.findUserInfoByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
    }

    @Override
    public boolean isUserExist(String email) {
        return userInfoRepository.existsByEmail(email);
    }

    @Override
    public void saveUser(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

    @Override
    public void sendMail(String email) throws MessagingException {
        String token = tokenProvider.generateJwtToken(email);

        Context context = new Context();

        context.setVariable("return_page", url + "/api/auth/verify?token=" + token);
        context.setVariable("front_end", frontEndUrl);

        String content = templateEngine.process(Constant.VERIFY_MAIL_TEMPLATE, context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String subject = "Email Verification";
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(mimeMessage);
    }

    @Override
    public void enableUser(String email) {
        UserInfo userInfo = userInfoRepository.findUserInfoByEmail(email).orElseThrow();
        userInfo.setEnable(true);
        userInfoRepository.save(userInfo);
    }

    @Override
    public Map<String, List<Map<String, Object>>> getUserProfileMetadata(String userEmail) {
        var userForms = metadataRepository.getFormKeyByUserId(userEmail).orElse(new ArrayList<>());
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        for (String formKey : userForms) {
            var formObjectIds = metadataRepository.getMetadataByUserIdAndFormKey(userEmail, FormType.valueOf(formKey)).orElse(new ArrayList<>());
            List<Map<String, Object>> listObjectsOfForm = new ArrayList<>();
            for (String formObjectId : formObjectIds) {
                var resultItem = metadataRepository.getMetadataByObjectsId(userEmail, UUID.fromString(formObjectId))
                        .orElse(new ArrayList<>())
                        .stream()
                        .collect(Collectors.toMap(MetadataProjection::getFormFieldKey, metadata ->
                                "List".equals(metadata.getFieldDataType())
                                        ? CommonUtils.convertStringToList(metadata.getFieldValue())
                                        : metadata.getFieldValue()));
                listObjectsOfForm.add(resultItem);
            }
            result.put(formKey, listObjectsOfForm);
        }

        return result;
    }

    @Override
    public UserProfileSummaryResponse userProfileSummary(String email) {
        UserInfo user = userInfoRepository.findUserInfoByEmail(email)
                .orElseThrow(() -> new ClientRequestException("User " + email + " not found"));


        UserProfileSummaryResponse response = new UserProfileSummaryResponse();

        if ("USER".equals(user.getRole())) {
            List<Metadata> latestJob = metadataRepository.getMetadataForSummaryProfile(user.getEmail()).orElse(new ArrayList<>());
            List<Metadata> latestSpecialty = metadataRepository.getMetadataForSummaryProfileSpecialty(user.getId()).orElse(new ArrayList<>());
            latestJob.addAll(latestSpecialty);
            var latestJobMap = latestJob.stream()
                    .collect(Collectors.toMap(Metadata::getFormFieldKey, Metadata::getFieldValue));

            response.setWalletAddress(user.getBlockChainAddress());
            response.setRole(user.getRole());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setGender(String.valueOf(user.getGender()));
            response.setDateOfBirth(user.getDateOfBirth());
            response.setPhone(user.getPhoneNumber());
            response.setDescription(user.getDescription());

            response.setEmail(user.getEmail());
            response.setJobTitle(latestJobMap.get("positionName"));
            response.setCompanyName(latestJobMap.get("companyName"));
            response.setLocation(latestJobMap.get("companyAddress"));
            response.setYearOfExp(latestJobMap.get("yearOfExp"));
            response.setSkill(CommonUtils.convertStringToList(latestJobMap.get("skills")));


//            response.setGender(String.valueOf(user.getGender()));
//            response.setPhone(user.getPhoneNumber());
//            response.setLocation(user.getAddress());
//            response.setDescription(user.getDescription());

        } else {
            response.setEmail(user.getEmail());
            response.setWalletAddress(user.getBlockChainAddress());
            response.setRole(user.getRole());
            response.setCompanyName(user.getCompanyName());
            response.setJobTitle(user.getJobTitle());
            response.setTaxNumber(user.getTaxNumber());
            response.setNumberOfEmployee(user.getNumberOfEmployee());
            response.setPhone(user.getPhoneNumber());
            response.setLocation(user.getAddress());
            response.setLink(user.getLink());
            response.setDescription(user.getDescription());
        }

        return response;
    }

    @Override
    public void signUp(UserRegisterRequest signUpRequest) {
        UserInfo user = userInfoRepository.findUserInfoByEmail(signUpRequest.getEmail()).orElse(new UserInfo());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(signUpRequest.getRole());
        this.saveUser(user);
        try {
            this.sendMail(signUpRequest.getEmail());
        } catch (MessagingException e) {
            throw new InternalServerException("Server cannot sending email, please contact admin", e);
        }
    }
}
