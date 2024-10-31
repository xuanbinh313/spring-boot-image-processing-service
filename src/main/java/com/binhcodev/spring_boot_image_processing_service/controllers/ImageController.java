package com.binhcodev.spring_boot_image_processing_service.controllers;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.binhcodev.spring_boot_image_processing_service.configs.RabbitMQConfig;
import com.binhcodev.spring_boot_image_processing_service.dtos.request.TransformationRequest;
import com.binhcodev.spring_boot_image_processing_service.entities.Image;
import com.binhcodev.spring_boot_image_processing_service.entities.TransformationMessage;
import com.binhcodev.spring_boot_image_processing_service.services.ImageService;
import com.binhcodev.spring_boot_image_processing_service.services.UploadService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class ImageController {
    private ImageService imageService;
    private UploadService uploadService;
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getMethodName(@PathVariable Long id) throws IOException {
        Image image = imageService.getImageById(id);
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageData = uploadService.getFile(image.getName());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageData);
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) {
        Image image = imageService.uploadImage(file);
        return ResponseEntity.ok(image);
    }

    @PostMapping("/{id}/transform")
    public ResponseEntity<String> transformImage(@PathVariable Long id,
            @RequestBody TransformationRequest transformationRequest) {
        TransformationMessage message = TransformationMessage.builder().imageId(id).transformationRequest(transformationRequest).build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
        return ResponseEntity.ok("Transformation request submitted");
    }
}