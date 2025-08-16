package com.example.PaginaWebRufyan.Products.Categories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsCategoryRepository extends JpaRepository<ProductsCategory, Integer> {
	
	Optional<ProductsCategory> findByName(String name);
}
