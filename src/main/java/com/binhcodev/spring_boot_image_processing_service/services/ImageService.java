package com.binhcodev.spring_boot_image_processing_service.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.binhcodev.spring_boot_image_processing_service.dtos.request.TransformationRequest;
import com.binhcodev.spring_boot_image_processing_service.entities.Image;
import com.binhcodev.spring_boot_image_processing_service.repositories.ImageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageService {
    private UploadService uploadService;
    private ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) {
        String fileName = uploadService.saveFile(file);
        Image image = Image.builder().name(fileName).build();
        return imageRepository.save(image);
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
    }

    public Image transformImage(Long imageId, TransformationRequest transformationRequest) {
        Image image = getImageById(imageId);
        Image transformImage = uploadService.transformImage(image.getName(), transformationRequest);
        return transformImage;
    }
}
