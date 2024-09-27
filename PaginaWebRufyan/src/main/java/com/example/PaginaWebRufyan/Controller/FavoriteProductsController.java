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

import com.example.PaginaWebRufyan.Entity.FavoriteRequest;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Service.FavoritesProductsService;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@PreAuthorize("permitAll()")
@RequestMapping("/my-favorites")
public class FavoriteProductsController {
	
	@Autowired
	FavoritesProductsService favoritesProductsService;
	
	@PostMapping("/add")
	public ResponseEntity<Product> addFavorite(@RequestBody FavoriteRequest favoriteRequest ){
		
		Optional<Product>optionalProduct= favoritesProductsService.addProductToFavorites(favoriteRequest.getUserId(), favoriteRequest.getProductId());
		
		if(optionalProduct.isPresent()) {
			
			return ResponseEntity.ok(optionalProduct.get());
		}
		
			 return ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/remove")
	public  ResponseEntity<Product> removeFavorite(@RequestBody FavoriteRequest favoriteRequest ){
		
		Optional<Product> optionalProduct = favoritesProductsService. 
				removeProductfromFavorites(favoriteRequest.getUserId(), favoriteRequest.getProductId());
		
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	

	
}



