package com.project.blockchainapi.controller;

import com.project.blockchainapi.config.security.JwtTokenProvider;
import com.project.blockchainapi.constant.Constant;
import com.project.blockchainapi.entity.UserInfo;
import com.project.blockchainapi.request.user.UserLoginRequest;
import com.project.blockchainapi.request.user.UserRegisterRequest;
import com.project.blockchainapi.response.MessageResponse;
import com.project.blockchainapi.response.user.UserDetailResponse;
import com.project.blockchainapi.service.UserInfoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Api
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final UserInfoService userInfoService;

    @PostMapping("/login")
    public ResponseEntity<MessageResponse> authenticateUser(@Valid @RequestBody UserLoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateJwtToken(authentication);

        UserInfo userDetails = (UserInfo) authentication.getPrincipal();

        return ResponseEntity.ok(
                MessageResponse.builder()
                        .internalStatus(Constant.SUCCESS)
                        .internalMessage("User login success")
                        .data(UserDetailResponse.builder()
                                .jwt(jwt)
                                .email(userDetails.getEmail())
                                .role(userDetails.getRole())
                                .walletAddress(userDetails.getBlockChainAddress())
                                .build())
                        .build()
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody UserRegisterRequest signUpRequest) throws MessagingException {

        if (userInfoService.isUserExist(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(MessageResponse.builder()
                            .internalStatus(Constant.EXISTED)
                            .internalMessage("Error: Email is already taken!")
                            .build());
        }

        // Create new user's account
        signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
        userInfoService.signUp(signUpRequest);

        return ResponseEntity.ok(
                MessageResponse.builder()
                        .internalStatus(Constant.SUCCESS)
                        .internalMessage("User signup successful, please verify in email")
                        .build());
    }

    @GetMapping("/verify")
    public String registerUser(@RequestParam("token") String token) {
        if(jwtTokenProvider.validateJwtToken(token)){
            userInfoService.enableUser(jwtTokenProvider.getUserNameFromJwtToken(token));
            return "Account verify success, you can close this tab";
        }

        return "Account verify failed";
    }
}
