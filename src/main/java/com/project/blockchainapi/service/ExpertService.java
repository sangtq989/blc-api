package com.project.blockchainapi.service;

import com.project.blockchainapi.request.expert.ExpertDashboardSearchRequest;
import com.project.blockchainapi.response.expert.ExpertMetadataResponse;

import java.util.List;

public interface ExpertService {

    List<ExpertMetadataResponse> expertDashBoard(ExpertDashboardSearchRequest request);
}
