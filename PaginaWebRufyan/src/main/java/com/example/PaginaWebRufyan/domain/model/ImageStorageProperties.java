package com.example.PaginaWebRufyan.domain.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageStorageProperties {

    private static String STATIC_IMAGES_URL;

    @Value("${app.upload-dir}")
    private String  uploadDir;


    @PostConstruct
    public void init() {

        System.out.println("upload dir: "+ uploadDir);
        STATIC_IMAGES_URL = System.getProperty("app.upload-dir");
        System.out.println("STATIC_IMAGES_URL = " + STATIC_IMAGES_URL);

    }
    public static String getUploadDir(){
        return STATIC_IMAGES_URL;
    }

}
