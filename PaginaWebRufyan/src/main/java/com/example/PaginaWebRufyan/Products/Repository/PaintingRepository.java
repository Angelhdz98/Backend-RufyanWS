package com.example.PaginaWebRufyan.Products.Repository;




import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PaginaWebRufyan.Products.Entity.Painting;

public interface PaintingRepository extends JpaRepository<Painting, Integer> {
	Optional<Painting> findByName(String name);
	
	List<Painting> findByNameContaining(String namePart);
	
	
	List<Painting> findByNameContainingIgnoreCase(String namePart);
	Page<Painting> findByNameContainingIgnoreCase(String namePart, Pageable pageable);
	
/*
	List<Painting> findByStyleContainingIgnoreCase(String stylePart);
	Page<Painting> findByStyleContainingIgnoreCase(String stylePart, Pageable pageable );
	
	

	List<Painting> findByMediumContainingIgnoreCase(String mediumPart);
	Page<Painting> findByMediumContainingIgnoreCase(String mediumPart, Pageable pageable);
	

	List<Painting> findBySupportMaterialContainingIgnoreCase(String support_materialPart);
	Page<Painting> findBySupportMaterialContainingIgnoreCase(String support_materialPart, Pageable pageable);
 */
	
	boolean existsByName(String namePart);
	
	
	
}
