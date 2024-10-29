package com.binhcodev.spring_boot_image_processing_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binhcodev.spring_boot_image_processing_service.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
     
}
