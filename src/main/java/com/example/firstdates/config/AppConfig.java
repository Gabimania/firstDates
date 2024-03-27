package com.example.firstdates.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${upload.path}")
    private String userImageDirectory;

    public String getUserImageDirectory() {
        return userImageDirectory;
    }
}