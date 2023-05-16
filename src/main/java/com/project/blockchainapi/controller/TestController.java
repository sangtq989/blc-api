package com.project.blockchainapi.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@Api
@RestController
@RequestMapping("/ping")
public class TestController {
    @RequestMapping(value = "", method = RequestMethod.OPTIONS)
    public String ping() {
        return "pong";
    }
}
