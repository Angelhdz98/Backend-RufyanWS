package com.example.PaginaWebRufyan.Controller;


import java.util.HashSet;
import java.util.List;


import com.example.PaginaWebRufyan.DTO.ProductDTO;
import com.example.PaginaWebRufyan.DTO.ProductRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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
	//@ModelAttribute Product product,
	@Transactional
	@PostMapping("/products/create")
	public ResponseEntity<Product> createProduct(ProductRegisterDTO productData) {
			
		List<Image> images= imageService.processImages(productData.getImageFiles());

		Product newProduct = Product.builder()
							.additionalFeatures(productData.getAdditionalFeatures())
							.category(productData.getProductsCategory())
							.creationDate(productData.getCreationDate())
							.description(productData.getDescription())
							.isFavorite(productData.getIsFavorite())
							.name(productData.getName())
							.price(productData.getPrice())
							.style(productData.getStyle())
							.image(images)
							.cartItems(new HashSet<>())
							.orderItems(new HashSet<>())
							.favoriteOf(new HashSet<>())
							.build();
		
		
		return ResponseEntity.ok(productService.saveProduct(newProduct));
		/*catch(IOException e) {
			return ResponseEntity.badRequest().build();
		}*/
		
	}
	
	@Transactional
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductDTO> updateProductById(Integer id, ProductDTO productData){

		return  ResponseEntity.ok(productService.updateProductById(id, productData)) ;
	}
	
	
	@Transactional
	@DeleteMapping
	public ResponseEntity<Void> deleteProductById(@PathVariable Integer id){

			productService.deleteProductById(id);
	return ResponseEntity.noContent().build();

		
	}
	
	
}