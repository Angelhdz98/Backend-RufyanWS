package com.example.PaginaWebRufyan.Service;


import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.PaginaWebRufyan.DTO.*;
import com.example.PaginaWebRufyan.Entity.*;
import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.InconsitentDataException;
import com.example.PaginaWebRufyan.Exceptions.NoStockException;
import com.example.PaginaWebRufyan.Repository.*;
import com.example.PaginaWebRufyan.Utils.RoleEnum;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;

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
	@Autowired
	CartItemRepository cartItemRepository;
	@Autowired
	ShoppingCartRepository shoppingCartRepository;








	/*private RoleEntity getClientRole(){
		return roleRepository.findByRoleEnum(RoleEnum.CLIENT)
				.orElseThrow(()->new ResourceNotFoundException("Role client not found"));

	}*/

	private UserEntity findUserBydId(Integer id){
		return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not foud by id: " +id));
	}
	private UserEntity findUserByEmail(String email){
		return  userRepository.findUserByEmail(email).orElseThrow(()->new ResourceNotFoundException("Not foud by email: " +email));
	}
	private UserEntity findUserByUsername(String username) {
	return userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User not found by username " + username));
	}
	private Product findProductById(Integer id) {
		return productsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with id: "+ id));
	}

	private CartItem findCartItem(CartItemRegisterDTO cartRegister){

		UserEntity user = findUserBydId(cartRegister.getUserId());

		 return user.getShoppingCart().getItemList().stream().filter((CartItem item)-> item.getProduct().getId().equals(cartRegister.getProductId()) && item.getIsOriginalSelected().equals(cartRegister.getIsOriginalSelected())).findFirst().orElseThrow(()-> new ResourceNotFoundException("Cart item with product id: "+ cartRegister.getProductId()+ "doesn't exist with actual value of isOriginalSelected: " + String.valueOf(cartRegister.getIsOriginalSelected())));

	}


	public List<UserEntityDTO> findAllUsers() {
		return userRepository.findAll().stream().map(UserEntityDTO::new).collect(Collectors.toList());

		
	}
	@Transactional
	public UserEntityDTO createUser(UserRegisterDTO userData){

		if (userRepository.existsByUsername(userData.getUsername())){
			throw new AlreadyExistIdenticatorException("username: "+ userData.getUsername()+" already exist");
		}
		if(userRepository.findUserByEmail(userData.getEmail()).isPresent()){
			throw new AlreadyExistIdenticatorException("Email: "+ userData.getEmail()+ " already registered" );
		}
		if(ChronoUnit.YEARS.between( userData.getBirthDate(),LocalDate.now())<13 ){
			throw  new InconsitentDataException("User must be at least 13 years old and its: " + ChronoUnit.YEARS.between(userData.getBirthDate(),LocalDate.now()));
		}
		if(userRepository.findUserByUsername(userData.getUsername()).isPresent()){
			throw new AlreadyExistIdenticatorException("username: "+ userData.getUsername()+"already occupied");
		}
		UserEntity newUser = UserEntity.builder()
				.name(userData.getName())
				.lastname(userData.getLastName())
				.email(userData.getEmail())
				.birthDate(userData.getBirthDate())
				.password(userData.getPassword())
				.username(userData.getUsername())
				.roles(
						 new HashSet<>(Set.of(roleRepository.findByRoleEnum(
								RoleEnum.CLIENT)
						.orElseThrow(
								()->new ResourceNotFoundException("Role client not found")))))
				.isEnabled(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.credentialNoExpired(true)
				.favoriteProducts(new HashSet<>())
				.originalPaintings(new HashSet<>())
				.build();

		UserEntity createdUser= userRepository.save(newUser);


		ShoppingCart shoppingCart = ShoppingCart.builder().user(createdUser).itemList(new HashSet<>()).updatedAt(LocalDate.now()).build();

		createdUser.setShoppingCart(shoppingCart);
		createdUser.getFavoriteProducts().size();
		return new UserEntityDTO(userRepository.save(createdUser));


	}

	public UserEntityDTO retrieveUserById(Integer id) {

		return new UserEntityDTO(findUserBydId(id));

	}
	 public List<UserEntityDTO> retrieveUsersByEmailContaining (String emailPart){
		return  userRepository.findByEmailContainingIgnoreCase(emailPart).
				stream().limit(10)
				.map(UserEntityDTO::new)
				.collect(Collectors.toList());// solo mostrará los primeros 10 resultados
	 }

	 public Page<UserEntityDTO> retrieveUsersByEmailContaining(String emailPart, Integer pageNum, Integer size){
		Page<UserEntity> users =  userRepository.findByEmailContainingIgnoreCase(emailPart, PageRequest.of(pageNum,size, Sort.by("email").ascending()));
		return  users.map(UserEntityDTO::new);

	 }
/*
	@Transactional
	 public CartItem addProductToCart(Integer productId, Integer userId, Integer quantity, Boolean isOriginalSelected){
		 BigDecimal price;
		 CartItem cartItem;
		UserEntity userFound = findUserBydId(userId);// this function will check if user exists
		Product productFound = findProductById(productId);
			if(productFound instanceof Painting){
				Painting painting = (Painting) productFound;
				if(isOriginalSelected){
					 price = BigDecimal.valueOf(painting.getPrice());
				} else{
					price = BigDecimal.valueOf(painting.getPricePerCopy());
				}
				//cartItem=  new CartItem(painting, quantity, isOriginalSelected, price);
						cartItem = CartItem.builder()
						.product(painting).quantity(quantity)
						.isOriginalSelected(isOriginalSelected)
						.pricePerUnit(price)
						.build();

			} else {
				price=  BigDecimal.valueOf(productFound.getPrice());
				//cartItem = new CartItem(productFound, quantity, false,price);
						cartItem = CartItem.builder()
						.product(productFound)
						.quantity(quantity)
						.isOriginalSelected(false)
						.pricePerUnit(price)
						.build();
			}

			//userFound.addCartItemToCart(cartItem);

			 userRepository.save(userFound);

			 return cartItem;




	 }
*/
	@Transactional
	public CartItemDTO addProductToCart(CartItemRegisterDTO cartItemRegisterDTO){
		BigDecimal price;
		CartItem cartItem;
		UserEntity userFound = findUserBydId(cartItemRegisterDTO.getUserId());// this function will check if user exists
		Product productFound = findProductById(cartItemRegisterDTO.getProductId());
		if(productFound.getAvailableStock()<cartItemRegisterDTO.getQuantity()){
			throw new NoStockException("el producto con id: "+ productFound.getId() + "no cuenta con "+ cartItemRegisterDTO.getQuantity() +" piezas de stock");
		}
		if(productFound instanceof Painting){
			Painting painting = (Painting) productFound;
			if(cartItemRegisterDTO.getIsOriginalSelected()){
				price = BigDecimal.valueOf(painting.getPrice());
			} else{
				price = BigDecimal.valueOf(painting.getPricePerCopy());
			}
			//cartItem=  new CartItem(painting, quantity, isOriginalSelected, price);
			cartItem = CartItem.builder()
					.product(painting).quantity(cartItemRegisterDTO.getQuantity())
					.isOriginalSelected(cartItemRegisterDTO.getIsOriginalSelected())
					.pricePerUnit(price)
					.build();

		} else {
			price=  BigDecimal.valueOf(productFound.getPrice());
			//cartItem = new CartItem(productFound, quantity, false,price);
			cartItem = CartItem.builder()
					.product(productFound)
					.quantity(cartItemRegisterDTO.getQuantity())
					.isOriginalSelected(false)
					.pricePerUnit(price)
					.build();
		}
		cartItem.setShoppingCart(userFound.getShoppingCart());

		userFound.getShoppingCart().addCartItem(cartItem);

		userRepository.save(userFound);

		return  new CartItemDTO(cartItem);




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
	public Set<ProductDTO> toggleProductToFavoriteFrom(Integer productId, Integer userId){


		UserEntity user = findUserBydId(userId);
		Product product = findProductById(productId);

		Optional<Product>  existingFavoriteProduct = user.getFavoriteProducts().stream().filter((fav)-> productId.equals(fav.getId()) ).findFirst();

		if(existingFavoriteProduct.isEmpty()){
			user.addFavoriteProduct(product);
		}else {
			user.removeFavoriteProduct(product);
		}
		userRepository.save(user);
		return user.getFavoriteProducts().stream().map(ProductDTO::new).collect(Collectors.toSet());

	}


	public UserEntityDTO retrieveUserByUsername(@NotNull String username) {
		return new UserEntityDTO(findUserByUsername(username));}

	public  UserEntityDTO retrieveUserByEmail(@NotNull String email){

		return new UserEntityDTO(findUserByEmail(email));
	}
	@Transactional
	public CartItemDTO addProductToCart(Integer productId, Integer userId, Integer quantity, Boolean isOriginalSelected) {
		if(quantity<1)throw new InconsitentDataException("Quantity should be 1 at least");
		BigDecimal price;
		CartItem cartItem;
		UserEntity userFound = findUserBydId(userId);// this function will check if user exists



		Product productFound = findProductById(productId);

		Optional<CartItem> existingCartItem = userFound.getShoppingCart().getItemList().stream().filter((CartItem item)-> item.getProduct().getId().equals(productFound.getId())&& item.getIsOriginalSelected().equals(isOriginalSelected) ).findFirst();

		if(existingCartItem.isPresent()){
			userFound.getShoppingCart().deleteCartItem(existingCartItem.get());
		}

				//.getItemList().removeIf((CartItem item)-> item.getProduct().getId().equals(productFound.getId())&& item.getIsOriginalSelected().equals(isOriginalSelected) );

		if(productFound instanceof Painting){
			Painting painting = (Painting) productFound;
			if(!painting.getIsOriginalAvailable() && isOriginalSelected){
				throw new NoStockException("Painting: " + painting.getName() + " is not original available");
			}
			//Check stock
			if(painting.getAvailableStock()< quantity ){
				throw new NoStockException("Painting " + painting.getName() + " has no enough copies");
			}

			if(isOriginalSelected){
				price = BigDecimal.valueOf(painting.getPrice());
				painting.setIsOriginalAvailable(false);
			} else{

				price = BigDecimal.valueOf(painting.getPricePerCopy());
			}
			//cartItem=  new CartItem(painting, quantity, isOriginalSelected, price);
			cartItem = CartItem.builder()
					.product(painting).quantity(quantity)
					.isOriginalSelected(isOriginalSelected)
					.pricePerUnit(price)
					.build();

		} else {

			if(productFound.getAvailableStock()<quantity){
				throw  new NoStockException("Product: " + productFound.getName()+" has no enough stock");
			}

			price= BigDecimal.valueOf(productFound.getPrice()) ;
			//cartItem = new CartItem(productFound, quantity, false,price);
			cartItem = CartItem.builder()
					.product(productFound)
					.quantity(quantity)
					.isOriginalSelected(false)
					.pricePerUnit(price)
					.shoppingCart(userFound.getShoppingCart())
					.build();
		}
		// if exist a CartItem with same product and the same value of isOriginalSelected will deleted before add the new



		userFound.addCartItemToCart(cartItemRepository.save(cartItem));
		shoppingCartRepository.save(userFound.getShoppingCart());
		userRepository.save(userFound);

		return new CartItemDTO(cartItem);



	}
	public void  removeCartItemFromCart(CartItemRegisterDTO cartItem){
		UserEntity foundUser= findUserBydId(cartItem.getUserId());



		Optional<CartItem> existingCartItem = foundUser.getShoppingCart().getItemList().stream().filter((CartItem item)-> item.getProduct().getId().equals(cartItem.getProductId())&& item.getIsOriginalSelected().equals(cartItem.getIsOriginalSelected()) ).findFirst();


		if(existingCartItem.isPresent()){
			foundUser.getShoppingCart().deleteCartItem(existingCartItem.get());
		} else {
			throw new ResourceNotFoundException("Cart item is not present");
		}
		//foundUser.getShoppingCart().deleteCartItem();

		userRepository.save(foundUser);

	}

	public void removeCartItemFromCart(Integer productId, Integer userId, Integer quantity, Boolean isOriginalSelected){
		UserEntity foundUser= findUserBydId(userId);
		foundUser.getShoppingCart().deleteCartItem(new CartItemRegisterDTO(productId, userId,quantity,isOriginalSelected));
		userRepository.save(foundUser);

	}
}
