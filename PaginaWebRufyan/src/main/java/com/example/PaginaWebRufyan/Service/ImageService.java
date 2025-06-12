package com.example.PaginaWebRufyan.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.ImageRepository;


@Service
public class ImageService { 
	//private final String UPLOAD_DIR = "C://Users//PP//Documents//Proyectos_Programación//Backends//Back_end_rufyan//PaginaWebRufyan//PaginaWebRufyan//src//main//resources//static";
	
	 @Value("${file.upload-dir}")
	 private  String uploadDir;
	@Autowired
	private ImageRepository imageRepository;
	
	public List<Image> findAllImages(){
		return imageRepository.findAll();
			}
	
	public Optional<Image> findImageById(Integer id) {
		Optional<Image> optionalImage= imageRepository.findById(id);
	/*	if(image.isEmpty()) {
			throw new RuntimeException(" No se econtró la imagen id: " + id);
		}*/
		return optionalImage;
	}
		
	public Image saveImage(Image image) {
		return imageRepository.save(image);
	}
	
	public void deleteImageById(Integer id ) {
		imageRepository.deleteById(id);
	}
	
	public Image updateImageById(Integer id, Image imageData) {
		Optional<Image> imageToUpdate= imageRepository.findById(id);
		
		if(imageToUpdate.isPresent()) {
			Image image= imageToUpdate.get();
			image.setId(imageData.getId());
			image.setUrl(imageData.getUrl());
		return imageRepository.save(image);
		} else {
			throw new ResourceNotFoundException("Image not found with id: "+id);
		}
		
	}

public List<Image> processImages(List<MultipartFile> imageFiles) {
		
		return imageFiles.stream().map((file)->{
			try {
				// Primero se guarda el archivo en el sistema de archivo
				String fileName =System.currentTimeMillis()+ "_"+ file.getOriginalFilename();
				Path filePath = Paths.get( uploadDir +"/UploadedImages/UploadedPaintingImages");
				if(!Files.exists(filePath)) {
					Files.createDirectories(filePath);
				}
				Path savedFilePath = filePath.resolve(fileName);
				Files.copy(file.getInputStream(),savedFilePath, StandardCopyOption.REPLACE_EXISTING);
				//Files.write(filePath, file.getBytes());
				// Agregamos el path del archivo a la image
				Image image = Image.builder() 
								   .url("http://localhost:8080/UploadedImages/UploadedPaintingImages/"+fileName) 
								  .productName(file.getOriginalFilename()).build();
				
				return image;
			}
			catch(IOException e) {
				throw new RuntimeException(e);
			}
			
		}).collect(Collectors.toList());
		

		
		}

	
	
}