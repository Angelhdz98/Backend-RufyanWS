package com.example.PaginaWebRufyan.Controller;


import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import com.example.PaginaWebRufyan.Service.ImageService;
import com.example.PaginaWebRufyan.Service.ProductService;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
							.cartItems(Set.of())
							.orderItems(Set.of())
							.favoriteOf(Set.of())
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