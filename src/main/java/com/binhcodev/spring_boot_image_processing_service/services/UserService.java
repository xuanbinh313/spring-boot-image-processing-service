package com.binhcodev.spring_boot_image_processing_service.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.binhcodev.spring_boot_image_processing_service.controllers.UserRepository;
import com.binhcodev.spring_boot_image_processing_service.entities.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
