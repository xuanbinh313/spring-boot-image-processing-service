package com.binhcodev.spring_boot_image_processing_service.exceptions;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }
}