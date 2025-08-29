package com.example.PaginaWebRufyan.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import com.example.PaginaWebRufyan.adapter.out.PaintingPriceManagerPersist;
import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Categories.ProductsCategory;
import com.example.PaginaWebRufyan.Products.DTO.Product.ProductDTO;
import com.example.PaginaWebRufyan.Products.DTO.Product.ProductUpdateRegisterDTO;
import com.example.PaginaWebRufyan.Products.Entity.BodyClothing;
import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.Products.Enums.ProductsEnum;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.Image.Service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PaginaWebRufyan.Exceptions.InconsitentDataException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Products.Categories.ProductsCategoryRepository;
import com.example.PaginaWebRufyan.Products.Repository.ProductsRepository;
import com.example.PaginaWebRufyan.Products.Service.ProductService;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {


	@InjectMocks
	private ProductService productService;
	@Mock
	private ImageService imageService;
	@Mock
	private ProductsRepository productRepo;
	@Mock
	private ProductsCategoryRepository productCategoryRepo;
	
	
	
	 
	//private Product productTest1 = new Painting();

	//private Product productTest2= new Product();

	//private Product productTest3= new Product();
	
	private ProductsCategory cupCategorySaved = ProductsCategory.builder()
			.name("Arte urbano")
			.id(2)
			.build();
	
	private int lowestPrice = 200;
	private int lowestAmountOfImages = 1; // puede ser una o al menos dos para 2 perspectivas 
	private int maxAmountOfImages =5;
	final String HOSTLINK= "http://localhost:8080/static/";

	ProductUpdateRegisterDTO wrongPrice= new ProductUpdateRegisterDTO();
	ProductUpdateRegisterDTO wrongPricePerCopy= new ProductUpdateRegisterDTO();
	ProductUpdateRegisterDTO wrongDate= new ProductUpdateRegisterDTO();
	ProductUpdateRegisterDTO noImages = new ProductUpdateRegisterDTO();
	Image product1Image = new Image();
	Image product2Image = new Image();
	Image product3Image = new Image();
	Image product4Image = new Image();
	Image product5Image = new Image();
	Image product6Image = new Image();
	
	@BeforeEach
	void setUp (){
		
		ProductsCategory pinturaCategory = ProductsCategory.builder()
				.name("paintings")
				.build();
		ProductsCategory pinturaCategorySaved = ProductsCategory.builder()
				.name("paintings")
				.id(1)
				.build();
		ProductsCategory cupCategory = ProductsCategory.builder()
				.name("Custom cup")
				.build();
		

		ProductsCategory accesoriesCategory= ProductsCategory.builder().
				name("accesories")
				.build();
		ProductsCategory accesoriesCategorySaved= ProductsCategory.builder().
				name("accesories")
				.id(3)
				.build();
		
		
		

		//Agregamos imagenes 
		
		product1Image.setUrl(HOSTLINK+"obra2.jpg");
		product1Image.setId(1);
			
		
		product2Image.setUrl(HOSTLINK+"obra3.jpg");
		product2Image.setId(2);	
		
		product3Image.setId(3);
		product3Image.setUrl(HOSTLINK+"obra3.jpg");


				
		
		product4Image.setUrl(HOSTLINK+"obra5.png");
		product4Image.setId(4);		
		
		product5Image.setUrl(HOSTLINK+"obra6.png");
		product5Image.setId(5);
		
		product6Image.setUrl(HOSTLINK+"obra7.png");
		product6Image.setId(6);
		
		LinkedHashMap<String, String> cupAdditionalFeatures = new LinkedHashMap<String, String>();
		
		cupAdditionalFeatures.put("Material", "Ceramic");
		cupAdditionalFeatures.put("Capacity", "300ml");
		cupAdditionalFeatures.put("Heat reaction", "No");
		
		
		LinkedHashMap<String, String> accesoriesAdditionalFeatures = new LinkedHashMap<String, String>();
		
		accesoriesAdditionalFeatures.put("Material", "Wood");
		accesoriesAdditionalFeatures.put("Technique", "Pyrography");

		Map<String,Object> wrongPriceMap = new HashMap<>();
		wrongPriceMap.put("pricePerCopy", Painting.minPricePerCopy);
		wrongPriceMap.put("pricePerOriginal", Painting.minPrice.subtract(BigDecimal.ONE));

		Map<String,Object> wrongPricePerCopyMap = new HashMap<>();
		wrongPriceMap.put("pricePerCopy",Painting.minPricePerCopy.subtract(BigDecimal.ONE));
		wrongPriceMap.put("pricePerOriginal", Painting.minPrice);

		Map<String,Object> paintingStock = new HashMap<>();
		paintingStock.put("stockCopies",10);
		paintingStock.put("isOriginalAvailable",true);
		paintingStock.put("copiesMade", 15);
		paintingStock.put("isInCart",false);

		Map<String,Object> wrongPaintingStock = new HashMap<>();
		wrongPaintingStock.put("stockCopies",10);
		wrongPaintingStock.put("isOriginalAvailable",true);
		wrongPaintingStock.put("copiesMade", 15);
		wrongPaintingStock.put("isInCart",false);


		ProductUpdateRegisterDTO wrongPrice = ProductUpdateRegisterDTO.builder()
				.type(ProductsEnum.PAINTING)
				.priceManage(wrongPriceMap)
				.oldImages(List.of(new Image()))
				.stock(paintingStock)
				.build();

		ProductUpdateRegisterDTO wrongPricePerCopy = ProductUpdateRegisterDTO.builder()
				.type(ProductsEnum.PAINTING)
				.oldImages(List.of(new Image()))
				.priceManage(wrongPricePerCopyMap)
				.stock(paintingStock)
				.build();



		ProductUpdateRegisterDTO wrongDate = ProductUpdateRegisterDTO.builder()
				.creationDate(LocalDate.now().plusDays(1l))
				.stock(paintingStock)
				.build();
		ProductUpdateRegisterDTO noImages = ProductUpdateRegisterDTO.builder().type(ProductsEnum.PAINTING).oldImages(List.of()).build();


		// Producto con una categoría anteriormente guardada
		/*	
		productTest1.setCategory(cupCategorySaved);
			productTest1.setCreation_date(LocalDate.of(2022, 8, 20));
			productTest1.setDescription("Customized cup with digital art made by Rufyan");
			productTest1.setFavorite(true);
			productTest1.setImage(List.of(product1Image, product2Image));
			productTest1.setName("Digital society cup");
			productTest1.setPrice(600);
			productTest1.setStyle("Expresionism");
			productTest1.setAdittionalFeatures(cupAdditionalFeatures);
		*/

		
		
	}
	
	@DisplayName("Test para listar los productos")
	@Test
	void findAllProductsTestOk() {
		BigDecimal copyPrice = BigDecimal.valueOf(350);
		BigDecimal originalPrice= BigDecimal.valueOf(1800);
		BigDecimal clothePrice=  BigDecimal.valueOf(600);

		Product painting1 = new Painting();
		painting1.setName("Pintura 1");
		painting1.setPriceManagerPersist(new PaintingPriceManagerPersist(copyPrice,originalPrice));

		Product painting2 = new Painting();
		painting2.setName("Pintura 2");

		Product clothing = new BodyClothing();
		clothing.setName("Sudadera");

		given(productRepo.findAll()).willReturn( List.of(painting1,painting2,clothing));
		List<ProductDTO> allProducts = productService.retrieveAllProducts();


		assertThat(allProducts.size()).isGreaterThan(0);
		assertThat(allProducts).contains(new ProductDTO(painting1));
		assertThat(allProducts).contains(new ProductDTO(painting2));
		assertThat(allProducts).contains(new ProductDTO(clothing));

		assertThat(new ProductDTO(painting1).getPrice()).isInstanceOf(Map.class);



		
		
		
	}
	
	@DisplayName("Test para encontrar productos por match en el nombre ")
	@Test
	void findProductsByNamePartTestOk() {
		
		String searchTerm= "py";
		Product productResponse2 = new Painting();
		productResponse2.setId(2);
		Product productResponse3 = new Painting();
		productResponse3.setId(3);
		
		given(productRepo.findByNameContainingIgnoreCase(searchTerm)).willReturn(List.of(productResponse2,productResponse3));
		
		List<Product> matchedProducts = productService.retrieveProductsByNameContainging(searchTerm);
		
		assertThat(matchedProducts.size()).isGreaterThan(0);
		assertThat(matchedProducts).contains(productResponse2);
		assertThat(matchedProducts).contains(productResponse3);
		
	}
	
	@DisplayName("Test para buscar un producto por su id de manera exitosa ")
	@Test
	void findProductByIdTestOk () {
	int id = 1;
	Product painting1 = new Painting();
	painting1.setId(id);
	painting1.setName("pintura 1");
	ProductDTO productResponse1= new ProductDTO(painting1);
	given(productRepo.findById(id)).willReturn(Optional.of(painting1));
	
	ProductDTO foundProduct = productService.retrieveProductById(id);
	
	assertThat(foundProduct).isNotNull();
	assertThat(foundProduct).isEqualTo(productResponse1);
	assertThat(foundProduct.getName()).isEqualTo(productResponse1.getName());

		
		
	}
	
	@DisplayName("Test para intentar buscar un producto por su id, no encontrado ")
	@Test
	void findProductByIdNotFoundTest () {
		int id = 12;
		
		given(productRepo.findById(id)).willReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, ()->{
			productService.retrieveProductById(id);
		});
		
		verify(productRepo, times(1)).findById(id);
		
		
		
	}
	
	
	@DisplayName("Test para buscar un producto por su nombre de manera exitosa")
	@Test
	void findProductByNameTestOk() {
			int id = 1;
			String searchParam = "night";
			Product painting1 = new Painting();
			painting1.setName("the last night");
			//ProductDTO productResponse1= new ProductDTO(painting);

			
			given(productRepo.findByName(searchParam)).willReturn(Optional.of(painting1));
			
			ProductDTO foundProduct = productService.retrieveProductByName(searchParam);

			
			assertThat(foundProduct).isNotNull();
			assertThat(foundProduct.getName()).isEqualTo(painting1.getName());

				
		
	}
	
	@DisplayName("Test para intentar buscar un producto por su nombre, no encontrado")
	@Test
	void findProductByNameNotFoundTest() {
		String nameToSearch = "lkadlkad";
		
		given(productRepo.findByName(nameToSearch)).willReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, ()->{
			productService.retrieveProductByName(nameToSearch);
		});
		
		verify(productRepo, times(1)).findByName(nameToSearch);
		
		
	}
	
	@DisplayName("Test para crear un producto de manera exitosa ")
	@Test
	void saveProducTestOk() {
		int id = 1;
		int idImage1= 1;
		int idImage2 = idImage1+1;
		String responseName= "nombreGenerico";
		Product productTest1 = Painting.builder().id(id)
				.priceManager(new PaintingPriceManagerPersist(Painting.minPricePerCopy, Painting.minPrice))
				.name(responseName)
				.build();

			

		given(productRepo.save(productTest1)).willReturn(productTest1);
		//given(imageRepo.save(null))
		
		//System.out.println(productResponse1);
		
		ProductDTO savedProduct = productService.saveProduct(productTest1);
		
		assertThat(savedProduct).isNotNull();
		assertThat(savedProduct.getName()).isNotNull();
		assertThat(savedProduct.getName()).isEqualTo(responseName);
		assertThat(savedProduct.getClass()).isEqualTo(ProductDTO.class);
		
		
	}
	
	@DisplayName("test para intentar guardar un producto con información inconsistente")
	@Test
	void saveProductInconsitentDataTest() {


// wrong priece
/*
		Product wrongCopyPriceProduct = Painting.builder()
				.name("wrong price")
				.priceManager(new PaintingPriceManager(Painting.minPricePerCopy.subtract(new BigDecimal("0.1")),Painting.minPrice))
				.build();

		Product wrongPriceProduct = Painting.builder()
				.name("wrong price")
				.priceManager(new PaintingPriceManager(Painting.minPricePerCopy,Painting.minPrice.subtract(new BigDecimal("0.1"))))
				.build();
		
		Product wrongDateProduct = Painting.builder()
				.creationDate(LocalDate.now().plusWeeks(1))
				.build();

		
		Product noImagesProduct = Painting.builder()
				.image(List.of())
				.build();

 */


		
		assertThrows(InconsitentDataException.class, ()->{
			productService.createProduct(wrongPrice);
		});

		assertThrows(InconsitentDataException.class, ()->{
			productService.createProduct(wrongPricePerCopy);
		});

		assertThrows(InconsitentDataException.class, ()->{
			productService.createProduct(wrongDate);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			productService.createProduct(noImages);
		});
		
		
		verify(productRepo, never()).save(any(Product.class));
		
		
		
	}
	
	@DisplayName("test para intentar actualizar un producto con información inconsistente, precio incorrecto, fecha incorrecta, sin imagenes")
	@Test
	void updateProductInconsistentDataTest() {

		//doNothing().when(imageService).deleteAllImages(any());
		int id = 1;
		Product productResponse = Painting.builder().id(id).image(List.of(new Image())).build();

		//paintings
		



		given(productRepo.findById(id)).willReturn(Optional.of(productResponse));

		//given(imageService.processImages(any())).willReturn(List.of(new Image(),new Image()));

		

		assertThrows(InputMismatchException.class, ()->{
			productService.updateProductById(id,wrongPrice);
		});

		assertThrows(InputMismatchException.class, ()->{
			productService.updateProductById(id,wrongPricePerCopy);
		});
		
		assertThrows(InputMismatchException.class, ()->{
			productService.updateProductById(id, wrongDate);
		});
		assertThrows(InputMismatchException.class, ()->{
			productService.updateProductById(id, noImages);
		});

		
		verify(productRepo, never()).save(any(Product.class));
		
		
		
	}
	
	
	@DisplayName("Test para intentar actualizar un producto que no existe ")
	@Test
	void updateNotFoundProductTest() {
		int id =1;
		String newName = "Pyrospoon";
		Product responseProduct = Painting.builder().id(id)
				.name(newName)
				.image(List.of())
				.build();
		responseProduct.setId(id);
		responseProduct.setName(newName);
		List<Image> newImageList = new ArrayList<>(responseProduct.getImage());
		newImageList.add(product3Image);
		responseProduct.setImage(newImageList);

		given(productRepo.findById(id)).willReturn(Optional.empty());		
		
		
		assertThrows(ResourceNotFoundException.class, () -> {
			productService.updateProductById(responseProduct.getId(), any());
		});
			verify(productRepo, never()).save(any(Product.class));

	}
	
	
	
	
	
}
