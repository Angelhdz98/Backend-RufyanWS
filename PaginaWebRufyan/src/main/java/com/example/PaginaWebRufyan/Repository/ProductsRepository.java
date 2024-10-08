
package com.example.PaginaWebRufyan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PaginaWebRufyan.Entity.Product;


public interface ProductsRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findByName(String name);
	@Query("SELECT u FROM Product u JOIN FETCH u.favoriteOf WHERE u.id = :id")
	Optional<Product> findByIdWithFavoriteProducts(@Param("id") Integer id);
}
