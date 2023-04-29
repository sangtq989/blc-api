package com.project.blockchainapi.controller;


import com.project.blockchainapi.constant.Constant;
import com.project.blockchainapi.request.expert.ExpertDashboardSearchRequest;
import com.project.blockchainapi.response.MessageResponse;
import com.project.blockchainapi.response.expert.ExpertMetadataResponse;
import com.project.blockchainapi.service.ExpertService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Api
@RestController
@RequestMapping("/api/experts")
public class ExpertMetadataController {

    private final ExpertService expertService;

    @GetMapping("")
    public ResponseEntity<MessageResponse> getUserMetadataByAddress(@RequestParam(required = false) List<String> speciality,
                                                                                 @RequestParam(required = false) List<String> certs,
                                                                                 @RequestParam(required = false) List<Integer> expYears,
                                                                                 @RequestParam(required = false) List<String> location) {
        return ResponseEntity.ok(
                MessageResponse.builder()
                        .internalStatus(Constant.SUCCESS)
                        .internalMessage("Get experts success")
                        .data(expertService.expertDashBoard(
                                        new ExpertDashboardSearchRequest(speciality, certs, expYears, location)))
                        .build()
                );
    }

    @GetMapping("/{address}")
    public ResponseEntity<ExpertMetadataResponse> getExpertMetadataByAddress(@PathVariable String address) {
        return ResponseEntity.ok(ExpertMetadataResponse.builder().build());
    }


}
