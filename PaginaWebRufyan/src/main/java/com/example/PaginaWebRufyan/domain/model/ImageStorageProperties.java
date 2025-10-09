package com.example.PaginaWebRufyan.domain.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
public class ImageStorageProperties {

    private static String STATIC_IMAGES_URL;

    @Value("${app.upload-dir}")
    private String  uploadDir;


    @PostConstruct
    public void init() {
        STATIC_IMAGES_URL = this.uploadDir;
        System.out.println("STATIC_IMAGES_URL = " + STATIC_IMAGES_URL);
    }
    public static String getUploadDir(){
        return STATIC_IMAGES_URL;
    }

}
