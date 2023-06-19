package com.project.blockchainapi.response.expert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertMetadataResponse {

    private String email;
    private String fullName;
    private String blockAddress;
    private String jobTitle;
    private List<String> tags;
}
