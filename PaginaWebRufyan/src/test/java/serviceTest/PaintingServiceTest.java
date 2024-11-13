package serviceTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.type.descriptor.jdbc.LocalDateTimeJdbcType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Painting;
import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import com.example.PaginaWebRufyan.Exceptions.InconsitentDataException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.ImageRepository;
import com.example.PaginaWebRufyan.Repository.PaintingRepository;
import com.example.PaginaWebRufyan.Service.PaintingService;

@ExtendWith(MockitoExtension.class)
public class PaintingServiceTest {
// generamos una instancia Mock de Paintingrepository 
	@Mock
	private PaintingRepository paintingRepo;
	
	@Mock
	private ImageRepository imageRepository; 
	
	@InjectMocks
	private PaintingService paintingService;
	
	Painting paintingTestOk1;
	
	Painting paintingTestOk2;
	
	
	
	
	
	@BeforeEach
	void setUp() {
		
		final String HOSTLINK= "http://localhost:8080/static/";

		//Agregamos imagenes 
		Image obra1Image = new Image();
		obra1Image.setUrl(HOSTLINK+"obra2.jpg");
		obra1Image.setId(1);
		Image obra2Image = new Image();
		obra2Image.setUrl(HOSTLINK+"obra3.jpg");
		obra2Image.setId(2);
		Image obra3Image = new Image();
		obra3Image.setUrl(HOSTLINK+"obra3.jpg");
		obra3Image.setId(3);
		Image obra4Image = new Image();
		obra4Image.setUrl(HOSTLINK+"obra5.png");
		obra4Image.setId(4);
		Image obra5Image = new Image();
		obra5Image.setUrl(HOSTLINK+"obra6.png");
		obra5Image.setId(5);
		Image obra6Image = new Image();
		obra6Image.setUrl(HOSTLINK+"obra7.png");
		obra6Image.setId(6);
		
		ProductsCategory pinturaCategory = ProductsCategory.builder().name("paintings").build();
		
		
		Painting paintingTestOk1 = Painting
									.builder()
									.name("1era Obra, datos correctos")
									.description("Obra con todos los datos correctos ")
									.creation_date(LocalDate.of(2020, 3, 25))
									.price(1000)
									.category(pinturaCategory)
									.style("Urbano")
									.favorite(true)
									.image(List.of(obra1Image, obra2Image))
									.altura_cm(60)
									.largo_cm(80)
									.medium("Aceite")
									.support_material("Lienzo")
									.available_copies(8)
									.copies_made(12)
									.price_copy(300)
									.build();
		
		Painting paintingTestOk2 = Painting
				.builder()
				.name("1era Obra, datos correctos")
				.description("Obra con todos los datos correctos ")
				.creation_date(LocalDate.of(2020, 3, 25))
				.price(1000)
				.category(pinturaCategory)
				.style("Urbano")
				.favorite(true)
				.image(List.of(obra1Image, obra2Image))
				.altura_cm(60)
				.largo_cm(80)
				.medium("Aceite")
				.support_material("Lienzo")
				.available_copies(8)
				.copies_made(12)
				.price_copy(300)
				.build();

		
	
		
		
	}
	@DisplayName("Test para encontrar una Obra por id")
	@Test
	void findPaintingByIdTest() {
		
		given(paintingRepo.findById(1)).willReturn(Optional.of(paintingTestOk1));
		
			Painting ObraEncontrada =  paintingService.findById(1).get();
		
			assertThat(ObraEncontrada).isNotNull();

	}
	
	@DisplayName("Test para encontrar una Obra por nombre")
	@Test
	void findPaintingByNameTest() {
		
		given(paintingRepo.findByName("1era Obra, datos correctos")).willReturn(Optional.of(paintingTestOk1));
		
			Painting ObraEncontrada =  paintingService.findPaintingByName("1era Obra, datos correctos").get();
		
			assertThat(ObraEncontrada).isNotNull();

	}
	
	@DisplayName("Test para encontrar una Obra por nombre")
	@Test
	void savePaintingTestOk() {
		
		given(paintingRepo.findById(1)).willReturn(Optional.empty());
		given(paintingRepo.save(paintingTestOk1)).willReturn(paintingTestOk1);
		
			Painting ObraGuardada =  paintingService.save(paintingTestOk1);
		
			assertThat(ObraGuardada).isNotNull();

	}
	
	void savePaintingTestInconsistentData() {
		
		
		Painting obraGuardada= paintingTestOk1;
		 // Se agrega el id a obra guardada por que eso distinguiría a la obra que vamos a subir de la obra que ya esta guardada
		
		Painting moreAvailableThanMade = obraGuardada;
		moreAvailableThanMade.setAvailable_copies(15);
		moreAvailableThanMade.setCopies_made(10);
		
		Painting wrongMeasures = obraGuardada;
		
		wrongMeasures.setAltura_cm(0);
		wrongMeasures.setLargo_cm(-1);
		
		Painting wrongPricing = obraGuardada;
		
		wrongPricing.setPrice(10);
		wrongPricing.setPrice_copy(20);
		
		Painting wrongDate = obraGuardada;
		 
		 wrongDate.setCreation_date(LocalDate.of(2025, 10, 17));
		 
		 
		 
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.save( wrongMeasures);
		});
		

		assertThrows(InconsitentDataException.class, ()->{
			paintingService.save( wrongPricing);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.save( wrongPricing);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.save(wrongDate);
		});
		
		
		
		verify(paintingRepo, never()).save(any(Painting.class));
		

		
	}
	
	@DisplayName("Test para encontrarlas obras favoritas")
	@Test
	void findFavoritesPaintingTest() {
		
		Painting obraFavorita = Painting.builder().favorite(true).build();
		Example<Painting> ejemploObraFavorita = Example.of(obraFavorita);
		given(paintingRepo.findAll(ejemploObraFavorita)).willReturn(List.of(paintingTestOk1, paintingTestOk2));
		
			List<Painting> ObrasFavoritas =  paintingService.findFavoritePaintings();
		
			assertThat(ObrasFavoritas).isNotNull();
			assertThat(ObrasFavoritas).isNotEmpty();
			
	}

	@DisplayName("Test para actualizar una obra de la manera correcta")
	@Test
	void  updatePaintingOkTest() {
		
		Painting responsePainting = paintingTestOk1;
		Integer id=1;
		
		given(paintingRepo.findById(id).get()).willReturn(responsePainting);
		Painting updatedPainting = responsePainting;
		String newName = "Un nuevo amanecer"; 
		updatedPainting.setName(newName);
		given(paintingRepo.save(updatedPainting)).willReturn(updatedPainting);
		
		Painting updatedResponse = paintingService.updatePaintingById(id,updatedPainting);
		
		assertThat(updatedResponse).isNotNull();
		assertThat(updatedResponse.getId()).isEqualTo(id);
		assertThat(updatedResponse.getName()).isEqualTo(newName);
	
	}
	
	@DisplayName("Test para intentar actualizar una obra que no existe")
	@Test
	void updatePaintingNotFoundedTest() {
		
Integer id=12;
		
		given(paintingRepo.findById(id)).willReturn(Optional.empty());
		Painting updatedPainting = paintingTestOk1;
		String newName = "Un nuevo amanecer"; 
		updatedPainting.setName(newName);
	 	
		assertThrows(ResourceNotFoundException.class, ()->{
			paintingService.updatePaintingById(id, updatedPainting);
			
		});
		
		verify(paintingRepo, never()).save(any(Painting.class));
		
		
	}
	
	@DisplayName("Test para intentar subir obra que tiene datos inconsistentes ")
	@Test
	void updatePaintingInconsistentDataTest() {
		
		Integer id = 1;
		Painting obraGuardada= paintingTestOk1;
		 // Se agrega el id a obra guardada por que eso distinguiría a la obra que vamos a subir de la obra que ya esta guardada
		obraGuardada.setId(id);

		
		Painting moreAvailableThanMade = obraGuardada;
		moreAvailableThanMade.setAvailable_copies(15);
		moreAvailableThanMade.setCopies_made(10);
		
		Painting wrongMeasures = obraGuardada;
		
		wrongMeasures.setAltura_cm(0);
		wrongMeasures.setLargo_cm(-1);
		
Painting wrongPricing = obraGuardada;
		
		wrongPricing.setPrice(10);
		wrongPricing.setPrice_copy(20);
		
		 Painting wrongDate = obraGuardada;
		 
		 wrongDate.setCreation_date(LocalDate.of(2025, 10, 17));
		 
		 given(paintingRepo.findById(id).get()).willReturn(obraGuardada);
		 
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.updatePaintingById(id, wrongMeasures);
		});
		

		assertThrows(InconsitentDataException.class, ()->{
			paintingService.updatePaintingById(id, wrongPricing);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.updatePaintingById(id, wrongPricing);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.updatePaintingById(id, wrongDate);
		});
		
		
		
		verify(paintingRepo, never()).save(any(Painting.class));
		

		
		
		
		
	}
	
	@DisplayName("Test para eliminar una obra de manera exitosa")
	@Test
	void deletePaintingByIdOk() {
		Integer id= 1;
		Painting paintingToDelete= paintingTestOk1;
		paintingToDelete.setId(id);
		given(paintingRepo.findById(id).get()).willReturn(paintingToDelete);
		
		willDoNothing().given(paintingRepo).deleteById(id);
		
		paintingService.deletePaintingByid(id);
		
		verify(paintingRepo, times(1)).deleteById(id);
		
	}
	
	@DisplayName("Test para intentar eliminar una obra que no existe")
	@Test
	void deletePaintingByIdNotfound() {
		
		Integer id= 1;
		Painting paintingToDelete= paintingTestOk1;
		paintingToDelete.setId(id);
		given(paintingRepo.findById(id)).willReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, ()->{
			paintingService.deletePaintingByid(id);
		});
		
		
		verify(paintingRepo, never()).deleteById(id);
		
	}
	

}




