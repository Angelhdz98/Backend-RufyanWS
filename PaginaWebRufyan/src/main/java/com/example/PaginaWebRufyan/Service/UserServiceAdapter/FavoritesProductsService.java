package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.Products.Repository.ProductsRepository;
import com.example.PaginaWebRufyan.User.Repository.UserRepository;

@Service
public class FavoritesProductsService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductsRepository productsRepository;
	
	public Optional<Product> addProductToFavorites(Integer userId, Integer productId){
		
		Optional<Product> optionalProduct= productsRepository.findById(productId);
		Optional<UserEntity> optionalUser = userRepository.findById(userId);
		
		if (optionalProduct.isPresent() && optionalUser.isPresent()) {
			UserEntity user = optionalUser.get();
			Product product = optionalProduct.get();
			//user.getFavoriteProducts().add(product);
			 Set<UserEntity> listaUsuarios = product.getFavoriteOf();
			
			listaUsuarios.add(user); 
			 
			 product.setFavoriteOf(listaUsuarios);
			 
			productsRepository.save(product);
			//userRepository.save(user);
			
			
		}
		 
		Optional<Product> result = productsRepository.findById(productId);
		return result;
		
	}
	
	public Optional<Product> removeProductfromFavorites(Integer userId, Integer productId){
		
		Optional<Product> optionalProduct= productsRepository.findById(productId);
		Optional<UserEntity> optionalUser = userRepository.findById(userId);
		
		if (optionalProduct.isPresent() && optionalUser.isPresent()) {
			UserEntity user = optionalUser.get();
			Product product = optionalProduct.get();
			user.removeFavoriteProduct(product);
			
			userRepository.save(user);
			
		}
		
		return optionalProduct;
		
	}
	
	
	
	
	

}
