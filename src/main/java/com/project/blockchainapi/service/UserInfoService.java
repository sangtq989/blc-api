package com.project.blockchainapi.service;

import com.project.blockchainapi.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInfoService extends UserDetailsService {
    boolean isUserExist(String email);
    void saveUser(UserInfo user);
    void sendMail(String email);

    void enableUser(String email);
}
