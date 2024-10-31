package com.binhcodev.spring_boot_image_processing_service.entities;

import com.binhcodev.spring_boot_image_processing_service.dtos.request.TransformationRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransformationMessage implements Serializable{
    private Long imageId;
    private TransformationRequest transformationRequest;
}
