package com.project.blockchainapi.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserProfileSummaryResponse {
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String yearOfExp;
    private String companyName;
    private List<String> skill;
    private String location;
    private String phone;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String walletAddress;
}
