package com.project.blockchainapi.service.impl;

import com.project.blockchainapi.repo.UserInfoRepository;
import com.project.blockchainapi.request.expert.ExpertDashboardSearchRequest;
import com.project.blockchainapi.response.expert.ExpertMetadataResponse;
import com.project.blockchainapi.service.ExpertService;
import com.project.blockchainapi.util.mapper.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpertServiceImpl implements ExpertService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public List<ExpertMetadataResponse> expertDashBoard(ExpertDashboardSearchRequest request) {

        var experts = userInfoRepository.getUsersForExpertDashboard(request.getKeyword());
        return experts.stream().map(item ->
                        ExpertMetadataResponse.builder()
                                .email(item.getEmail())
                                .fullName(item.getFirstName() + " " + item.getLastName())
                                .jobTitle(item.getPositionName())
                                .blockAddress(item.getBlockChainAddress())
                                .tags(CommonUtils.convertStringToList(item.getSpecialities()))
                        .build())
                .toList();

    }
}
