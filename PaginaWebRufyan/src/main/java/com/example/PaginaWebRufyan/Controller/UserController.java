package com.example.PaginaWebRufyan.Controller;

//import java.util.HashSet;
import java.util.List;
import java.util.Optional;
//import java.util.Set;

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

import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
		
	@GetMapping("/users")
	@PreAuthorize("permitAll()")
	public List<UserEntity> retrieveAllUsers(){
		return   userService.findAllUsers();
	}
	
	@GetMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntity> retrieveUserById(@PathVariable Integer id){
		Optional<UserEntity> user= userService.findUserById(id);
		 if (user.isEmpty()) {
			 return  ResponseEntity.notFound().build();
		 }
		 
		return ResponseEntity.ok(user.get());
	}
	
	@PostMapping("/users")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userData) {

		return ResponseEntity.ok(userService.save(userData));
		
	}
	
	@PutMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserEntity user) {
		Optional<UserEntity> optionalUser = userService.findUserById(id);
		 if (optionalUser.isEmpty()) {
			 return  ResponseEntity.notFound().build();
		 }
		 
		 return ResponseEntity.ok(userService.save(user));
	}
	
	@DeleteMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntity> deleteUser(@PathVariable Integer id){
		Optional<UserEntity> user = userService.findUserById(id);
		if(user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		userService.deleteUserById(id);
		return  ResponseEntity.ok().build();
		
		
			
	}
	
	
}