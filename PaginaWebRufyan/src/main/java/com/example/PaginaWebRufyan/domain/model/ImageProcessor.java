package com.example.PaginaWebRufyan.domain.model;


import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class ImageProcessor {
    @Value("${app.storage.upload-dir}")
    private String uploadDir;
    private final ImageStorageProperties imgProperties;

    public ImageProcessor(ImageStorageProperties imgProperties) {
        this.imgProperties = imgProperties;
    }


    public  Set<ImageDomain> processImages(List<MultipartFile> imageFiles, String productName, String uploadDir) {
        Set<ImageDomain> collected = imageFiles.stream().map((file) -> {
            try {
                if (file.getSize() > 15 * 1024 * 1024) {

                    throw new RuntimeException("File too large");

                }
                // Primero se guarda el archivo en el sistema de archivo
                        /*
                        Image.builder()
                        .url("http://localhost:8080/UploadedImages/UploadedPaintingImages/"+fileName)
                        .productName(file.getOriginalFilename()).build();*/
                String fileName = file.getOriginalFilename() + "_" + System.currentTimeMillis();
                Path filePath = Paths.get(uploadDir, "UploadedImages", "UploadedPaintingImages");
                if (!Files.exists(filePath)) {
                    Files.createDirectories(filePath);
                }
                Path savedFilePath = filePath.resolve(fileName);
                Files.copy(file.getInputStream(), savedFilePath, StandardCopyOption.REPLACE_EXISTING);
                // URL pública (esto es lo importante)
                String publicUrl = "/images/UploadedImages/UploadedPaintingImages/" + fileName;
                return new ImageDomain(null, productName, publicUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).collect(Collectors.toSet());
        System.out.println("Imagenes procesadas en processImages: " + collected);
        return collected;
    }
    public Set<ImageDomain> processImages(List<MultipartFile> imageFiles, String productName) {

        Path uploadPath = Paths.get(uploadDir, "UploadedImages", "UploadedPaintingImages");

        System.out.println("archivos recibidos: "+ imageFiles.toString());

        try {

            if (!Files.exists(uploadPath)) {

                Files.createDirectories(uploadPath);

            }

        } catch (IOException e) {

            throw new RuntimeException("Error creating upload directory", e);

        }

        Set<ImageDomain> collected = imageFiles.stream().map(file -> {

            try {
                // 🔒 Sanitizar nombre
                String originalName = file.getOriginalFilename();
                String cleanName = originalName != null ? originalName.replaceAll("\\s+", "_") : "image";
                //  Nombre único
                String fileName = System.currentTimeMillis() + "_" + cleanName;
                Path savedFilePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), savedFilePath, StandardCopyOption.REPLACE_EXISTING);
                // URL pública (clave)
                String publicUrl = "/images/UploadedImages/UploadedPaintingImages/" + fileName;
                return new ImageDomain(null, productName, publicUrl);
            } catch (IOException e) {
                throw new RuntimeException("Error saving file: " + file.getOriginalFilename(), e);
            }
        }).collect(Collectors.toSet());
        System.out.println("Imagenes procesadas: " + collected);
        return collected;

    }
}

