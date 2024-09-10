
package com.example.PaginaWebRufyan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PaginaWebRufyan.Entity.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findByName(String name);
}
