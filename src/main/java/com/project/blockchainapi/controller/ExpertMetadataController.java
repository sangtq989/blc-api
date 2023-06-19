package com.project.blockchainapi.controller;


import com.project.blockchainapi.constant.Constant;
import com.project.blockchainapi.request.expert.ExpertDashboardSearchRequest;
import com.project.blockchainapi.response.MessageResponse;
import com.project.blockchainapi.response.user.UserProfileSummaryResponse;
import com.project.blockchainapi.service.ExpertService;
import com.project.blockchainapi.service.UserInfoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Api
@RestController
@RequestMapping("/api/experts")
public class ExpertMetadataController {

    private final ExpertService expertService;
    private final UserInfoService userInfoService;

    @GetMapping("")
    public ResponseEntity<MessageResponse> getUserMetadataByAddress(@RequestParam(required = false) List<String> speciality,
                                                                    @RequestParam(required = false) List<String> certs,
                                                                    @RequestParam(required = false) List<Integer> expYears,
                                                                    @RequestParam(required = false) List<String> location,
                                                                    @RequestParam(required = false) String keyword
    ) {
        return ResponseEntity.ok(
                MessageResponse.builder()
                        .internalStatus(Constant.SUCCESS)
                        .internalMessage("Get experts success")
                        .data(expertService.expertDashBoard(
                                new ExpertDashboardSearchRequest(keyword, speciality, certs, expYears, location)))
                        .build()
        );
    }

    @GetMapping("/{email}")
    public ResponseEntity<MessageResponse> getUserProfile(@PathVariable String email) {
        var metadata = userInfoService.getUserProfileMetadata(email);
        UserProfileSummaryResponse profileDetailResponse = userInfoService.userProfileSummary(email);
        return ResponseEntity.ok(MessageResponse.builder()
                .internalStatus(Constant.SUCCESS)
                .internalMessage("Profile retrieve successfully")
                .data(Map.of(
                        "userInfo", profileDetailResponse,
                        "metadata", metadata))
                .build());
    }


}
