
package com.example.PaginaWebRufyan.Repository;



import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PaginaWebRufyan.Entity.Product;


public interface ProductsRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findByName(String name);

	
	List<Product> findByNameContainingIgnoreCase(String namePart);
	Page<Product> findByNameContainingIgnoreCase(String namePart, Pageable pageable);
	
		
}
