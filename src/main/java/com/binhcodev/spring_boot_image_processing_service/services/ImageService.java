package com.binhcodev.spring_boot_image_processing_service.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.binhcodev.spring_boot_image_processing_service.entities.Image;
import com.binhcodev.spring_boot_image_processing_service.repositories.ImageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageService {
    private UploadService uploadService;
    private ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) {
        String filePath = uploadService.saveFile(file);
        Image image = Image.builder().name(filePath).build();
        return imageRepository.save(image);
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
