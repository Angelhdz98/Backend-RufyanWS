package com.example.PaginaWebRufyan.Image.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.PaginaWebRufyan.Image.DTO.ImageDTO;
import com.example.PaginaWebRufyan.Utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Image.Repository.ImageRepository;



public class ImageService { 
	//private final String UPLOAD_DIR = "C://Users//PP//Documents//Proyectos_Programación//Backends//Back_end_rufyan//PaginaWebRufyan//PaginaWebRufyan//src//main//resources//static";
	
	 @Value("${file.upload-dir}")
	 private  String uploadDir;
	@Autowired
	private ImageRepository imageRepository;

	public List<ImageDTO> findAllImages(){
		return imageRepository.findAll().stream().map(ImageDTO::new).collect(Collectors.toList());
			}

			private Image findImageById(Integer id){
		return imageRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Imagen no encontrada con Id: "+ id));
			}

	public ImageDTO retrieveImageById(Integer id) {
	return new ImageDTO(findImageById(id));
	}
		
	public Image saveImage(Image image) {
		return imageRepository.save(image);
	}
	
	public void deleteImageById(Integer id ) {

		Image imageToDelete = findImageById(id);
		ImageUtils.deleteImageFiles(imageToDelete.getUrl());
		imageRepository.deleteById(id);
	}

	public void deleteAllImages(List<Image> imagesToDelete){
		//got deleted imageFiles from server
		for  ( Image image:imagesToDelete){
			ImageUtils.deleteImageFiles(image.getUrl());
		}

		imageRepository.deleteAll(imagesToDelete);
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


		

		
		}

	
	
}