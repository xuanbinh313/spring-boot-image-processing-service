package com.binhcodev.spring_boot_image_processing_service.services;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.binhcodev.spring_boot_image_processing_service.dtos.request.TransformationRequest;
import com.binhcodev.spring_boot_image_processing_service.entities.Image;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UploadService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveFile(MultipartFile file) {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = uploadPath.resolve(fileName);
        try (InputStream is = file.getInputStream()) {
            BufferedImage originalImage = ImageIO.read(is);
            ImageIO.write(originalImage, "png", filePath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public byte[] getFile(String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        return Files.readAllBytes(filePath);
    }

    public Image transformImage(String imageName, TransformationRequest transformationRequest) {
        Path filePath = Paths.get(uploadDir).resolve(imageName);
        try (InputStream is = Files.newInputStream(filePath)) {
            BufferedImage transformedImage = ImageIO.read(is);
            Image image = new Image();
            String resizeName = transformationRequest.getResize().getWidth() + "x"
                    + transformationRequest.getResize().getHeight()
                    + "_" + imageName;
            image.setName(resizeName);

            if (transformationRequest.getResize() != null && transformationRequest.getCrop() == null) {
                transformedImage = Scalr.resize(transformedImage, Scalr.Method.ULTRA_QUALITY,
                        Scalr.Mode.FIT_EXACT, transformationRequest.getResize().getWidth(),
                        transformationRequest.getResize().getHeight());
            }

            if (transformationRequest.getCrop() != null) {
                transformedImage = Scalr.crop(transformedImage, transformationRequest.getCrop().getX(),
                        transformationRequest.getCrop().getY(), transformationRequest.getCrop().getWidth(),
                        transformationRequest.getCrop().getHeight());
            }

            if (transformationRequest.getGrayscale() != null && transformationRequest.getGrayscale()) {
                BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                transformedImage = op.filter(transformedImage, null);
            }

            if (transformationRequest.getRotate() != null) {
                transformedImage = Scalr.rotate(transformedImage,
                        Scalr.Rotation.valueOf("CW_" + transformationRequest.getRotate()));
            }

            try {
                ImageIO.write(transformedImage, "png", Paths.get(uploadDir).resolve(resizeName).toFile());
                image.setName(filePath.toString());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to write transformed image", e);
            }
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to transform image", e);
        }
    }
}
