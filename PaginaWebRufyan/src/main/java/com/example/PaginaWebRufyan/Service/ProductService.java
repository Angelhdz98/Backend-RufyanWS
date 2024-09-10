package com.example.PaginaWebRufyan.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.ImageRepository;
import com.example.PaginaWebRufyan.Repository.ProductsRepository;
import com.example.PaginaWebRufyan.Repository.UserRepository;

import jakarta.transaction.Transactional;

//import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
@Service
public class ProductService {
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserRepository userRepository;
	
	private final String UPLOAD_DIR = "C:/Users/PP/Documents/Proyectos Programación/Backends/Back-end rufyan/PaginaWebRufyan/PaginaWebRufyan/src/main/resources/static/";


	public ProductService() {
		super();
	}

	public ProductService(ProductsRepository repository) {
		super();
		this.productsRepository = repository;
	}
	
	
	public List<Product> retrieveAllProducts(){
		return productsRepository.findAll();
	}
	
	public Optional<Product> retrieveProductById(Integer id) {
		Optional<Product> optionalProduct = productsRepository.findById(id);

		return optionalProduct;
	}
	public Product retrieveProductByName(String name){
		return productsRepository.findByName(name)
				.orElseThrow(
				()->new ResourceNotFoundException("Product not found by name: "+name));
	}
	
	
	@Transactional
	public Product saveProduct(Product product) {
		if(product.getImage()!=null) {
			product.getImage().forEach(image -> 
			{
			
			imageRepository.save(image);
			});

			HashSet<UserEntity> favoriteBy = new HashSet<UserEntity>(Set.of());
			if(product.getFavoriteOf()!=null) {
				for(UserEntity fav : product.getFavoriteOf() ) {
					favoriteBy.add(userRepository
							.findById(fav.getId())
							.orElseThrow(()-> new ResourceNotFoundException("User not found with id"+ fav.getId())));
				}
				product.setFavoriteOf(favoriteBy);
			}
			
			/*List<UserEntity> copyBuyers = List.of();

			if(product.getCopyBuyers()!=null) {
			for(UserEntity user: product.getCopyBuyers()) {
				copyBuyers.add(userRepository.findById(user.getId()).
						orElseThrow(()->new ResourceNotFoundException("User not found with id"+ user.getId())));
			}
			product.setCopyBuyers(copyBuyers);
				
			}
			*/
			
		}
		return productsRepository.save(product);		
	}
	
	/**
	 * @PutMapping()
	public Product updateProductById(Integer id, Product product) {
		
	}
	**/
	
	public Optional<Product> deleteProductById(Integer id) {
		Optional<Product> product = retrieveProductById(id);
		if(product.isPresent()) {
		productsRepository.deleteById(id);}
		return product;
	}

	public Product saveProductWithImages(Product product, List<MultipartFile> imageFiles) throws IOException  {

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
	
	public Product updateProductById(Integer id, Product productData) {
		
		Optional<Product> optionalProduct = retrieveProductById(id);
		if (optionalProduct.isEmpty()) {
			throw new ResourceNotFoundException("Product no found with id: "+ id);
		}
		else {
			productData.setId(id);
			return productsRepository.save(productData);
		}
			
	}

	
}


















