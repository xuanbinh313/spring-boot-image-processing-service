package com.binhcodev.spring_boot_image_processing_service.controllers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binhcodev.spring_boot_image_processing_service.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
