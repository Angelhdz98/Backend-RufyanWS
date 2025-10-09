package com.example.PaginaWebRufyan.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ImageStoragePropertiesTest {

    @Test
    @DisplayName("Test para inicializar ImageStorageProperties correctamente")
    void getUploadDir() {


        String uploadDir = ImageStorageProperties.getUploadDir();
        assertThat(uploadDir).isNotNull();
        assertThat(uploadDir).isNotEqualTo("null");
        System.out.println("uploadDir:  "+ ImageStorageProperties.getUploadDir());



    }
}