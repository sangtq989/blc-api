package com.project.blockchainapi.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class UserProfileSummaryResponse {
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String yearOfExp;
    private Integer numberOfEmployee;
    private String link;
    private String description;
    private String companyName;
    private List<String> skill;
    private String location;
    private String phone;
    private String email;
    private String gender;
    private String taxNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String walletAddress;
    private String role;

}
