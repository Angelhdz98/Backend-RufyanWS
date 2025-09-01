package com.example.PaginaWebRufyan.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PaginaWebRufyan.DTO.FavoriteRequestDTO;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;


@CrossOrigin(origins = "http://localhost:5173/")
@PreAuthorize("permitAll()")
public class FavoriteProductsController {
	
	@Autowired

	
	@PostMapping("/add")
	public ResponseEntity<Product> addFavorite(@RequestBody FavoriteRequestDTO favoriteRequest ){
		
		Optional<Product>optionalProduct= null;
		
		if(optionalProduct.isPresent()) {
			
			return ResponseEntity.ok(optionalProduct.get());
		}
		
			 return ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/remove")
	public  ResponseEntity<Product> removeFavorite(@RequestBody FavoriteRequestDTO favoriteRequest ){
		
		Optional<Product> optionalProduct = Optional.empty();

		
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	

	
}



