package com.project.blockchainapi.request.expert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpertDashboardSearchRequest {
    private String keyword = "";
    private List<String> speciality;
    private List<String> certs;
    private List<Integer> expYears;
    private List<String> location;
}
