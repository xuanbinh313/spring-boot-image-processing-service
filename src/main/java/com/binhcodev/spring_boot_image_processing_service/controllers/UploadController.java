package com.binhcodev.spring_boot_image_processing_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @GetMapping
    public String upload() {
        return "Upload";
    }
}
