package com.example.PaginaWebRufyan.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import com.example.PaginaWebRufyan.Service.ProductsCategoryService;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@PreAuthorize("permitAll()")
@Controller
public class ProductsCategoryController {
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
