package com.project.blockchainapi.response.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailResponse {
    private String email;
    private String jwt;
}
