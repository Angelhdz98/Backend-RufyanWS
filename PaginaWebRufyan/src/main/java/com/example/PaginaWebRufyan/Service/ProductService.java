package com.example.PaginaWebRufyan.Service;


import com.example.PaginaWebRufyan.Components.OriginalStock;
import com.example.PaginaWebRufyan.Components.PaintingPriceManager;
import com.example.PaginaWebRufyan.DTO.ProductDTO;
import com.example.PaginaWebRufyan.DTO.ProductImagesRegisterDTO;
import com.example.PaginaWebRufyan.DTO.ProductRegisterDTO;
import com.example.PaginaWebRufyan.Entity.*;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.ImageRepository;
import com.example.PaginaWebRufyan.Repository.ProductsCategoryRepository;
import com.example.PaginaWebRufyan.Repository.ProductsRepository;
import com.example.PaginaWebRufyan.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;




@Service
public class ProductService {
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private ImageService imageService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductsCategoryRepository productsCategoryRepository;
	
	private final String UPLOAD_DIR = "C:/Users/PP/Documents/Proyectos Programación/Backends/Back-end rufyan/PaginaWebRufyan/PaginaWebRufyan/src/main/resources/static/";


	public ProductService() {
		super();
	}

	private Product findProductById(Integer id){
		return productsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with id: "+ id ));
	}
	private Product findProductByName(String name){
		return productsRepository.findByName(name).orElseThrow(()->new ResourceNotFoundException("Product not found with name: "+ name ));
	}

	public ProductService(ProductsRepository repository) {
		super();
		this.productsRepository = repository;
	}
	
	
	public List<ProductDTO> retrieveAllProducts(){
		return productsRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	public ProductDTO retrieveProductById(Integer id) {

		return new ProductDTO(findProductById(id));


	}
	public ProductDTO retrieveProductByName(String name){
		
		return new ProductDTO(findProductByName(name));
	}
	
	
	// method to create new products
	public Product saveProduct(Product product) {
		
		
		

		
			
			
			/*List<UserEntity> copyBuyers = List.of();

			if(product.getCopyBuyers()!=null) {
			for(UserEntity user: product.getCopyBuyers()) {
				copyBuyers.add(userRepository.findById(user.getId()).
						orElseThrow(()->new ResourceNotFoundException("User not found with id"+ user.getId())));
			}
			product.setCopyBuyers(copyBuyers);
				
			}
			*/
			
		
		/*product.setCategory(
		productsCategoryRepository.
		findByName(product.getCategory().getName()).orElseThrow(()->{
			throw new ResourceNotFoundException(
				"Role: "+product.getCategory().getName()+" was not found");
		}));
		*/
		
		return productsRepository.save(product);		
	}
	


	
	public void deleteProductById(Integer id) {

	Product productToDelete = findProductById(id);
	productsRepository.delete(productToDelete);

	}
	
	// Creo que este no se usa 
	/*public Product saveProductWithImages(Product product, List<MultipartFile> imageFiles) throws IOException  {

		List<Image> images = imageFiles.stream().map((file)->{
			try {
				String fileName = file.getOriginalFilename()+"_"+System.currentTimeMillis();
				Path filePath = Paths.get(UPLOAD_DIR + "images/products/"+ fileName);
				Files.write(filePath, file.getBytes());
				
				Image image= Image.builder() 
									.url(filePath.toString()) 
									.productName(file.getOriginalFilename()) 
									.build();
					
				return imageRepository.save(image);
			}
			catch(IOException e ){
				throw  new RuntimeException("Failed to store image in "+ UPLOAD_DIR+ "images/products"+file.getOriginalFilename().toString() );
				
			}
			
		}) 
				.collect(Collectors.toList());
		
			product.setImage(images);
			
			return productsRepository.save(product);
	}
	*/
	public ProductDTO updateProductById(Integer id, ProductDTO productData) {

		Product foundProduct = findProductById(id);

		foundProduct.setDescription(productData.getDescription());
		foundProduct.setImage(productData.getImages());
		foundProduct.setName(productData.getName());
		//foundProduct.setPrice(productData.getPrice());
		foundProduct.setStyle(productData.getStyle());

		return new ProductDTO(productsRepository.save(foundProduct));



			
	}
		//FactoryPattern
	public Product createProduct(ProductRegisterDTO productData){
	List<Image> images	= imageService.processImages(productData.getImageFiles());
		return productsRepository.save(ProductFactory.createProductFromRegister(new ProductImagesRegisterDTO(productData,images)));
	}

	public List<Product> retrieveProductsByNameContainging(String searchTerm) {
		// TODO Auto-generated method stub
		return productsRepository.findByNameContainingIgnoreCase(searchTerm);
	}

	
}


















