package com.example.PaginaWebRufyan.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.example.PaginaWebRufyan.DTO.*;
import com.example.PaginaWebRufyan.Entity.*;
import com.example.PaginaWebRufyan.Exceptions.NoStockException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.InconsitentDataException;
import com.example.PaginaWebRufyan.Service.UserService;
import com.example.PaginaWebRufyan.Utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	RoleRepository roleRepo;


	@InjectMocks
	UserService userService;

	@Mock
	private ProductsRepository productsRepository;
	@Mock
	private PaintingRepository paintingRepository;
	@Mock
	CartItemRepository cartItemRepository;
	@Mock
	ShoppingCartRepository shoppingCartRepository;



	private UserEntity user = new UserEntity();
	private UserEntity user2 = new UserEntity();
	private UserEntity user3 = new UserEntity();
	private UserEntity user4 = new UserEntity();

	private Product product1 = new Product();
	private Product product2= new Product();

	
	
	PermissionEntity createPermission = PermissionEntity.builder()
            .name("CREATE")
            .build();

    PermissionEntity readPermission = PermissionEntity.builder()
            .name("READ")
            .build();

    
	RoleEntity roleClient = RoleEntity.builder()
            .roleEnum(RoleEnum.CLIENT)
            .permissionList(new HashSet<>(Set.of(createPermission, readPermission)))
            .build();
	@BeforeEach
	void setUp() {
		
		user= UserEntity.builder()
				.birthDate(LocalDate.of(1988, 3, 28))
				.credentialNoExpired(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.name("Ezequiel")
				.lastname("Machiwi")
				.password("soyUnChicoEnamorado")
				.email("ezequielmachiwi@gmail.com")
				.username("EzequielMach")
				.isEnabled(true)
				.build();
		user2= UserEntity.builder()
				.birthDate(LocalDate.of(1999, 5, 16))
				.credentialNoExpired(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.name("Ezelik")
				.lastname("Gamez")
				.password("soyUnChicoGamez")
				.email("ezelikgamez@gmail.com")
				.username("EzelGom")
				.build();
		
		user3= UserEntity.builder()
				.birthDate(LocalDate.of(1999, 5, 16))
				.credentialNoExpired(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.name("Lorena")
				.lastname("Copiona")
				.password("soyUnaCopiona")
				.email("copiona@gmail.com")
				.username("Forinconsitentespurposes")
				.build();
		
		user4= UserEntity.builder()
				.birthDate(LocalDate.of(1980, 5, 16))
				.credentialNoExpired(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.name("Albertano")
				.lastname("De la candelaria")
				.password("esperonoolvidaresta")
				.email("albertano@gmail.com")
				.username("elAlbertano")
				.build();



		
	}
	
	@DisplayName("Test para encontrar un usuario por su id")
	@Test
	void findUserByIdtest() {
	Integer id= 1; 
	UserEntity userResponse= user;
	userResponse.setId(id);
	given(userRepo.findById(id)).willReturn(Optional.of(userResponse));
	
	UserEntityDTO foundUser = userService.retrieveUserById(id);
	
	assertThat(foundUser).isNotNull();
	assertThat(foundUser.getFullName()).isEqualTo(userResponse.getName()+ " "+ userResponse.getLastname());
	
	
	}
	
	@DisplayName("Test para encontrar un usuario por su username")
	@Test
	void findUserByUsername() {
		Integer id= 1;
		String username= "nombreDeUsuario";
		UserEntity userResponse= user;
		userResponse.setUsername(username);
		UserEntityDTO userDto = new UserEntityDTO(userResponse);
		userResponse.setId(id);

		given(userRepo.findByUsername(userResponse.getUsername())).willReturn(Optional.of(userResponse));
		
		 UserEntityDTO foundUserDto = userService.retrieveUserByUsername(username);

		assertThat(foundUserDto).isNotNull();
		assertThat(foundUserDto.getUserName()).isEqualTo(userResponse.getUsername());
		assertThat(foundUserDto.getFullName()).isEqualTo(userResponse.getName()+" "+userResponse.getLastname());

		
	}
	
	void findUserByUserNameTestOk() {
		Integer id= 1;
		UserEntity userResponse= user;
		UserEntity userResponse2= user2;


		
	}
/*	 due this Test depends on a list will be implemented later
	@DisplayName("Test para encontrar un usuario a través de su email")
	@Test
	void findUserByEmail() {
		Integer id = 1;
		
		UserEntity userResponse= user;
		userResponse.setId(id);
		given(userRepo.findUserByEmail(user.getEmail())).willReturn(Optional.of(userResponse));
		
		UserEntityDTO foundUserDto = userService.(user.getEmail());
		UserEntity foundUser = optionalFoundUser.get();
		
		assertThat(foundUser).isNotNull();
		assertThat(foundUser.getEmail()).isEqualTo(userResponse.getEmail());
		
		
	}
*/


	@DisplayName("Test para guardar un usuario de manera exitosa")
	@Test
	void saveNewUserTestOk() {
		Integer id = 1;
		Integer roleId=1;
		UserEntity userResponse = user;
		userResponse.setId(id);
		userResponse.setRoles(new HashSet<>(Set.of(roleClient)));
		RoleEntity roleClientResponse= roleClient;
		roleClientResponse.setId(roleId);
		UserRegisterDTO userRegisterDto = new UserRegisterDTO(user);

		
		
		given(userRepo.existsByUsername(user.getUsername())).willReturn(false);
		given(userRepo.findUserByEmail(user.getEmail())).willReturn(Optional.empty());
		given(userRepo.save(any(UserEntity.class))).willReturn(userResponse);
		given(roleRepo.findByRoleEnum(RoleEnum.CLIENT)).willReturn(Optional.of(roleClientResponse));
		
		
		UserEntityDTO nuevoUsuario= userService.createUser(userRegisterDto);
		
		assertThat(nuevoUsuario).isNotNull();

		
		
	}

	@DisplayName("Test para intentar guardar un usuario con email repetido")
	@Test
	void saveNewUserReapeatedEmail() {
		UserRegisterDTO userRegisterDTO = new UserRegisterDTO(user);
		given(userRepo.findUserByEmail(user.getEmail())).willReturn(Optional.of(user));
		
		assertThrows(AlreadyExistIdenticatorException.class, ()->{
			userService.createUser(userRegisterDTO);
		});
		
		verify(userRepo, never()).save(any(UserEntity.class));
		
		
	}
	
	@DisplayName("Test para intentar guardar un usuario con el username repetido")
	@Test
	void saveNewUserReapeatedUsername() {
		UserEntity userNameRepeated = new UserEntity();
		userNameRepeated.setUsername("repeated");
		UserRegisterDTO userRegister = new UserRegisterDTO(userNameRepeated);

		PermissionEntity createPermission = PermissionEntity.builder()
				.name("CREATE")
				.build();

		PermissionEntity readPermission = PermissionEntity.builder()
				.name("READ")
				.build();

		RoleEntity clientRole= new RoleEntity();
		clientRole.setRoleEnum(RoleEnum.CLIENT);
		clientRole.setPermissionList(Set.of(readPermission,createPermission));
		clientRole.setId(1);

		//given(userRepo.findUserByUsername(userNameRepeated.getUsername())).willReturn(Optional.of(userNameRepeated));
		given(userRepo.existsByUsername(userNameRepeated.getUsername())).willReturn(true);
//		given(roleRepo.findByRoleEnum(RoleEnum.CLIENT)).willReturn(Optional.of(clientRole));

		assertThrows(AlreadyExistIdenticatorException.class, ()->{
			userService.createUser(userRegister);
		});
		
		verify(userRepo, never()).save(any(UserEntity.class));
		
		
	}

	@DisplayName("Test para intentar guardar un usuario con datos inconsistentes: No-username, Weak-password No-email ")
	@Test
	void saveNewUserInconsistentData() {
		UserEntity bornedIn2025 = user;
		bornedIn2025.setBirthDate(LocalDate.now().plusYears(1));
		
		UserEntity noUsername = user;
		noUsername.setUsername("");
		
		UserEntity noEmailUser = user;
		noEmailUser.setEmail("");
		
		UserEntity weakPasswordUser= user; 
		weakPasswordUser.setPassword("123");
		
		
		
		given(userRepo.existsByUsername(bornedIn2025.getUsername())).willReturn(false);
		given(userRepo.findUserByEmail(bornedIn2025.getEmail())).willReturn(Optional.empty());
		
		given(userRepo.existsByUsername(noUsername.getUsername())).willReturn(false);
		given(userRepo.findUserByEmail(noUsername.getEmail())).willReturn(Optional.empty());
		
		given(userRepo.existsByUsername(noEmailUser.getUsername())).willReturn(false);
		given(userRepo.findUserByEmail(noEmailUser.getEmail())).willReturn(Optional.empty());
		
		given(userRepo.existsByUsername(weakPasswordUser.getUsername())).willReturn(false);
		given(userRepo.findUserByEmail(weakPasswordUser.getEmail())).willReturn(Optional.empty());

		//given(roleRepo.findByRoleEnum(RoleEnum.CLIENT)).willReturn(Optional.of(roleClient));
		
		
		UserRegisterDTO borned2025Register = new UserRegisterDTO(bornedIn2025);
		UserRegisterDTO noUsernameRegister = new UserRegisterDTO(noUsername);
		UserRegisterDTO noEmailUserRegister = new UserRegisterDTO(noEmailUser);
		UserRegisterDTO weakPasswordRegister = new UserRegisterDTO(weakPasswordUser);

		assertThrows(InconsitentDataException.class, ()->{
			userService.createUser(borned2025Register);
		});
		assertThrows(InconsitentDataException.class, ()->{
			userService.createUser(noUsernameRegister);
		});
		
		assertThrows(InconsitentDataException.class, ()->{
			userService.createUser(noEmailUserRegister);
		});
		assertThrows(InconsitentDataException.class, ()->{
			userService.createUser(weakPasswordRegister);
		});
		
		
		verify(userRepo, never()).save(any(UserEntity.class));

	}

	@DisplayName("Test para  buscar los usuarios por su nombre ordenados por su nombre")
	@Test
	void searchUserByContainingName(){}

	@DisplayName("Test para buscar los usuarios por username")
	@Test
	void searchUserByContainingUsername(){}

	@DisplayName("Test para agregar una copia del producto al carrito de manera exitosa ")
	@Test
	 void addToCartTest_Success(){
		int price = 200;
		//product1.setPrice(price);
		int quantity = 2;
		int availableStock = 3;
		UserEntity testUser = new UserEntity();
		ShoppingCart shoppingCart = new ShoppingCart();
		int pricePerCopy = 250;
		int originalPrice= 2400;
		testUser.setShoppingCart(shoppingCart);
		Painting paintingTest1 = new Painting();
		paintingTest1.setName("StreetArt");
		paintingTest1.setPrice(originalPrice);
		paintingTest1.setPricePerCopy(pricePerCopy);
		paintingTest1.setAvailableStock(availableStock);


		paintingTest1.setAvailableStock(availableStock);
		Boolean isOriginalSelected = false;

		CartItem item = new CartItem(paintingTest1,quantity,isOriginalSelected);


		when(userRepo.findById(anyInt())).thenReturn(Optional.of(testUser));
		when(productsRepository.findById(anyInt())).thenReturn(Optional.of(paintingTest1));
		when(cartItemRepository.save(any(CartItem.class))).thenReturn(item);


		CartItemDTO result  = userService.addProductToCart(1,1, quantity,isOriginalSelected);

		verify(cartItemRepository, times(1)).save(any(CartItem.class));
		verify(shoppingCartRepository, times(1)).save(any(ShoppingCart.class));

		assertThat(result.getQuantity()).isEqualTo(quantity);
		assertThat(result.getIsOriginalSelected()).isEqualTo(isOriginalSelected);
		assertThat(paintingTest1.getAvailableStock() ).isEqualTo(availableStock-quantity);
		assertThat(result.getPricePerUnit()).isEqualTo(BigDecimal.valueOf(pricePerCopy));
		assertThat(testUser.getShoppingCart().getTotalAmount()).isEqualTo(BigDecimal.valueOf(pricePerCopy).multiply(BigDecimal.valueOf(quantity)));


	}
	@Test
	@DisplayName("Test para agregar una pintura original de manera exitosa se cambia isOriginalAvailable a falso")
	void addOriginalPaintToCartSuccess(){
		Integer quantity = 1;
		Integer originalPrice = 3000;
		Integer pricePerCopy = 250;
		Boolean isOriginalSelected= true;
		Painting paintingTest = new Painting();
		paintingTest.setAvailableStock(2);
		paintingTest.setIsOriginalAvailable(true);
		paintingTest.setPrice(originalPrice);
		paintingTest.setPricePerCopy(pricePerCopy);
		paintingTest.setAvailableCopies(2);

		ShoppingCart cart = new ShoppingCart();
		UserEntity user = new UserEntity();
		user.setShoppingCart(cart);


		CartItem cartItemResponse = new CartItem();
		cartItemResponse.setIsOriginalSelected(isOriginalSelected);
		cartItemResponse.setQuantity(quantity);
		cartItemResponse.setProduct(paintingTest);
		cartItemResponse.setPricePerUnit(BigDecimal.valueOf(paintingTest.getPrice()));

		given(cartItemRepository.save(any(CartItem.class))).willReturn(cartItemResponse);
		given(shoppingCartRepository.save(any(ShoppingCart.class))).willReturn(cart);
		given(userRepo.findById(anyInt())).willReturn(Optional.of(user));
		given(productsRepository.findById(anyInt())).willReturn(Optional.of(paintingTest));

		CartItemDTO cartItemDTO= userService.addProductToCart(1,1,quantity,isOriginalSelected);


		assertThat(cartItemDTO.getIsOriginalSelected()).isEqualTo(true);
		assertThat(paintingTest.getIsOriginalAvailable()).isEqualTo(false);
		assertThat(cartItemDTO.getPricePerUnit()).isEqualTo( BigDecimal.valueOf(originalPrice));


	}

	@Test
	@DisplayName("Test para intetar agragar una pintura original que no esta disponible la pieza orginal ")
	void addOriginalPaintingToCartRejected(
	){
		Boolean isOriginalSelected = true;
		Integer quantity = 1;
		Painting noOriginal = new Painting();
		noOriginal.setIsOriginalAvailable(false);

		UserEntity  newUser = new UserEntity();
		newUser.setShoppingCart(new ShoppingCart());

		when(productsRepository.findById(any())).thenReturn(Optional.of(noOriginal));
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(newUser));

	assertThrows(NoStockException.class, ()->{
		userService.addProductToCart(1,1,quantity,isOriginalSelected);
	});


	}
	@DisplayName("Test para evitar agregar un cartItem con valor 0 ")
	@Test
	void addToCart_inconsistentData(){
		int quantity = 0;


		assertThrows(InconsitentDataException.class, ()->{
			userService.addProductToCart(1,1,quantity,false);
		});



	}

	@DisplayName("Test para intentar agregar un producto con stock insuficiente al carrito ")
	@Test
	void addToCartTest_NoStockException(){
	int quantity =3;
	int availableStock = 2;
	product1.setAvailableStock(availableStock);
	product1.setPrice(200);
	Boolean isOriginalSelected= false;
	CartItemRegisterDTO request = new CartItemRegisterDTO(1,1, quantity,isOriginalSelected);
		when(productsRepository.findById(anyInt())).thenReturn(Optional.of(product1));
		when (userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		assertThrows(NoStockException.class, ()->{
			userService.addProductToCart(request);
		});



	}

	@DisplayName("Test para eliminar un cartItem de manera exitosa")
	@Test
	void deleteFromCart(){
			int priceProduct1= 200;
			int priceProduct2= 300;
			product1.setPrice(priceProduct1);
			product2.setPrice(priceProduct2);
			product2.setId(2);
		int quantity= 2;
		int quantityProduct2= 1;
		Boolean isOriginalSelected = false;
		 int availableStock = 5;

		CartItemRegisterDTO cartItemRegister  = new CartItemRegisterDTO(1, 1,quantity,isOriginalSelected);
		ShoppingCart cart = new ShoppingCart();
		user.setShoppingCart(cart);
		CartItem cartItem = new CartItem(product1, quantity,isOriginalSelected);
		CartItem cartItem2 = new CartItem(product2,quantityProduct2,false);
		product1.setAvailableStock(availableStock);
		product1.setId(1);
		cartItem.setProduct(product1);
		cartItem.setQuantity(quantity);
		cartItem.setIsOriginalSelected(isOriginalSelected);
		cartItem2.setProduct(product2);
		cartItem2.setQuantity(quantityProduct2);
		cartItem2.setIsOriginalSelected(false);

		cart.addCartItem(cartItem);
		cart.addCartItem(cartItem2);


		//System.out.println(product1.getAvailableStock());

		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		//when(productsRepository.findById(anyInt())).thenReturn(Optional.of(product1));


		userService.removeCartItemFromCart(cartItemRegister);

		assertThat(cart.getItemList()).doesNotContain(cartItem);
		assertThat(product1.getAvailableStock()).isEqualTo(availableStock);
		assertThat(cart.getTotalAmount()).isEqualTo(BigDecimal.valueOf(priceProduct2).multiply(BigDecimal.valueOf(quantityProduct2)));
		verify(userRepo, times(1)).save(any(UserEntity.class));


	}
	@DisplayName("Test para intentar eliminar un producto que no existe en el carrito")
	@Test
	void deleteCartItem_cartDoesNotExist(){
		// An user with no cartItems
		user.getShoppingCart().setItemList(Set.of());

		Boolean isOriginalSelected = false;
		CartItemRegisterDTO deleteRequest = new CartItemRegisterDTO(1,1,1,isOriginalSelected);
		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));

		assertThrows(ResourceNotFoundException.class,()->{
			userService.removeCartItemFromCart(deleteRequest);
		});

		verify(userRepo,never()).save(any(UserEntity.class));

	}

	@DisplayName("Test para agregar un producto a favoritos de un usuario")
	@Test
    void addProductToFavorites(){

		UserEntity user = new UserEntity();
		user.setFavoriteProducts(new HashSet<>());

		Product product = new Product();
		product.setName("favorite");
		ProductDTO productDto = new ProductDTO(product);
		ProductDTO foundProduct = new ProductDTO(new Product());

		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		when(productsRepository.findById(anyInt())).thenReturn(Optional.of(product));

		  Set<ProductDTO> productSet = userService.toggleProductToFavoriteFrom(1,1);

		  
		  Optional<ProductDTO> optionalFirstProduct = productSet.stream().findFirst();
		  if(optionalFirstProduct.isPresent()){
			  foundProduct = optionalFirstProduct.get();
		  }

		  assertThat(foundProduct.getName()).isEqualTo(productDto.getName());


	}
	@DisplayName("Test para quitar un producto de favoritos de un usuario")
	@Test
	void removeProductFromFavorites(){

		UserEntity user = new UserEntity();
		Product product = new Product();
		user.getFavoriteProducts().add(product);

		when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
		when(productsRepository.findById(anyInt())).thenReturn(Optional.of(product));

		Set<ProductDTO> productSet = userService.toggleProductToFavoriteFrom(1,1);

		assertThat(productSet).doesNotContain(new ProductDTO(product));

	}






	

}
