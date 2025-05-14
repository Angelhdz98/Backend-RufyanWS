package com.example.PaginaWebRufyan.Controller;


import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
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

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import com.example.PaginaWebRufyan.Service.ImageService;
import com.example.PaginaWebRufyan.Service.ProductService;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
	public ResponseEntity<List<Product>> getAllProducts(){
		return ResponseEntity.ok(productService.retrieveAllProducts());
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
	//@ModelAttribute Product product,
	@Transactional
	@PostMapping("/products/create")
	public ResponseEntity<Product> createProduct(@RequestParam("name") @NotNull @NotBlank @Size(min=3,max=127) String name,
			@RequestParam("description")@NotNull @NotBlank @Size(min=12,max=511)String description,
			@RequestParam("creationDate")@NotNull @PastOrPresent LocalDate creationDate,
			@RequestParam("style") @NotNull @Size(min=3,max=127) String style,
			@RequestParam("price") @NotNull @Min(14) int price,
			@RequestParam("favorite")@NotBlank @Pattern(regexp = "^(true|false)$") String favorite,
			@RequestParam("category")@NotNull ProductsCategory category,
			@RequestParam("adittionalFeatures") LinkedHashMap<String, String> adittionalFeatures,
			@RequestParam("image")@NotNull@Size(min=1,max=6) List<MultipartFile> imageFiles) {
			
		List<Image> images= imageService.processImages(imageFiles);
		Product newProduct = Product.builder()
							.adittionalFeatures(adittionalFeatures)
							.category(category)
							.creationDate(creationDate)
							.description(description)
							.favorite(Boolean.valueOf(favorite))
							.name(name)
							.price(price)
							.style(style)
							.image(images)
							.build();
		
		
		return ResponseEntity.ok(productService.saveProduct(newProduct));
		/*catch(IOException e) {
			return ResponseEntity.badRequest().build();
		}*/
		
	}
	
	@Transactional
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProductById(@PathVariable Integer id, @RequestParam("name") @NotNull @NotBlank @Size(min=3,max=127) String name,
			@RequestParam("description")@NotNull @NotBlank @Size(min=12,max=511)String description,
			@RequestParam("creationDate")@NotNull @PastOrPresent LocalDate creationDate,
			@RequestParam("style") @NotNull @Size(min=3,max=127) String style,
			@RequestParam("price") @NotNull @Min(14) int price,
			@RequestParam("favorite")@NotBlank @Pattern(regexp = "^(true|false)$") String favorite,
			@RequestParam("category")@NotNull ProductsCategory category,
			@RequestParam("adittionalFeatures") LinkedHashMap<String, String> adittionalFeatures,
			@RequestParam("image")@NotNull@Size(min=1,max=6) List<MultipartFile> imageFiles){
		Optional<Product> optionalProduct = productService.retrieveProductById(id);
		if(optionalProduct.isPresent()) {
			List<Image> allImages= optionalProduct.get().getImage();
			
			List<Image>UpdatedImage = imageService.processImages(imageFiles);
			allImages.addAll(UpdatedImage);
			
				Product updatedProduct = Product.builder()
						.adittionalFeatures(adittionalFeatures)
						.category(category)
						.creationDate(creationDate)
						.description(description)
						.favorite(Boolean.valueOf(favorite))
						.name(name)
						.price(price)
						.style(style)
						.image(allImages)
						.build();

		return	ResponseEntity.ok(productService.updateProductById(id, updatedProduct));	
		}else {
			return ResponseEntity.notFound().build();		}
		
	}
	
	
	@Transactional
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