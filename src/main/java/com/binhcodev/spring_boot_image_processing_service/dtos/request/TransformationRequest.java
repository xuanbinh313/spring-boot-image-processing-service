package com.binhcodev.spring_boot_image_processing_service.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransformationRequest {
    private Resize resize;
    private Crop crop;
    private Integer rotate;
    private Boolean grayscale;
    private Boolean sepia;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Resize {
        private int width;
        private int height;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Crop {
        private int x;
        private int y;
        private int width;
        private int height;
    }
}
