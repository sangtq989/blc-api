package com.project.blockchainapi.service.impl;

import com.project.blockchainapi.entity.UserInfo;
import com.project.blockchainapi.repo.UserInfoRepository;
import com.project.blockchainapi.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

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
}
