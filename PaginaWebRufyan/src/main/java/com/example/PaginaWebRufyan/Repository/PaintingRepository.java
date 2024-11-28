package com.example.PaginaWebRufyan.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PaginaWebRufyan.Entity.Painting;

public interface PaintingRepository extends JpaRepository<Painting, Integer> {
	Optional<Painting> findByName(String name);
	
	List<Painting> findByNameContaining(String namePart);
	
	boolean existByName(String namePart);
	
	
	
}
