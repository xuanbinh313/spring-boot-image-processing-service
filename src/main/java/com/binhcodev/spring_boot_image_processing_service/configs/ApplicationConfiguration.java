package com.binhcodev.spring_boot_image_processing_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.binhcodev.spring_boot_image_processing_service.services.UserService;

@Configuration
public class ApplicationConfiguration {
    private UserService userService;

    @Bean
    UserDetailsService userDetailsService() {
        return email -> userService.findByEmail(email);
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
}
