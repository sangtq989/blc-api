package com.project.blockchainapi.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {
    private String blockChainAddress;
    private String firstName;
    private String lastName;

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
