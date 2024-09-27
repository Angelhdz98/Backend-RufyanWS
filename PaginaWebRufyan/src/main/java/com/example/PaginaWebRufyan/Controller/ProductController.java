package com.example.PaginaWebRufyan.Controller;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Service.ProductService;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@PreAuthorize("permitAll()")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productService.retrieveAllProducts();
	}
	@PreAuthorize("permitAll()")
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
		Optional<Product> optionalProduct = productService.retrieveProductById(id);
		if(optionalProduct.isPresent()) {
			System.out.println(optionalProduct.get().getFavoriteOf());
			
			return ResponseEntity.ok(optionalProduct.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping("/products/create")
	public ResponseEntity<Product> createProduct(@ModelAttribute Product product, @RequestParam("image") List<MultipartFile> imageFiles) {
		
		try {
			return ResponseEntity.ok(productService.saveProductWithImages( product, imageFiles));
			}
		catch(IOException e){
			return ResponseEntity.badRequest().build();
		}
		/*catch(IOException e) {
			return ResponseEntity.badRequest().build();
		}*/
		
	}
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProductById(@PathVariable Integer id, @RequestBody Product product){
		Optional<Product> optionalProduct = productService.retrieveProductById(id);
		if(optionalProduct.isPresent()) {
		return	ResponseEntity.ok(productService.updateProductById(id, product));	
		}else {
			return ResponseEntity.notFound().build();		}
		
	}
	
	
	@DeleteMapping
	public ResponseEntity<Product> deleteProductById(@PathVariable Integer id){
		Optional<Product> optionalProductDeleted = productService.deleteProductById(id);
		
		if(optionalProductDeleted.isPresent()) {
			return ResponseEntity.ok(optionalProductDeleted.get());
		}else {
			return ResponseEntity.notFound().build();
			
		}
		
		
	}
	
	
}