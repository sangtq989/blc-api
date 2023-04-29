package com.project.blockchainapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


public class BeanFactory {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
