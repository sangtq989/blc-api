package com.project.blockchainapi.config.security;

import com.project.blockchainapi.entity.UserInfo;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static UserInfo currentLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return (UserInfo) authentication.getPrincipal();
        }
        throw new AccountExpiredException("User not logged in");
    }

}
