package com.project.blockchainapi.service;

import com.project.blockchainapi.entity.UserInfo;
import com.project.blockchainapi.request.user.UserRegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;
import java.util.Map;

public interface UserInfoService extends UserDetailsService {
    boolean isUserExist(String email);


    void saveUser(UserInfo user);
    void sendMail(String email) throws MessagingException;

    void enableUser(String email);

    Map getUserProfileMetadata(String userEmail);

    void signUp(UserRegisterRequest signUpRequest);
}
