package com.example.PaginaWebRufyan.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.ImageRepository;


@Service
public class ImageService {
	@Autowired
	private ImageRepository imageRepository;
	
	public List<Image> findAllImages(){
		return imageRepository.findAll();
			}
	
	public Image findbyImageId(Integer id) {
		Optional<Image> image= imageRepository.findById(id);
		if(image.isEmpty()) {
			throw new RuntimeException(" No se econtró la imagen id: " + id);
		}
		return image.get();
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
	
	
}