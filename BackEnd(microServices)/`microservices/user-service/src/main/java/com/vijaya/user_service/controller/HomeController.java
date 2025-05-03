package com.vijaya.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String homeControllerHandler(){
        return "Welcome to the home page!";
    }
}