package com.example.PaginaWebRufyan.Controller;


import java.util.HashSet;
import java.util.List;


import com.example.PaginaWebRufyan.DTO.ProductDTO;
import com.example.PaginaWebRufyan.DTO.ProductRegisterDTO;
import com.example.PaginaWebRufyan.DTO.ProductUpdateRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Service.ImageService;
import com.example.PaginaWebRufyan.Service.ProductService;

import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@PreAuthorize("permitAll()")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ImageService imageService;



	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts(){
		return ResponseEntity.ok(productService.retrieveAllProducts());
	}
	@PreAuthorize("permitAll()")
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
		return ResponseEntity.ok(productService.retrieveProductById(id));

	}
	//Factory pattern
	//@ModelAttribute Product product,
	@Transactional
	@PostMapping("/products/create")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductUpdateRegisterDTO productData) {

		return ResponseEntity.ok(productService.createProduct(productData));

	}
	
	@Transactional
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductDTO> updateProductById(@PathVariable Integer id,@RequestBody ProductUpdateRegisterDTO productData){

		return  ResponseEntity.ok(productService.updateProductById(id, productData)) ;
	}
	
	
	@Transactional
	@DeleteMapping
	public ResponseEntity<Void> deleteProductById(@PathVariable Integer id){

			productService.deleteProductById(id);
	return ResponseEntity.noContent().build();

		
	}
	
	
}