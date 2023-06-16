package com.project.blockchainapi.response.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileSummaryResponse {
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String yearOfExp;
    private String companyName;
    private String skill;
    private String location;
}
