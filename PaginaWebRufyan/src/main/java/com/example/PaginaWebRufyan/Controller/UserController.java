package com.example.PaginaWebRufyan.Controller;

//import java.util.HashSet;
import java.util.List;
import java.util.Optional;
//import java.util.Set;

import com.example.PaginaWebRufyan.DTO.UserEditableDTO;
import com.example.PaginaWebRufyan.DTO.UserEntityDTO;
import com.example.PaginaWebRufyan.DTO.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;

import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Service.ProductService;
import com.example.PaginaWebRufyan.Service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
		
	@GetMapping("/users")
	@PreAuthorize("permitAll()")
	public List<UserEntityDTO> retrieveAllUsers(){
		return   userService.findAllUsers();
	}
	
	@GetMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntityDTO> retrieveUserById(@PathVariable Integer id){

		return ResponseEntity.ok(userService.retrieveUserById(id));
	}
	
	@PostMapping("/users")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntityDTO> saveUser(@RequestBody UserRegisterDTO userData) {

		return ResponseEntity.ok(userService.createUser(userData));
		
	}
	
	@PutMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntityDTO> updateUser(@PathVariable Integer id, @RequestBody UserEditableDTO userData) {

		 return ResponseEntity.ok(userService.updateUser(id, userData));
	}
	
	@DeleteMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntity> deleteUser(@PathVariable Integer id){
		        UserEntityDTO userToDelete = userService.retrieveUserById(id);// this sentece will throw a resource not exception if user does not exist
				userService.deleteUserById(id);
		return  ResponseEntity.ok().build();
		
		
			
	}
	
	@PutMapping("/users/{userId}/favorites/{productId}")
	public ResponseEntity<UserEntityDTO> toggleProductToFavorite(@PathVariable Integer userId, @PathVariable Integer productId){

		return ResponseEntity.ok(userService.toggleProductToFavoriteFrom(productId, userId));
		
		
	}
	
	
}