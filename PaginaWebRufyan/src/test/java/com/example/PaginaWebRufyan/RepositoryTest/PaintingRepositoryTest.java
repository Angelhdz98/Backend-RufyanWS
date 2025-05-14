package com.example.PaginaWebRufyan.RepositoryTest;


import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.PaginaWebRufyan.Entity.Painting;
import com.example.PaginaWebRufyan.Repository.PaintingRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PaintingRepositoryTest {
	
	@Autowired
	private PaintingRepository paintingRepository;
	
	private Painting paintingTest1;
	private Painting paintingTest2;
	
	
	@BeforeEach
	public void setUp() {
				paintingTest1 = Painting.builder()
						.name("El llanto perfecto")
						.copies_made(10)
						.available_copies(7)
						.build();
				
				paintingTest2 = Painting.builder()
						.name("La cueva de platón")
						.copies_made(10)
						.available_copies(3)
						.build();
	}
				
	
	
	@DisplayName("Test para probar el agregar una pintura al repositorio")
	@Test
	public void addUserRepositoryTest() {
		
		
		
		Painting savedPainting = paintingRepository.save(paintingTest1);
		
		Assertions.assertThat(savedPainting).isNotNull();
		Assertions.assertThat(savedPainting.getName()).isEqualTo(paintingTest1.getName());
		
		

	}
	
	@DisplayName("Test para probar que se haya actualizado una obra ")
	@Test
	public void updatePaintingRepositoryTest() {
		
		Painting savedPainting = paintingRepository.save(paintingTest1);
		
		savedPainting.setName("El llanto perfecto del afligido");
		savedPainting.setAvailable_copies(2);
		
		Painting updatedPainting = paintingRepository.save(savedPainting);
		
		Assertions.assertThat(updatedPainting.getName()).isEqualTo("El llanto perfecto del afligido");
		Assertions.assertThat(updatedPainting.getId()).isEqualTo(savedPainting.getId());
		Assertions.assertThat(updatedPainting.getAvailable_copies()).isEqualTo(2);
		
		
	}
	@DisplayName("Test para comprobar que se encuentre una obra por ID")
	@Test
	public void findByIdRepositoryTest () {
		
		Painting savedPainting1 = paintingRepository.save(paintingTest1);
		Painting savedPainting2 = paintingRepository.save(paintingTest2);
		
		Painting findedPainting = paintingRepository.findById(savedPainting1.getId()).get();
		
		Assertions.assertThat(findedPainting).isEqualTo(savedPainting1);
		
		
		
		
	}
	
	@DisplayName("Test para comprobar la eliminación de una entidad")
	@Test
	public void deleteByIdRepositoryTest() {
		
		Painting savedPainting1 = paintingRepository.save(paintingTest1);
		Painting savedPainting2 = paintingRepository.save(paintingTest2);
		
		paintingRepository.deleteById(savedPainting1.getId());
		
		Optional<Painting> paintingReturn = paintingRepository.findById(savedPainting1.getId());
		
		Assertions.assertThat(paintingReturn).isEmpty();
		
		
		
	}
	

}



