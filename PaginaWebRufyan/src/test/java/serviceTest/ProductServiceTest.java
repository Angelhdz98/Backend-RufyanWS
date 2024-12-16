package serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import com.example.PaginaWebRufyan.Exceptions.InconsitentDataException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.ImageRepository;
import com.example.PaginaWebRufyan.Repository.ProductsCategoryRepository;
import com.example.PaginaWebRufyan.Repository.ProductsRepository;
import com.example.PaginaWebRufyan.Service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ImageRepository imageRepo;
	@Mock
	private ProductsRepository productRepo;
	@Mock
	private ProductsCategoryRepository productCategoryRepo;
	@InjectMocks
	private ProductService productService;
	
	
	
	
	 
	private Product productTest1 = new Product();

	private Product productTest2= new Product();

	private Product productTest3= new Product();
	
	private ProductsCategory cupCategorySaved = ProductsCategory.builder()
			.name("Custom cup")
			.id(2)
			.build();
	
	private int lowestPrice = 200;
	private int lowestAmountOfImages = 1; // puede ser una o al menos dos para 2 perspectivas 
	private int maxAmountOfImages =5;
	final String HOSTLINK= "http://localhost:8080/static/";
	

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
			productTest1= Product.builder()
							.category(cupCategorySaved)
							.creationDate(LocalDate.of(2022, 8, 20))
							.description("Customized cup with digital art made by Rufyan")
							.favorite(true)
							.image(List.of(product1Image, product2Image))
							.name("Digital society cup")
							.price(600)
							.style("Expresionism")
							.adittionalFeatures(cupAdditionalFeatures)
							.build();
		// Producto con una categoría totalmente nueva 
			
			productTest2.setCategory(accesoriesCategory);
			productTest2.setCreationDate(LocalDate.of(2022, 8, 20));
			productTest2.setDescription("Custom large wooden spoon with flowers, branches and other nature elements");
			productTest2.setFavorite(true);
			productTest2.setImage(List.of(product4Image, product5Image, product6Image));
			productTest2.setName("Pyrography spoon");
			productTest2.setPrice(250);
			productTest2.setStyle("Natural clasic");
			productTest2.setAdittionalFeatures(accesoriesAdditionalFeatures);
			
			productTest3.setCategory(accesoriesCategory);
			productTest3.setCreationDate(LocalDate.of(2029, 1, 22));
			productTest3.setDescription("cup with an ditital art made by Rufyan");
			productTest3.setFavorite(true);
			productTest3.setImage(List.of(product4Image, product5Image, product6Image));
			productTest3.setName("Py the reveal cup");
			productTest3.setPrice(350);
			productTest3.setStyle("Expresionism");
			productTest3.setAdittionalFeatures(accesoriesAdditionalFeatures);
				
		
		
	}
	
	@DisplayName("Test para tener todos los usuarios de manera exitosa")
	@Test
	void findAllProductsTestOk() {
		Product productResponse1 = productTest1;
		productResponse1.setId(1);
		Product productResponse2 = productTest2;
		productResponse2.setId(2);
		Product productResponse3 = productTest3;
		productResponse3.setId(3);
		
		given(productRepo.findAll()).willReturn(List.of(productResponse1,productResponse2, productResponse3));
		
		List<Product> allProducts = productService.retrieveAllProducts();
		
		assertThat(allProducts.size()).isGreaterThan(0);
		assertThat(allProducts).contains(productResponse1);
		assertThat(allProducts).contains(productResponse2);
		assertThat(allProducts).contains(productResponse3);
		
		
		
	}
	
	@DisplayName("Test para encontrar productos por match en el nombre ")
	@Test
	void findProductsByNamePartTestOk() {
		
		String searchTerm= "py";
		Product productResponse2 = productTest2;
		productResponse2.setId(2);
		Product productResponse3 = productTest3;
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
	Product productResponse1= productTest1;
	productResponse1.setId(id);
	
	given(productRepo.findById(id)).willReturn(Optional.of(productResponse1));
	
	Optional<Product> foundProduct = productService.retrieveProductById(id);
	
	assertThat(foundProduct).isNotNull();
	assertThat(foundProduct.get()).isEqualTo(productResponse1);
		
		
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
			Product productResponse1= productTest1;
			productResponse1.setId(id);
			String nameSearch= productResponse1.getName();
			
			given(productRepo.findByName(nameSearch)).willReturn(Optional.of(productResponse1));
			
			Optional<Product> optionalfoundProduct = productService.retrieveProductByName(nameSearch);
			Product foundProduct = optionalfoundProduct.get();
			
			
			assertThat(foundProduct).isNotNull();
			assertThat(foundProduct).isEqualTo(productResponse1);
				
		
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
		
		
		/*
		 * Image image1Response = product1Image;
		image1Response.setId(idImage1);
		Image image2Response = product2Image;
		image2Response.setId(idImage2);
		
		*/
		
		Product productResponse1 = productTest1;
		productResponse1.setId(id);
			
		//given(imageRepo.save(product1Image)).willReturn(image1Response);
		//given(imageRepo.save(product2Image)).willReturn(image2Response);
		System.out.println(cupCategorySaved);
		//given(productCategoryRepo.findByName(cupCategorySaved.getName())).willReturn(Optional.of(cupCategorySaved));
		given(productRepo.save(productTest1)).willReturn(productResponse1);
		//given(imageRepo.save(null))
		
		//System.out.println(productResponse1);
		
		Product savedProduct = productService.saveProduct(productTest1);
		
		assertThat(savedProduct).isNotNull();
		assertThat(savedProduct.getName()).isNotNull();
		assertThat(savedProduct.getId()).isGreaterThan(0);
		
		
	}
	
	@DisplayName("test para intentar guardar un producto con información inconsistente")
	@Test
	void saveProductInconsitentDataTest() {
		
		Product wrongPriceProduct = productTest1;
		wrongPriceProduct.setPrice(lowestPrice-1);
		
		Product wrongDateProduct = productTest1;
		wrongDateProduct.setCreationDate(LocalDate.now().plusWeeks(1));
		
		Product noImagesProduct = productTest1;
		noImagesProduct.setImage(List.of());
		
		assertThrows(InconsitentDataException.class, ()->{
			productService.saveProduct(wrongPriceProduct);
		});

		assertThrows(InconsitentDataException.class, ()->{
			productService.saveProduct(wrongDateProduct);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			productService.saveProduct(noImagesProduct);
		});
		
		
		verify(productRepo, never()).save(any(Product.class));
		
		
		
	}
	
	@DisplayName("test para intentar actualizar un producto con información inconsistente, precio incorrecto, fecha incorrecta, sin imagenes")
	@Test
	void updateProductInconsitentDataTest() {
		int id = 1;
		Product productResponse = productTest1;
		productResponse.setId(id);
		
		
		Product wrongPriceProduct = productResponse;
		wrongPriceProduct.setPrice(lowestPrice-1);
		
		Product wrongDateProduct = productResponse;
		wrongDateProduct.setCreationDate(LocalDate.now().plusWeeks(1));
		
		Product noImagesProduct = productResponse;
		noImagesProduct.setImage(List.of());
		
		given(productRepo.findById(id)).willReturn(Optional.of(productResponse));
		
		
		assertThrows(InconsitentDataException.class, ()->{
			productService.updateProductById(id, noImagesProduct);
		});

		assertThrows(InconsitentDataException.class, ()->{
			productService.updateProductById(id,wrongDateProduct);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			productService.updateProductById(id, noImagesProduct);
		});
		
		
		verify(productRepo, never()).save(any(Product.class));
		
		
		
	}
	
	
	@DisplayName("Test para intentar actualizar un producto que no existe ")
	@Test
	void updateNotFoundProductTest() {
		int id =1;
		String newName = "Pyrospoon";
		Product responseProduct = productTest1;
		responseProduct.setId(id);
		responseProduct.setName(newName);
		List<Image> newImageList = new ArrayList<>(responseProduct.getImage());
		newImageList.add(product3Image);
		responseProduct.setImage(newImageList);

		given(productRepo.findById(id)).willReturn(Optional.empty());		
		
		
		assertThrows(ResourceNotFoundException.class, () -> {
			productService.updateProductById(responseProduct.getId(), responseProduct);
		});
			verify(productRepo, never()).save(any(Product.class));

	}
	
	
	
	
	
}
