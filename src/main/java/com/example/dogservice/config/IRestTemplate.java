package com.example.dogservice.config;

import org.springframework.http.ResponseEntity;

public interface IRestTemplate {
    ResponseEntity HTTPRequest(String URL, Class responseType);
}
