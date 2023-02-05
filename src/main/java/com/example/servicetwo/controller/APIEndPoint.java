package com.example.servicetwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class APIEndPoint {
    @GetMapping("testS2")
    public String testEndpoint(){
        return "from service two";
    }
}
