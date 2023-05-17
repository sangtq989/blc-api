package com.project.blockchainapi.service.impl;

import com.project.blockchainapi.config.security.JwtTokenProvider;
import com.project.blockchainapi.constant.Constant;
import com.project.blockchainapi.entity.Metadata;
import com.project.blockchainapi.entity.UserInfo;
import com.project.blockchainapi.exception.InternalServerException;
import com.project.blockchainapi.repo.MetadataRepository;
import com.project.blockchainapi.repo.UserInfoRepository;
import com.project.blockchainapi.request.user.UserRegisterRequest;
import com.project.blockchainapi.service.UserInfoService;
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
import java.util.List;
import java.util.Map;
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
    public Map getUserProfileMetadata(String userEmail) {
        List<Metadata> metadata = metadataRepository.getMetadataByUserId(userEmail);

        return metadata.stream()
                .collect(
                        Collectors.groupingBy(Metadata::getFormKey,
                                Collectors.groupingBy(item -> item.getObjectId().toString(),
                                        Collectors.toMap(Metadata::getFormFieldKey, Metadata::getFieldValue)
                                )
                        )
                );
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
