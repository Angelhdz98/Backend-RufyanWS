package com.example.PaginaWebRufyan.ControllerTests;

import com.example.PaginaWebRufyan.DTO.LoginCommand;
import com.example.PaginaWebRufyan.PaginaWebRufyanApplication;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.Security.Roles.RoleEnum;
import com.example.PaginaWebRufyan.Security.Service.AuthService;
import com.example.PaginaWebRufyan.Security.Service.TokenResponse;
import com.example.PaginaWebRufyan.Service.ShoppingCartServiceAdapter.AddCartItemService;
import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.CartItemCommand;
import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.ShoppingCartDTO;
import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.ShoppingCartDomainToDTO;
import com.example.PaginaWebRufyan.domain.model.*;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.ShoppingCartMapper;
import com.example.PaginaWebRufyan.domain.port.out.ShoppingCartRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest( classes = PaginaWebRufyanApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT /*, properties = {"spring.servlet.multipart.max-file-size=100MB",
        "spring.servlet.multipart.max-request-size=100MB"} */ )
@ActiveProfiles("test")
public class ShoppingCartControllerTest {

    @Autowired
    UserRepositoryPort userRepositoryPort;
    @Autowired
    ProductRepositoryPort productRepositoryPort;
    @Autowired
    ShoppingCartRepositoryPort shoppingCartRepositoryPort;
    @Value("${spring.datasource.user-buyer-password}")
    private String userBuyerPassword;
    @Value("${spring.datasource.user-buyer-email}")
    private String userBuyerEmail;
    @Value("${spring.datasource.admin-password}")
    private String adminPassword;


    @Autowired
    ProductDomainFactory productDomainFactory;
    @Autowired
    AuthService authService;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AddCartItemService addCartItemService;

    @Autowired
            ShoppingCartDomainToDTO shoppingCartDomainToDTO;

    String tokenAdmin;
    String tokenUser;

    static TokenResponse loginUser;
    static TokenResponse loginAdmin;

    @Autowired
    ImageInitializer imageInitializer;

    Set<MultipartFile> initialImages;
    Set<MultipartFile> editedImages;
    ProductDomain productDomain1;
    ProductDomain productDomain2;
    ShoppingCartDTO initialCartDTO;
    ShoppingCartDomain shoppingCartDomain;
    ShoppingCartDomain shoppingCartUpdated;
    ShoppingCartMapper shoppingCartMapper;
    UserDomain userBuyer;
    static UserDomain admin = new UserDomain(1L, new FullName("Rodrigo", "", "muñoz", "silva"), new BirthDate(LocalDate.of(1997,03,28)),"RufyanSilvaMuralista", "rufyanone@gmail.com", "contra123", RoleEnum.ROLE_ADMIN);

    @LocalServerPort
    private Integer port;

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.0")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){

        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username",mysql::getUsername);
        registry.add("spring.datasource.password",mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
        registry.add("spring.servlet.multipart.max-file-size", () -> "100MB");
        registry.add("spring.servlet.multipart.max-request-size", () -> "100MB");
    }




    @BeforeEach
    public void setUp() throws IOException {

        //Login users
        loginUser = authService.login(new LoginCommand(userBuyerEmail, userBuyerPassword));
        loginAdmin = authService.login(new LoginCommand(admin.getEmail(), adminPassword));


        tokenAdmin = loginAdmin.accessToken();

        tokenUser = loginUser.accessToken();

        //Initializing Images
        imageInitializer.readFiles();

        initialImages= imageInitializer.getSet1Files();
        editedImages = imageInitializer.getSet2Files();

        PaintingStockDTO paintingStockDTO =  new PaintingStockDTO();
        PaintingPricingDTO paintingPricingDTO = new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE);

// CREATING TEST PRODUCTS IN DATABASE WITH REPOSITORY PORT

        ProductSpecs productSpecs = new ProductSpecs("Obra de prueba 1", "obra de prueba con descripción de prueba",paintingStockDTO, paintingPricingDTO, ProductTypeEnum.PAINTING, true);
        PaintingDomainDetails paintingDomainDetails = new PaintingDomainDetails();

        ProductSpecs productSpecs2 = new ProductSpecs("Obra de prueba 2",
                "obra de prueba con descripción de prueba 2",
                new PaintingStockDTO(false,10,15), new PaintingPricingDTO(
                        new BigDecimal("2200"),new BigDecimal("450")
                            ),
                ProductTypeEnum.PAINTING,
                true);


        ProductDomain product1 = productDomainFactory.createProduct(productSpecs, paintingDomainDetails, initialImages);
        ProductDomain product2 = productDomainFactory.createProduct(productSpecs2, paintingDomainDetails, editedImages);

         productDomain1 = productRepositoryPort.saveProduct(product1);
         productDomain2 = productRepositoryPort.saveProduct(product2);
        RestAssured.baseURI = "http://localhost:" + port;
        RestAssured.port = port;

        //Getting the user shopping cart
        userBuyer = userRepositoryPort.retrieveUserByEmail(userBuyerEmail);
         shoppingCartDomain = shoppingCartRepositoryPort.retrieveShoppingCart(userBuyer.getId());
       // Making sure its empty to add a product
         shoppingCartDomain.getItems().clear();
         shoppingCartRepositoryPort.updateShoppingCart(userBuyer.getId(),shoppingCartDomain);
        //adding Item to shopping cart
        //shoppingCartDomain.addItem( new CartItemDomain(0L, product1, new PaintingItemDetails()));
        ShoppingCartDomain shoppingCartDomain1 = addCartItemService.addCartItem(new CartItemCommand(userBuyer.getId(), productDomain1.getId(), new PaintingItemDetails(1, true)));
        //shoppingCartUpdated = shoppingCartRepositoryPort.updateShoppingCart(userBuyer.getId(), shoppingCartDomain);

        initialCartDTO = shoppingCartDomainToDTO.toDTO(shoppingCartDomain1);

        ShoppingCartDomain shoppingCartDomainByRepo = shoppingCartRepositoryPort.retrieveShoppingCart(userBuyer.getId());
        System.out.println("Shopping cart find By repo in a beforeEach"+  shoppingCartDomainByRepo);

    }
    @Test
    @DisplayName("Test para probar la petición del carrito de compras")
    public void shouldGetUserShoppingCart() {
        ShoppingCartDTO findShoppingCart = RestAssured.given().header("Authorization", "Bearer " + tokenUser)
                .when().get("/shopping-cart").then().statusCode(200)
                .extract()
                .as(ShoppingCartDTO.class);

//        assertThat(findShoppingCart.cartItemDTOSet().size()).isEqualTo(initialCartDTO.cartItemDTOSet().size());
        ShoppingCartDomain shoppingCartByRepo = shoppingCartRepositoryPort.retrieveShoppingCart(userBuyer.getId());
        System.out.println("find shopping cart: "+findShoppingCart);
        System.out.println("Initial shopping cart: "+initialCartDTO);
    System.out.println("Shopping cart find by repository direct: "+ shoppingCartByRepo );
        System.out.println("Shopping cart find by repository to DTO: "+ shoppingCartDomainToDTO.toDTO(shoppingCartByRepo));
        assertThat(findShoppingCart.cartItemDTOSet().stream().findFirst().get().productName()).isEqualTo(initialCartDTO.cartItemDTOSet().stream().findFirst().get().productName());

    }


    @Test
    @DisplayName("Test para probar que un usuario pueda agregar varios productos a su carro de compras ")
    public void shouldAddProductToCart() throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
        ShoppingCartDTO productAddedShoppingCart = RestAssured.given().header("Authorization", "Bearer " + tokenUser)
                .multiPart("productId", productDomain2.getId(),"application/json")
                .multiPart("cartItemDetails", objectMapper.writeValueAsString(new PaintingDomainDetails()),"application/json")
                .when()
                .put("/shopping-cart/add-item").then().statusCode(200)
                .extract()
                .as(ShoppingCartDTO.class);


        assertThat(productAddedShoppingCart.cartItemDTOSet().size()).isEqualTo(2);



    }

    @Test
    @DisplayName("Test para probar que un usuario pueda quitar un item de su carrito de compras")
    public void shouldDeleteCartItemFromTheShoppingCart() throws JsonProcessingException {
        ShoppingCartDomain shoppingCartByRepo = shoppingCartRepositoryPort.retrieveShoppingCart(userBuyer.getId());
        System.out.println("Shopping cart size: "+shoppingCartByRepo.getItems().size());
       System.out.println("Shopping cart before deleting: "+ shoppingCartByRepo);
         Set<CartItemDomain> items = shoppingCartByRepo.getItems();
        CartItemDomain foundCartItemDomain = items.stream().findFirst().orElseThrow(() -> new RuntimeException("No se encontró el cart item"));



        UserDomain foundUserBuyer = userRepositoryPort.retrieveUserByEmail(userBuyerEmail);



        ShoppingCartDTO productDeletedShoppingCart = RestAssured.given()
                .header("Authorization", "Bearer " + tokenUser)
                .when()
                .put("/shopping-cart/remove-item/"+foundCartItemDomain.getId()).then().statusCode(200)
                .extract()
                .as(ShoppingCartDTO.class);

        assertThat(productDeletedShoppingCart.cartItemDTOSet().size()).isEqualTo(0);


    }



    @Test
    @DisplayName("Test para evitar que alguien sin autenticar agregue  items del  shopping cart ")
    public void shouldThrowExceptionAddingCartItemsWithNoClientRole() throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
        RestAssured.given().header("Authorization", "Bearer " + tokenAdmin)
                .multiPart("productId", productDomain2.getId())
                .multiPart("cartItemDetails", objectMapper.writeValueAsString( new PaintingDomainDetails()) )
                .when()
                .put("/shopping-cart/add-item").then().statusCode(403);

    }
    @Test
    @DisplayName("Test para evitar que alguien sin autenticar elimine items del  shopping cart ")
    public void shouldThrowExceptionDeletingCartItemsWithNoClientRole(){

        Set<CartItemDomain> items = shoppingCartRepositoryPort.retrieveShoppingCart(userBuyer.getId()).getItems();
        CartItemDomain foundCartItemDomain = items.stream().findFirst().orElseThrow(() -> {
            return new RuntimeException("No se encontró el cart item");
        });

        UserDomain foundUserBuyer = userRepositoryPort.retrieveUserByEmail(userBuyerEmail);



                RestAssured.given()
                        .header("Authorization", "Bearer " + tokenAdmin)
                .when()
                .put("/shopping-cart/remove-item/"+foundCartItemDomain.getId()).then().statusCode(403);



    }




}
