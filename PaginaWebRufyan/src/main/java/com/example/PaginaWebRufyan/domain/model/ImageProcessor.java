package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ImageProcessor {

    @Value("${file.upload-dir}")
    private static  String uploadDir;

    public static Set<ImageDomain> processImages(List<MultipartFile> imageFiles, String productName) {

        return imageFiles.stream().map((file)->{
            try {
                // Primero se guarda el archivo en el sistema de archivo
                String fileName = file.getOriginalFilename()+"_"+System.currentTimeMillis();
                Path filePath = Paths.get( uploadDir +"/UploadedImages/UploadedPaintingImages");
                if(!Files.exists(filePath)) {
                    Files.createDirectories(filePath);
                }
                Path savedFilePath = filePath.resolve(fileName);
                Files.copy(file.getInputStream(),savedFilePath, StandardCopyOption.REPLACE_EXISTING);
                //Files.write(filePath, file.getBytes());
                // Agregamos el path del archivo a la image
                ImageDomain image = new ImageDomain(null,productName,productName);
                        /*
                        Image.builder()
                        .url("http://localhost:8080/UploadedImages/UploadedPaintingImages/"+fileName)
                        .productName(file.getOriginalFilename()).build();*/
                return image;
            }
            catch(IOException e) {
                throw new RuntimeException(e);
            }

        }).collect(Collectors.toSet());
}}

