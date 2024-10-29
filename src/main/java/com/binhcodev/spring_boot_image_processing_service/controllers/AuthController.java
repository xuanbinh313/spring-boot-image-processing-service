package com.binhcodev.spring_boot_image_processing_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "Login";
    }
    
}
