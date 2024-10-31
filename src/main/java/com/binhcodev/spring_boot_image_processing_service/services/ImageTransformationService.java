package com.binhcodev.spring_boot_image_processing_service.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.binhcodev.spring_boot_image_processing_service.configs.RabbitMQConfig;
import com.binhcodev.spring_boot_image_processing_service.dtos.request.TransformationRequest;
import com.binhcodev.spring_boot_image_processing_service.entities.Image;
import com.binhcodev.spring_boot_image_processing_service.entities.TransformationMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageTransformationService {
    private ImageService imageService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processTransformation(TransformationMessage message) {
        try {
            // Transformation logic here
            Long imageId = message.getImageId();
            TransformationRequest request = message.getTransformationRequest();
            // Perform transformations on the image with ID `imageId` as per `request`

            System.out.println("Processing transformation for image ID: " + imageId);

            // Add your image transformation logic here
            // E.g., resize, crop, rotate based on the request
            Image image = imageService.transformImage(imageId, request);

            System.out.println("Transformation complete for image ID: " + imageId);
        } catch (Exception e) {
            // Log the error and let RabbitMQ retry
            throw new RuntimeException("Error processing transformation", e);
        }
    }
}
