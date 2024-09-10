package com.example.PaginaWebRufyan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.ProductsCategory;

public interface ProductsCategoryRepository extends JpaRepository<ProductsCategory, Integer> {
	
	Optional<Product> findByCategory(String name);
}
