package com.example.PaginaWebRufyan.Controller;

import java.util.List;
import java.util.Set;

import com.example.PaginaWebRufyan.DTO.*;
import com.example.PaginaWebRufyan.Entity.CartItem;
import com.example.PaginaWebRufyan.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Service.ProductService;
import com.example.PaginaWebRufyan.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
		
	@GetMapping("")
	@PreAuthorize("permitAll()")
	public List<UserEntityDTO> retrieveAllUsers(){
		return   userService.findAllUsers();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntityDTO> retrieveUserById(@PathVariable Integer id){

		return ResponseEntity.ok(userService.retrieveUserById(id));
	}
	
	@PostMapping("")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntityDTO> saveUser(@RequestBody UserRegisterDTO userData) {

		return ResponseEntity.ok(userService.createUser(userData));
		
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntityDTO> updateUser(@PathVariable Integer id, @RequestBody UserEditableDTO userData) {

		 return ResponseEntity.ok(userService.updateUser(id, userData));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<UserEntity> deleteUser(@PathVariable Integer id){
		        UserEntityDTO userToDelete = userService.retrieveUserById(id);// this sentece will throw a resource not exception if user does not exist
				userService.deleteUserById(id);
		return  ResponseEntity.ok().build();
		
		
			
	}
	
	@PutMapping("/{userId}/favorites/{productId}")
	public ResponseEntity<Set<ProductDTO>> toggleProductToFavorite(@PathVariable Integer userId, @PathVariable Integer productId){

		return ResponseEntity.ok(userService.toggleProductToFavoriteFrom(productId, userId));
		
		
	}

	@PostMapping("/add-to-cart")
	public CartItemDTO addCartItemToCart (@RequestBody CartItemRegisterDTO itemRegister){
		return userService.addProductToCart(itemRegister);
	}

	@PostMapping("/add-to-cart-params/{productId}/{userId}/{quantity}/{isOriginalSelected}")
	public CartItemDTO addCartItemToCart (@PathVariable Integer productId,@PathVariable Integer userId,@PathVariable Integer quantity, @PathVariable Boolean  isOriginalSelected){
		return userService.addProductToCart(productId, userId, quantity, isOriginalSelected);
	}
	@DeleteMapping("/delete-to-cart")
	public ResponseEntity<Void> removeCartItem (@RequestBody CartItemRegisterDTO cartItemRegisterDTO){
		 userService.removeCartItemFromCart(cartItemRegisterDTO);
		return ResponseEntity.accepted().build();
	}

	@DeleteMapping("/delete-to-cart-params/{productId}/{userId}/{quantity}/{isOriginalSelected}")
	public ResponseEntity<Void> removeCartItem (@PathVariable Integer productId,@PathVariable Integer userId,@PathVariable Integer quantity, @PathVariable Boolean  isOriginalSelected){
		userService.removeCartItemFromCart(productId,userId, quantity, isOriginalSelected);

		return ResponseEntity.accepted().build();
	}


	
}