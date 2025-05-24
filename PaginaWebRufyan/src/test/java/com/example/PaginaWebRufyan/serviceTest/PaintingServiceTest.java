package com.example.PaginaWebRufyan.serviceTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
				.name("2da Obra, datos correctos")
				.description("Obra con todos los datos correctos ")
				.creationDate(LocalDate.of(2020, 3, 25))
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
		Painting paintingTest3 = Painting.builder()
		        .name("3era Obra, datos correctos")
		        .description("Obra realista con tonos cálidos")
		        .creationDate(LocalDate.of(2022, 1, 15))
		        .price(2000)
		        .category(pinturaCategory)
		        .style("Realista")
		        .favorite(true)
		        .image(List.of(obra4Image, obra5Image))
		        .altura_cm(80)
		        .largo_cm(100)
		        .medium("Acuarela")
		        .support_material("Cartón")
		        .available_copies(10)
		        .copies_made(15)
		        .price_copy(400)
		        .build();

		Painting paintingTest4 = Painting.builder()
		        .name("4ta Obra, obra minimalista")
		        .description("Pintura de estilo minimalista")
		        .creationDate(LocalDate.of(2020, 7, 20))
		        .price(800)
		        .category(pinturaCategory)
		        .style("Minimalista")
		        .favorite(true)
		        .image(List.of(obra6Image))
		        .altura_cm(40)
		        .largo_cm(50)
		        .medium("Óleo")
		        .support_material("Papel")
		        .available_copies(5)
		        .copies_made(10)
		        .price_copy(150)
		        .build();

		Painting paintingTest5 = Painting.builder()
		        .name("5ta Obra, impresionista")
		        .description("Obra destacada con estilo impresionista")
		        .creationDate(LocalDate.of(2019, 12, 5))
		        .price(5000)
		        .category(pinturaCategory)
		        .style("Impresionista")
		        .favorite(false)
		        .image(List.of(obra7Image, obra8Image))
		        .altura_cm(120)
		        .largo_cm(150)
		        .medium("Tempera")
		        .support_material("Lienzo")
		        .available_copies(2)
		        .copies_made(3)
		        .price_copy(1000)
		        .build();
		
		Painting paintingTest6 = Painting.builder()
		        .name("6ta Obra, monocromática")
		        .description("Pintura monocromática en blanco y negro")
		        .creationDate(LocalDate.of(2023, 3, 3))
		        .price(3000)
		        .category(pinturaCategory)
		        .style("Monocromático")
		        .favorite(true)
		        .image(List.of(obra9Image))
		        .altura_cm(50)
		        .largo_cm(70)
		        .medium("Carboncillo")
		        .support_material("Tela")
		        .available_copies(4)
		        .copies_made(6)
		        .price_copy(250)
		        .build();

		Painting paintingTest7 = Painting.builder()
		        .name("7ma Obra, surrealista")
		        .description("Obra surrealista con detalles oníricos")
		        .creationDate(LocalDate.of(2018, 6, 12))
		        .price(3500)
		        .category(pinturaCategory)
		        .style("Surrealista")
		        .favorite(false)
		        .image(List.of(obra10Image, obra11Image))
		        .altura_cm(100)
		        .largo_cm(120)
		        .medium("Acrílico")
		        .support_material("Lienzo")
		        .available_copies(1)
		        .copies_made(2)
		        .price_copy(800)
		        .build();

		Painting paintingTest8 = Painting.builder()
		        .name("8va Obra, digital")
		        .description("Obra digital impresa")
		        .creationDate(LocalDate.of(2021, 11, 10))
		        .price(1200)
		        .category(digitalCategory)
		        .style("Futurista")
		        .favorite(true)
		        .image(List.of(obra12Image))
		        .altura_cm(60)
		        .largo_cm(90)
		        .medium("Digital")
		        .support_material("Impresión")
		        .available_copies(15)
		        .copies_made(20)
		        .price_copy(100)
		        .build();

		Painting paintingTest9 = Painting.builder()
		        .name("9na Obra, paisaje")
		        .description("Pintura de paisaje natural")
		        .creationDate(LocalDate.of(2020, 8, 5))
		        .price(1800)
		        .category(pinturaCategory)
		        .style("Paisajismo")
		        .favorite(true)
		        .image(List.of(obra13Image, obra14Image))
		        .altura_cm(80)
		        .largo_cm(120)
		        .medium("Óleo")
		        .support_material("Lienzo")
		        .available_copies(6)
		        .copies_made(8)
		        .price_copy(400)
		        .build();

		Painting paintingTest10 = Painting.builder()
		        .name("10ma Obra, expresionista")
		        .description("Obra de estilo expresionista")
		        .creationDate(LocalDate.of(2022, 2, 20))
		        .price(2500)
		        .category(pinturaCategory)
		        .style("Expresionista")
		        .favorite(false)
		        .image(List.of(obra15Image))
		        .altura_cm(70)
		        .largo_cm(100)
		        .medium("Acrílico")
		        .support_material("Cartón")
		        .available_copies(3)
		        .copies_made(5)
		        .price_copy(500)
		        .build();

		Painting paintingTest11 = Painting.builder()
		        .name("11va Obra, abstracto colorido")
		        .description("Pintura abstracta con colores vivos")
		        .creationDate(LocalDate.of(2019, 10, 15))
		        .price(2200)
		        .category(pinturaCategory)
		        .style("Abstracto")
		        .favorite(true)
		        .image(List.of(obra16Image))
		        .altura_cm(90)
		        .largo_cm(110)
		        .medium("Óleo")
		        .support_material("Madera")
		        .available_copies(7)
		        .copies_made(9)
		        .price_copy(450)
		        .build();

		Painting paintingTest12 = Painting.builder()
		        .name("12va Obra, retrato")
		        .description("Pintura de un retrato clásico")
		        .creationDate(LocalDate.of(2023, 4, 1))
		        .price(4000)
		        .category(pinturaCategory)
		        .style("Retrato")
		        .favorite(false)
		        .image(List.of(obra17Image, obra18Image))
		        .altura_cm(100)
		        .largo_cm(150)
		        .medium("Acuarela")
		        .support_material("Lienzo")
		        .available_copies(2)
		        .copies_made(4)
		        .price_copy(900)
		        .build();

		Painting paintingTest13 = Painting.builder()
		        .name("13va Obra, técnica mixta")
		        .description("Obra con técnica mixta sobre madera")
		        .creationDate(LocalDate.of(2020, 6, 18))
		        .price(3200)
		        .category(pinturaCategory)
		        .style("Técnica Mixta")
		        .favorite(true)
		        .image(List.of(obra19Image))
		        .altura_cm(75)
		        .largo_cm(100)
		        .medium("Mixta")
		        .support_material("Madera")
		        .available_copies(5)
		        .copies_made(10)
		        .price_copy(350)
		        .build();

		Painting paintingTest14 = Painting.builder()
		        .name("14va Obra, cubismo")
		        .description("Obra de estilo cubista")
		        .creationDate(LocalDate.of(2018, 9, 30))
		        .price(2800)
		        .category(pinturaCategory)
		        .style("Cubismo")
		        .favorite(false)
		        .image(List.of(obra20Image))
		        .altura_cm(90)
		        .largo_cm(120)
		        .medium("Óleo")
		        .support_material("Lienzo")
		        .available_copies(4)
		        .copies_made(6)
		        .price_copy(600)
		        .build();

		Painting paintingTest15 = Painting.builder()
		        .name("15va Obra, hiperrealismo")
		        .description("Obra de estilo hiperrealista")
		        .creationDate(LocalDate.of(2021, 12, 25))
		        .price(5000)
		        .category(pinturaCategory)
		        .style("Hiperrealismo")
		        .favorite(true)
		        .image(List.of(obra21Image, obra22Image))
		        .altura_cm(100)
		        .largo_cm(130)
		        .medium("Óleo")
		        .support_material("Lienzo")
		        .available_copies(3)
		        .copies_made(4)
		        .price_copy(1200)
		        .build();


		
	
		
		
	}
	
	
	
	@DisplayName("Test para encontrar una Obra por id")
	@Test
	void findPaintingByIdTest() {
		int id = 1;
		Painting paintingResponse = paintingTestOk1;
		paintingResponse.setId(id);
		given(paintingRepo.findById(id)).willReturn(Optional.of(paintingResponse));
		
			Painting obraEncontrada =  paintingService.findById(id).get();
		
			assertThat(obraEncontrada).isNotNull();
			assertThat(obraEncontrada.getId()).isGreaterThan(0);
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
		
			Optional<Painting> optionalObraEncontrada =  paintingService.findPaintingByName(searchTerm);
			Painting obraEncontrada = optionalObraEncontrada.get();
			
			assertThat(optionalObraEncontrada).isNotNull();
			assertThat(optionalObraEncontrada).isNotEmpty();
			assertThat(obraEncontrada);

	}
	
	@DisplayName("Test para guardar una obra de manera exitosa ")
	@Test
	void findPaintingTestOk() {
		
		int id =1;
		Painting paintingSavedResponse= paintingTestOk1;
		paintingSavedResponse.setId(id);
		
		given(paintingRepo.existsByName(paintingTestOk1.getName())).willReturn(false);
		given(paintingRepo.save(paintingTestOk1)).willReturn(paintingSavedResponse);
		
			Painting ObraGuardada =  paintingService.save(paintingTestOk1);
		
			assertThat(ObraGuardada).isNotNull();

	}
	
	@DisplayName("Test para intentar crear una obra con datos inconsistentes")
	@Test
	void savePaintingTestInconsistentData() {
		
		int id = 1;
		Painting obraGuardada= paintingTestOk1;
		obraGuardada.setId(id);
		 // Se agrega el id a obra guardada por que eso distinguiría a la obra que vamos
		//a subir de la obra que ya esta guardada
		
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
		wrongDate.setCreationDate(LocalDate.of(2025, 10, 17));
		 
		Painting noImages = obraGuardada;
		noImages.setImage(List.of());
		 
		
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
		
		assertThrows(InconsitentDataException.class, ()->{
			paintingService.save(noImages);
		});
		
		
		
		
		
		verify(paintingRepo, never()).save(any(Painting.class));
		

		
	}
	
	@DisplayName("Test para encontrar las obras favoritas")
	@Test
	void findFavoritesPaintingTest() {
		
		Painting obraFavorita = Painting.builder().favorite(true).build();
		Example<Painting> ejemploObraFavorita = Example.of(obraFavorita);
		given(paintingRepo.findAll(ejemploObraFavorita))
		.willReturn(List.of(paintingTestOk1, paintingTestOk2));
		
			List<Painting> ObrasFavoritas =  paintingService.findFavoritePaintings();
		
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
	
	@DisplayName("Test para intentar actualizar una obra que tiene datos inconsistentes ")
	@Test
	void updatePaintingInconsistentDataTest() {
		
		Integer id = 1;
		Painting obraGuardada= paintingTestOk1;
		 // Se agrega el id a obra guardada por que eso distinguiría a la obra que
		//vamos a subir de la obra que ya esta guardada
		
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
		 
		 Painting noImages = obraGuardada;
		 noImages.setImage(List.of());
		 
		 wrongDate.setCreationDate(LocalDate.of(2025, 10, 17));
		 
		 given(paintingRepo.findById(id)).willReturn(Optional.of(obraGuardada));
		 
		
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




