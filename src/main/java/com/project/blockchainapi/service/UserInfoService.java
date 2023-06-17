package com.project.blockchainapi.service;

import com.project.blockchainapi.entity.UserInfo;
import com.project.blockchainapi.request.user.UserRegisterRequest;
import com.project.blockchainapi.response.user.UserProfileSummaryResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

public interface UserInfoService extends UserDetailsService {
    boolean isUserExist(String email);

    void saveUser(UserInfo user);
    void sendMail(String email) throws MessagingException;

    void enableUser(String email);

    Map<String, List<Map<String, Object>>> getUserProfileMetadata(String userEmail);

    UserProfileSummaryResponse userProfileSummary(String email);

    void signUp(UserRegisterRequest signUpRequest);
}
