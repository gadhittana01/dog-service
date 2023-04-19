package com.example.dogservice.config;

import java.time.Duration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    // This Bean is created using singleton scope (only created once)
    @Bean(name = "restTemplateAll")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RestTemplate restTemplateAll(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder
           .setConnectTimeout(Duration.ofMillis(5000))
           .setReadTimeout(Duration.ofMillis(5000))
           .build();
    }

    // This Bean is created using singleton scope (only created once)
    @Bean(name = "restTemplateBreed")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) 
    public RestTemplate restTemplateBreed(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder
           .setConnectTimeout(Duration.ofMillis(2000))
           .setReadTimeout(Duration.ofMillis(2000))
           .build();
    }
}
