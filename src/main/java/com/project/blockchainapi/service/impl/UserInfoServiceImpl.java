package com.project.blockchainapi.service.impl;

import com.project.blockchainapi.config.security.JwtTokenProvider;
import com.project.blockchainapi.entity.UserInfo;
import com.project.blockchainapi.repo.UserInfoRepository;
import com.project.blockchainapi.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final JwtTokenProvider tokenProvider;
    private final JavaMailSender mailSender;

    @Value("${blockchain-app.url}")
    public String url;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  userInfoRepository.findUserInfoByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
    }

    @Override
    public boolean isUserExist(String email) {
        return userInfoRepository.existsByEmail(email);
    }

    @Override
    public void saveUser(UserInfo userInfo){
        userInfoRepository.save(userInfo);
    }

    @Override
    public void sendMail(String email) {
        String token = tokenProvider.generateJwtToken(email);

        String subject = "Email Verification";
        String body = "Please verify your email by clicking the following link: " + url +"/api/auth/verify?token="+ token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    public void enableUser(String email) {
       UserInfo userInfo = userInfoRepository.findUserInfoByEmail(email).orElseThrow();
       userInfo.setEnable(true);
       userInfoRepository.save(userInfo);
    }
}
