package com.example.vijaya.salon_service.controller;

import com.example.vijaya.salon_service.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String homeControllerHandler(){
        return "Welcome to the Salon page!";
    }
}