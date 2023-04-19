package com.example.dogservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateBreedConfig implements IRestTemplate{
    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateBreedConfig(@Qualifier("restTemplateBreed") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity HTTPRequest(String URL, Class responseType){
        return restTemplate.getForEntity(URL, responseType);
    }

    
}
