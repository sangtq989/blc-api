package com.project.blockchainapi.request.user;

import com.project.blockchainapi.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileUpdateRequest {
    //    private MultipartFile avatar;
    private String firstName;
    private String lastName;
    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String companyName;
    private Integer numberOfEmployee;
    private String taxNumber;
    private String link;

    private String phoneNumber;
    private String address;
    private String jobTitle;
    private String description;

}
