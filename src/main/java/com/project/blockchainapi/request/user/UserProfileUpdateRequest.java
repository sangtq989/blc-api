package com.project.blockchainapi.request.user;

import com.project.blockchainapi.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileUpdateRequest {
    private String blockChainAddress;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;
    private String description;
}
