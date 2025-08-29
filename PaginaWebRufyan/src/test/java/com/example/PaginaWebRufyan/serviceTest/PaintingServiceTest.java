package com.example.PaginaWebRufyan.serviceTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.PaginaWebRufyan.adapter.out.OriginalStockAdapter;
import com.example.PaginaWebRufyan.adapter.out.PaintingPriceManagerPersist;
import com.example.PaginaWebRufyan.Products.DTO.Painting.PaintingDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.Products.Categories.ProductsCategory;
import com.example.PaginaWebRufyan.Exceptions.InconsitentDataException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Image.Repository.ImageRepository;
import com.example.PaginaWebRufyan.Products.Repository.PaintingRepository;
import com.example.PaginaWebRufyan.Products.Service.PaintingService;

@ExtendWith(MockitoExtension.class)
public class PaintingServiceTest {
// generamos una instancia Mock de Paintingrepository 
	@Mock
	private PaintingRepository paintingRepo;
	
	@Mock
	private ImageRepository imageRepository; 
	
	@InjectMocks
	private PaintingService paintingService;
	
	Painting paintingTestOk1= new Painting();
	Painting paintingTestOk2= new Painting();
	Painting paintingTestOk3= new Painting();
	Painting paintingTestOk4= new Painting();
	Painting paintingTestOk5= new Painting();
	Painting paintingTestOk6= new Painting();
	Painting paintingTestOk7= new Painting();
	Painting paintingTestOk8= new Painting();
	Painting paintingTestOk9= new Painting();
	Painting paintingTestOk10= new Painting();
	Painting paintingTestOk11= new Painting();
	Painting paintingTestOk12= new Painting();
	Painting paintingTestOk13= new Painting();
	Painting paintingTestOk14= new Painting();
	Painting paintingTestOk15= new Painting();

	
	
	
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
		Image obra7Image = new Image();
		obra1Image.setUrl(HOSTLINK+"obra2.jpg");
		obra1Image.setId(1);
		Image obra8Image = new Image();
		obra2Image.setUrl(HOSTLINK+"obra3.jpg");
		obra2Image.setId(2);
		Image obra9Image = new Image();
		obra3Image.setUrl(HOSTLINK+"obra3.jpg");
		obra3Image.setId(3);
		Image obra10Image = new Image();
		obra4Image.setUrl(HOSTLINK+"obra5.png");
		obra4Image.setId(4);
		Image obra11Image = new Image();
		obra5Image.setUrl(HOSTLINK+"obra6.png");
		obra5Image.setId(5);
		Image obra12Image = new Image();
		obra6Image.setUrl(HOSTLINK+"obra7.png");
		obra6Image.setId(6);
		Image obra13Image = new Image();
		obra1Image.setUrl(HOSTLINK+"obra2.jpg");
		obra1Image.setId(1);
		Image obra14Image = new Image();
		obra2Image.setUrl(HOSTLINK+"obra3.jpg");
		obra2Image.setId(2);
		Image obra15Image = new Image();
		obra3Image.setUrl(HOSTLINK+"obra3.jpg");
		obra3Image.setId(3);
		Image obra16Image = new Image();
		obra4Image.setUrl(HOSTLINK+"obra5.png");
		obra4Image.setId(4);
		Image obra17Image = new Image();
		obra5Image.setUrl(HOSTLINK+"obra6.png");
		obra5Image.setId(5);
		Image obra18Image = new Image();
		obra6Image.setUrl(HOSTLINK+"obra7.png");
		obra6Image.setId(6);
		Image obra19Image = new Image();
		obra1Image.setUrl(HOSTLINK+"obra2.jpg");
		obra1Image.setId(1);
		Image obra20Image = new Image();
		obra2Image.setUrl(HOSTLINK+"obra3.jpg");
		obra2Image.setId(2);
		Image obra21Image = new Image();
		obra3Image.setUrl(HOSTLINK+"obra3.jpg");
		obra3Image.setId(3);
		Image obra22Image = new Image();
		obra4Image.setUrl(HOSTLINK+"obra5.png");
		obra4Image.setId(4);
		Image obra23Image = new Image();
		obra5Image.setUrl(HOSTLINK+"obra6.png");
		obra5Image.setId(5);
		Image obra24Image = new Image();
		obra6Image.setUrl(HOSTLINK+"obra7.png");
		obra6Image.setId(6);
		
		
		
		
		
		
		ProductsCategory pinturaCategory = ProductsCategory.builder().name("paintings").build();
		ProductsCategory digitalCategory = ProductsCategory.builder().name("digital").build();
		
		
		Painting paintingTestOk1 = Painting
									.builder()
									.name("1era Obra, datos correctos")
									.description("Obra con todos los datos correctos ")
									.creationDate(LocalDate.of(2020, 3, 25))
									.priceManager(new PaintingPriceManagerPersist())
									.style("Urbano")
									.isFavorite(true)
									.image(Set.of(obra1Image, obra2Image))
									.alturaCm(60)
									.largoCm(80)
									.medium("Aceite")
									.supportMaterial("Lienzo")
									.stockManager(new OriginalStockAdapter(5,10,true))
									.build();
		
		Painting paintingTestOk2 = Painting
				.builder()
				.name("2da Obra, datos correctos")
				.description("Obra con todos los datos correctos ")
				.creationDate(LocalDate.of(2020, 3, 25))
				.priceManager(new PaintingPriceManagerPersist())
				.style("Urbano")
				.isFavorite(true)
				.image(List.of(obra1Image, obra2Image))
				.alturaCm(60)
				.largoCm(80)
				.medium("Aceite")
				.supportMaterial("Lienzo")
				.stockManager(new OriginalStockAdapter(5,10,true))
				.build();


		
	
		
		
	}
	
	
	
	@DisplayName("Test para encontrar una Obra por id")
	@Test
	void findPaintingByIdTest() {
		int id = 1;
		Painting paintingResponse = paintingTestOk1;
		paintingResponse.setId(id);
		given(paintingRepo.findById(id)).willReturn(Optional.of(paintingResponse));
		
			PaintingDTO obraEncontrada =  paintingService.retrievePaintingById(id);
		
			assertThat(obraEncontrada).isNotNull();
			//assertThat(obraEncontrada.getId()).isGreaterThan(0);
			assertThat(obraEncontrada.getName()).isNotEmpty();
			assertThat(obraEncontrada.getName()).isNotBlank();

	}
	
	@DisplayName("Test para encontrar una Obra por nombre")
	@Test
	void findPaintingByNameTest() {
		int id =1;
		Painting paintingResponse= paintingTestOk1;
		paintingResponse.setId(id);
		String searchTerm = "1era Obra, datos correctos";
		
		given(paintingRepo.findByName("1era Obra, datos correctos"))
		.willReturn(Optional.of(paintingResponse));
		
			PaintingDTO obraEncontrada =  paintingService.retrievePaintingByName(searchTerm);

			assertThat(obraEncontrada).isNotNull();
			assertThat(obraEncontrada.getName()).isEqualTo(paintingResponse.getName());
			assertThat(obraEncontrada.getDescription()).isEqualTo(paintingResponse.getDescription());

	}
	
	@DisplayName("Test para guardar una obra de manera exitosa ")
	@Test
	void findPaintingTestOk() {
		
		int id =1;
		Painting paintingSavedResponse= paintingTestOk1;
		paintingSavedResponse.setId(id);
		
		given(paintingRepo.existsByName(paintingTestOk1.getName())).willReturn(false);
		given(paintingRepo.save(paintingTestOk1)).willReturn(paintingSavedResponse);
		
			PaintingDTO ObraGuardada =  paintingService.createPainting(paintingTestOk1);
		
			assertThat(ObraGuardada).isNotNull();

	}
	
	@DisplayName("Test para intentar crear una obra con datos inconsistentes")
	@Test
	void savePaintingTestInconsistentData() {
		
		int id = 1;

		 // Se agrega el id a obra guardada por que eso distinguiría a la obra que vamos
		//a subir de la obra que ya esta guardada
		
		Painting moreAvailableThanMade = Painting.builder().stockManager(new OriginalStockAdapter(15,10,true)).build();

		
		Painting wrongMeasureHeight = Painting.builder().alturaCm(Painting.minHeightCm).build();
		Painting wrongMeasureLarge = Painting.builder().largoCm(Painting.minLargeCm).build();



		Painting wrongPricing = Painting.builder().priceManager(new PaintingPriceManagerPersist(Painting.minPricePerCopy ,Painting.minPrice)).build();

		Painting wrongPricingInCopy = Painting.builder().priceManager(new PaintingPriceManagerPersist(Painting.minPricePerCopy.subtract(BigDecimal.ONE),Painting.minPrice.subtract(BigDecimal.ONE))).build();
		

		Painting wrongDate = Painting.builder().creationDate(LocalDate.now().plusDays(1L)).build();

		 
		Painting noImages = Painting.builder()
				.image(List.of()).build();
		 
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.createPainting(wrongMeasureHeight);
		});

		assertThrows(InconsitentDataException.class, ()->{
			paintingService.createPainting(wrongMeasureLarge);
		});

		assertThrows(InconsitentDataException.class, ()->{
			paintingService.createPainting( wrongPricing);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.createPainting( wrongPricing);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.createPainting(wrongDate);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.createPainting(noImages);
		});
		
		
		
		
		
		verify(paintingRepo, never()).save(any(Painting.class));
		

		
	}
	
	@DisplayName("Test para encontrar las obras favoritas")
	@Test
	void findFavoritesPaintingTest() {
		
		Painting obraFavorita = Painting.builder().isFavorite(true).build();
		Example<Painting> ejemploObraFavorita = Example.of(obraFavorita);
		given(paintingRepo.findAll(ejemploObraFavorita))
		.willReturn(List.of(paintingTestOk1, paintingTestOk2));
		
			List<PaintingDTO> ObrasFavoritas =  paintingService.findFavoritePaintings();
		
			assertThat(ObrasFavoritas).isNotNull();
			assertThat(ObrasFavoritas).isNotEmpty();
			
	}

	@DisplayName("Test para actualizar una obra de la manera correcta")
	@Test
	void  updatePaintingOkTest() {
		
		Painting responsePainting = paintingTestOk1;
		Integer id=1;
		responsePainting.setId(id);
		
		given(paintingRepo.findById(id)).willReturn(Optional.of(responsePainting));
		Painting updatedPainting = responsePainting;
		String newName = "Un nuevo amanecer"; 
		updatedPainting.setName(newName);
		given(paintingRepo.save(updatedPainting)).willReturn(updatedPainting);
		
		PaintingDTO updatedResponse = paintingService.updatePaintingById(id,updatedPainting);
		
		assertThat(updatedResponse).isNotNull();
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
	
	@DisplayName("Test para intentar actualizar una obra que tiene datos inconsistentes ")
	@Test
	void updatePaintingInconsistentDataTest() {
		
		Integer id = 1;
		Painting obraGuardada= paintingTestOk1;
		 // Se agrega el id a obra guardada por que eso distinguiría a la obra que
		//vamos a subir de la obra que ya esta guardada
		
		obraGuardada.setId(id);

		
		Painting moreAvailableThanMade = Painting.builder().stockManager(new OriginalStockAdapter(15,10)).build();

		
		Painting wrongMeasureHeigth = Painting.builder().alturaCm(Painting.minHeightCm-1).build();
		Painting wrongMeasureLarge = Painting.builder().largoCm(Painting.minLargeCm-1).build();
		
Painting wrongPricingPerCopy = Painting.builder()
		.priceManager(new PaintingPriceManagerPersist(Painting.minPricePerCopy.subtract(BigDecimal.ONE), Painting.minPrice)).build();

		Painting wrongPricingPerOriginal = Painting.builder()
				.priceManager(new PaintingPriceManagerPersist(Painting.minPricePerCopy, Painting.minPrice.subtract(BigDecimal.ONE))).build();


		Painting wrongDate = Painting.builder()
				.creationDate(LocalDate.now().plusDays(1L))
				.build();
		 
		 Painting noImages = Painting.builder()
				 .image(List.of())
				 .build();

		 given(paintingRepo.findById(id)).willReturn(Optional.of(any(Painting.class)));
		 
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.updatePaintingById(id, wrongMeasureHeigth);
		});
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.updatePaintingById(id, wrongMeasureLarge);
		});

		assertThrows(InconsitentDataException.class, ()->{
			paintingService.updatePaintingById(id, wrongPricingPerCopy);
		});

		assertThrows(InconsitentDataException.class, ()->{
			paintingService.updatePaintingById(id, wrongPricingPerOriginal);
		});


		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.updatePaintingById(id, wrongDate);
		});
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.updatePaintingById(id, noImages);
		});
		
		
		
		verify(paintingRepo, never()).save(any(Painting.class));
		

	}
	
	@DisplayName("Test para eliminar una obra de manera exitosa")
	@Test
	void deletePaintingByIdOk() {
		Integer id= 1;
		Painting paintingToDelete= paintingTestOk1;
		paintingToDelete.setId(id);
		//given(paintingRepo.findById(id)).willReturn(Optional.of(paintingToDelete));
		
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
	
	@DisplayName("Test para intentar encontrar una obra por parte del nombre ")
	@Test
	void findByNamePartTest () {
		
		
		
		
	}
	

}




