package com.example.PaginaWebRufyan.Service;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.PaginaWebRufyan.DTO.UserEditableDTO;
import com.example.PaginaWebRufyan.DTO.UserEntityDTO;
import com.example.PaginaWebRufyan.DTO.UserRegisterDTO;
import com.example.PaginaWebRufyan.Entity.RoleEntity;
import com.example.PaginaWebRufyan.Utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.PaintingRepository;
import com.example.PaginaWebRufyan.Repository.ProductsRepository;
import com.example.PaginaWebRufyan.Repository.RoleRepository;
import com.example.PaginaWebRufyan.Repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ProductsRepository productsRepository;
	@Autowired 
	private PaintingRepository paintingRepository;






	private RoleEntity getClientRole(){
		return roleRepository.findByRoleEnum(RoleEnum.CLIENT)
				.orElseThrow(()->new ResourceNotFoundException("Role client not found"));

	}

	private UserEntity findUserBydId(Integer id){
		return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not foud by id: " +id));
	}
	private Product findProductById(Integer id) {
		return productsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with id: "+ id));
	}

	public List<UserEntityDTO> findAllUsers() {
		return userRepository.findAll().stream().map(UserEntityDTO::new).collect(Collectors.toList());

		
	}

	public UserEntityDTO createUser(UserRegisterDTO userData){

		UserEntity newUser = UserEntity.builder()
				.name(userData.getName())
				.lastname(userData.getLastName())
				.email(userData.getEmail())
				.birthDate(userData.getBirthDate())
				.password(userData.getPassword())
				.roles(Set.of(getClientRole()))
				.isEnabled(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.credentialNoExpired(true)
				.build();

		return new UserEntityDTO(userRepository.save(newUser));

	}

	public UserEntityDTO retrieveUserById(Integer id) {

		return new UserEntityDTO(findUserBydId(id));

	}



	public  UserEntityDTO updateUser(Integer id ,UserEditableDTO userData) {

		UserEntity foundUser = findUserBydId(id);
		foundUser.setEmail(userData.getEmail());
		foundUser.setName(userData.getName());
		foundUser.setLastname(userData.getLastname());
		foundUser.setBirthDate(userData.getBirthDate());
		foundUser.setUsername(userData.getUsername());

		return  new UserEntityDTO(userRepository.save(foundUser));

	}



	
	@Transactional
	public void deleteUserById(Integer id) {

		 userRepository.deleteById(id);
		 
	
		
	}
	// should be changed this method?
	public UserEntityDTO toggleProductToFavoriteFrom(Integer productId, Integer userId){


		UserEntity user = findUserBydId(userId);
		Product product = findProductById(productId);

		Optional<Product>  existingFavoriteProduct = user.getFavoriteProducts().stream().filter((fav)-> productId.equals(fav.getId()) ).findFirst();

		if(existingFavoriteProduct.isEmpty()){
			user.addFavoriteProduct(product);
		}else {
			user.removeFavoriteProduct(product);
		}

		return new UserEntityDTO(userRepository.save(user));

	}
	

	
	
}