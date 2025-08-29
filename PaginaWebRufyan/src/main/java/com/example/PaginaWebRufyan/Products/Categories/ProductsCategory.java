package com.example.PaginaWebRufyan.Products.Categories;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@EqualsAndHashCode
@Table(name="category")
public class ProductsCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@CrossOrigin(origins = "http://localhost:5173/")
	@RestController
	@PreAuthorize("permitAll()")
	@Controller
	public static class ProductsCategoryController {
		@Autowired
		private ProductsCategoryService categoryService;

		@GetMapping("/products-category")
		public List<ProductsCategory> getAllCategories(){
			return categoryService.retrieveAllCategories();
		}

		@GetMapping("/products-category/{id}")
		public ResponseEntity<ProductsCategory> getCategoryById(@PathVariable Integer id){
			 Optional<ProductsCategory> optionalCategory= categoryService.retrieveCategoryById(id);
			if(optionalCategory.isPresent()) {
			return ResponseEntity.ok(optionalCategory.get());
		}else{
			return ResponseEntity.notFound().build();
		}

		}

		@PostMapping("/products-category")
		public ResponseEntity<ProductsCategory> addCategory(@ModelAttribute ProductsCategory productCategory ){

				return ResponseEntity.ok(categoryService.saveCategory(productCategory));

			}


		@PutMapping("/products-category/{id}")
		public ResponseEntity<ProductsCategory> updateCategoryById(@PathVariable Integer id, 	@RequestBody ProductsCategory category){
		Optional<ProductsCategory> optionalCategory = categoryService.retrieveCategoryById(id);

			if (optionalCategory.isPresent() ) {
				return ResponseEntity.ok(categoryService.updateById(id, category));
			}
			else {
				 return ResponseEntity.notFound().build();
			 }

		}

		@DeleteMapping("/products-category/{id}")
		public ResponseEntity<ProductsCategory> deleteCategoryById(@PathVariable Integer id){

			Optional<ProductsCategory> optionalCategory = categoryService.retrieveCategoryById(id);
			 if(optionalCategory.isPresent()) {
				 categoryService.deleteCategoryById(id);
				 return ResponseEntity.ok(null);
			 }
			 else {
				 return ResponseEntity.notFound().build();
			 }

		}


	}
}
