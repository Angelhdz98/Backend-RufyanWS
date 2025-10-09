package com.example.PaginaWebRufyan.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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