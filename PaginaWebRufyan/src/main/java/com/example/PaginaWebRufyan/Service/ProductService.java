package com.example.PaginaWebRufyan.Service;


import com.example.PaginaWebRufyan.DTO.ProductDTO;

import com.example.PaginaWebRufyan.DTO.ProductRegisterDTO;
import com.example.PaginaWebRufyan.DTO.ProductUpdateRegisterDTO;
import com.example.PaginaWebRufyan.Entity.*;
import com.example.PaginaWebRufyan.Exceptions.InconsitentDataException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.ProductsCategoryRepository;
import com.example.PaginaWebRufyan.Repository.ProductsRepository;
import com.example.PaginaWebRufyan.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
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
	/*
	private void checkConsistentData(ProductUpdateRegisterDTO productData ){
		 Map<String,Object> priceManage =productData.getPriceManage();
		BigDecimal copyPrice
		if(productData.getOldImages().isEmpty()){
			throw new InconsitentDataException("Every single product should have at least one image");
		} else if (productData.) {

		}

	}

	 */

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
	//DEPRECATED
	public ProductDTO saveProduct(Product product) {
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
		
		return new ProductDTO(productsRepository.save(product));
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

	//good idea, here checking diferences between the foundProduct and the ProductUpdate register so can be possible to delete an image from the server
	@Transactional
	public ProductDTO updateProductById(Integer id, ProductUpdateRegisterDTO productUpdateData) {


		Product foundProduct = findProductById(id);
		Product updatedProduct = ProductFactory.createProductFromRegister(productUpdateData);
		updatedProduct.setId(id);
		List<Image> productImages =foundProduct.getImage();
		if(productUpdateData.getOldImages().isEmpty() && productUpdateData.getNewImageFiles().isEmpty())throw  new InconsitentDataException("Is not posible to update a product with no images:");
		List<Image> updateRegisterImages = productUpdateData.getOldImages();
		List<Image> imagesToDelete = productImages.stream().filter(image -> !(updateRegisterImages.contains(image))).toList();

		List<Image> newImages = new ArrayList<>(List.of());

		if(!productUpdateData.getNewImageFiles().isEmpty()){
			newImages = imageService.processImages(productUpdateData.getNewImageFiles());
		}
		newImages.addAll(updateRegisterImages);

		updatedProduct.setImage(newImages);

		imageService.deleteAllImages(imagesToDelete);



		return new ProductDTO(productsRepository.save(updatedProduct));



			
	}
		//FactoryPattern
	public ProductDTO createProduct(ProductUpdateRegisterDTO productData){
		if(productData.getNewImageFiles().isEmpty()){
			throw new InconsitentDataException("You can't create a product with no images");
		}
	Product newProduct=	ProductFactory.createProductFromRegister(productData);
	newProduct.setImage(imageService.processImages(productData.getNewImageFiles()));
		return new ProductDTO(productsRepository.save(newProduct));
	}

	public List<Product> retrieveProductsByNameContainging(String searchTerm) {
		// TODO Auto-generated method stub
		return productsRepository.findByNameContainingIgnoreCase(searchTerm);
	}

	
}


















