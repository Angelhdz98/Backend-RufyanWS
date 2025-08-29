package com.example.PaginaWebRufyan.serviceTest;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.PaginaWebRufyan.adapter.out.PaintingPriceManagerPersist;
import com.example.PaginaWebRufyan.Image.DTO.ImageDTO;
import com.example.PaginaWebRufyan.Products.Entity.Painting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.Products.Categories.ProductsCategory;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Image.Repository.ImageRepository;
import com.example.PaginaWebRufyan.Image.Service.ImageService;

@ExtendWith(MockitoExtension.class)
public class  ImageServiceTests {

	@Mock
	private ImageRepository imageRepo;
	
	@InjectMocks
	private ImageService imageService;
	
	Image imageTest1 = new Image();
	Image imageTest2 = new Image();
	final String HOSTLINK= "http://localhost:8080/static/";
	private Product productTest1 = new Painting();
	
	
	ProductsCategory cupCategorySaved = ProductsCategory.builder()
			.name("bodyClothes")
			.id(2)
			.build();
	
	
	LinkedHashMap<String, String> cupAdditionalFeatures = new LinkedHashMap<String, String>();


	
	@BeforeEach
	void setUp() {
		
		
		cupAdditionalFeatures.put("Material", "Ceramic");
		cupAdditionalFeatures.put("Capacity", "300ml");
		cupAdditionalFeatures.put("Heat reaction", "No");
		
		
		productTest1= Painting.builder()

				.creationDate(LocalDate.of(2022, 8, 20))
				.description("Customized cup with digital art made by Rufyan")
				.isFavorite(true)
				.name("Digital society cup")
				.priceManager(new PaintingPriceManagerPersist(Painting.minPricePerCopy,Painting.minPrice))
				.style("Expresionism")
				.additionalFeatures(cupAdditionalFeatures)
				.build();
		
		// el imageService recibe las imagenes sin guardar y las regresa guardadas (entran sin id)
		imageTest1 = Image.builder()
					.url(HOSTLINK + "obra2.jpg")
					.productName(productTest1.getName())
					.build();
		
		imageTest2 = Image.builder()
					.url(HOSTLINK + "obra3.jpg")
					.productName(productTest1.getName()).build();
		
		productTest1.setImage(List.of(imageTest1, imageTest2));
		
		
		
		
	}
	@DisplayName("Test para guardar una imagen de manera exitosa")
	@Test
	void saveImageTestOk() {
		int id = 1; 
		Image imageResponse = imageTest1;
		imageResponse.setId(1);
		
		given(imageRepo.save(imageTest1)).willReturn(imageResponse);
		Image imagenGuardada= imageService.saveImage(imageTest1);
		
		assertThat(imagenGuardada.getId()).isGreaterThan(0);
		assertThat(imagenGuardada).isNotNull();
		
		
		
	}
	

	
	@Test
	@DisplayName("Test para encontrar todas las imagenes ")
	void findAllImagesTestOk() {
		
		
		given(imageRepo.findAll()).willReturn(List.of(imageTest1,imageTest2));
		
		List<ImageDTO> allImages= imageService.findAllImages();
		
		assertThat(allImages.size()).isGreaterThan(0);
		assertThat(allImages).contains(new ImageDTO(imageTest1));
		assertThat(allImages).contains(new ImageDTO(imageTest1));
		
		
	}
	
	@DisplayName("Test para encontrar una imagen por el id de manera exitosa")
	@Test
	void findImageByIdTestOk() {
		int idToSearch= 1;
		
		given(imageRepo.findById(idToSearch)).willReturn(Optional.of(imageTest1));
		
		ImageDTO foundImage = imageService.retrieveImageById(idToSearch);

		
		assertThat(foundImage).isNotNull();
		assertThat(foundImage).isEqualTo(imageTest1);
		
	}
	
	@DisplayName("Test para buscar una imagen por un ID que no existe")
	@Test
	void findImageByIdNotFoundTest() {
		int idToSearch= 20;
		given(imageRepo.findById(idToSearch)).willReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class,()->{
			ImageDTO Image =imageService.retrieveImageById(idToSearch);
		});


		

		
	}
	
	
	@DisplayName("Test para actualizar una imagen de manera exitosa")
	@Test
	void updateImageByIdTestOK() {
		
		Image imageResponse= imageTest1;
		imageResponse.setId(1);
		int idToUpdate = 1;
		Image updatedImage = imageResponse; 
		String newUrl = HOSTLINK+ "obra.jpg";
		updatedImage.setUrl(newUrl);
		
		given(imageRepo.findById(idToUpdate)).willReturn(Optional.of(imageTest1));
		
		given(imageRepo.save(updatedImage)).willReturn(updatedImage);
		
		Image responseUpdatedImage =  imageService.updateImageById(idToUpdate, updatedImage);
		
		assertThat(responseUpdatedImage).isEqualTo(updatedImage);
		assertThat(responseUpdatedImage.getUrl()).isEqualTo(newUrl);
		
	}
	
	@DisplayName("Test para intentar actualizar una imagen que no existe")
	@Test
	void updateImageNotFoundTest() {
		
		int idToUpdate = 100;
		Image imageUpdated = imageTest1;
		
		given(imageRepo.findById(idToUpdate)).willReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, ()->{
			imageService.updateImageById(idToUpdate, imageUpdated);
		});
			
		verify(imageRepo, never()).save(any(Image.class));
		
	}
	
	@DisplayName("Test para eliminar una imagen de manera exitosa")
	@Test
	void deleteImageByIdTestOk() {
		int idToDelete = 1;
		Image imageResponse = imageTest1;
		imageResponse.setId(idToDelete);
		given(imageRepo.findById(idToDelete)).willReturn(Optional.of(imageResponse));
		
		
		willDoNothing().given(imageRepo).deleteById(idToDelete);
		
		verify(imageRepo, times(1)).deleteById(idToDelete);
		
		
		
	}
	
	@DisplayName("Test para intentar eliminar una obra que no existe")
	@Test
	void deleteImageByIdNotFoudTest() {
		int idToDelete = 15;
		given(imageRepo.findById(idToDelete)).willReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, ()->{
			imageService.deleteImageById(idToDelete);
		});
		
		verify(imageRepo, never()).save(any(Image.class));
		
		
	}
	
	
	
	
}
