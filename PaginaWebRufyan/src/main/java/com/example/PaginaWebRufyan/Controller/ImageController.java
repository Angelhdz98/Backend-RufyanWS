
package com.example.PaginaWebRufyan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Service.ImageService;

@RestController
public class ImageController {
	@Autowired
	private ImageService imageService;
	
	@GetMapping("/images")
	public List<Image> retrieveAllImages(){
		return imageService.findAllImages();
	}
	
	@GetMapping("/images/{id}")
	public Image findImageById(@PathVariable Integer id) {
		return imageService.findbyImageId(id);
	}
	@PostMapping("/images")
	public void saveImage(@RequestBody Image image) {
		imageService.saveImage(image);
	}
	@PutMapping("/images/{id}")
	public ResponseEntity<Image> updateImage(@PathVariable Integer id, @RequestBody Image image ){
		Image updatedImage = imageService.updateImageById(id, image);
		return ResponseEntity.ok(updatedImage);
	}
	
	@DeleteMapping("/images/{id}")
	public void deleteImage(@PathVariable Integer id ) {
		imageService.deleteImageById(id);
	}
	
	
}
