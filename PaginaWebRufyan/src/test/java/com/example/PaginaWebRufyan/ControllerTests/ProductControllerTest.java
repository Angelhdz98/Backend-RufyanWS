package com.example.PaginaWebRufyan.ControllerTests;

import com.example.PaginaWebRufyan.DTO.LoginCommand;
import com.example.PaginaWebRufyan.PaginaWebRufyanApplication;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.Security.Roles.RoleEnum;
import com.example.PaginaWebRufyan.Security.Service.AuthService;
import com.example.PaginaWebRufyan.Security.Service.JwtService;
import com.example.PaginaWebRufyan.Security.Service.TokenResponse;
import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.model.ImageStorageProperties;
import com.example.PaginaWebRufyan.domain.model.PaintingDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.CreateProductCommand;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductCommand;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.security.Keys;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
@SpringBootTest( classes = PaginaWebRufyanApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT /*, properties = {"spring.servlet.multipart.max-file-size=100MB",
        "spring.servlet.multipart.max-request-size=100MB"} */ )
@ActiveProfiles("test")
public class ProductControllerTest {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Value("${spring.datasource.admin-password}")
     private String adminPassword;



  static  UserDomain admin = new UserDomain(1L, new FullName("Rodrigo", "", "muñoz", "silva"), new BirthDate(LocalDate.of(1997,03,28)),"RufyanSilvaMuralista", "rufyanone@gmail.com", "contra123", RoleEnum.ROLE_ADMIN);




    @Autowired
    JwtService jwtService;

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Value("${spring.datasource.user-buyer-password}")
    private String userBuyerPassword;
    @Value("${spring.datasource.user-buyer-email}")
    private String userBuyerEmail;

    String tokenAdmin;
    String tokenUser;

    static TokenResponse loginUser;
    static TokenResponse loginAdmin;

    Set<File> initialImages= new HashSet<>();
    Set<File> editedImages= new HashSet<>();
    ClassPathResource resource1  =new ClassPathResource("images/obra1.jpg");
    ClassPathResource resource2  =new ClassPathResource("images/obra2.jpg");
    ClassPathResource resource3  =new ClassPathResource("images/obra3.jpg");
    File file1;
    File file2;
    File file3;
    File file4;
    ProductSpecs productSpecs1;
    ProductSpecs productSpecs2;
    ProductSpecs productSpecsUpdated;
    ProductDomain defaultProduct1;
    ProductDomain defaultProduct2;
    ProductDomain bodyClothingProduct;
    ProductDomain defaultProductUpdated;
    Integer stockCopies =5;
    Integer copiesMade = 10;
    Boolean isOriginalAvailable= true;

    String titulo1= "PRODUCT TEST 1";
    String titulo2= "product test 2";
    CreateProductCommand product1Command;
    CreateProductCommand product2Command;
    Path p1;
    Path p2;

    byte[] image1Bytes = new byte[50*1024];
    byte[] image2Bytes = new byte[50*1024];
    byte[] image3Bytes = new byte[50*1024];
    byte[] image4Bytes = new byte[50*1024];




    @LocalServerPort
    private Integer port;

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.0")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");



    ImageStorageProperties imageStorageProperties;


    @BeforeAll
    static void startDb(){


byte[] key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded();
       // System.out.println("contranesa: "+  Base64.getEncoder().encodeToString(key));;




    }
    @BeforeEach
    void setUp(){

        loginUser = authService.login(new LoginCommand(userBuyerEmail, userBuyerPassword));
        loginAdmin = authService.login(new LoginCommand(admin.getEmail(), adminPassword));


        tokenAdmin = loginAdmin.accessToken();

        tokenUser = loginUser.accessToken();




        try {
            //Firs Read the image content
            file1 = resource1.getFile();
            file2 = resource2.getFile();
            file3 = resource3.getFile();
            file4 = resource1.getFile();
            p1 = resource1.getFile().toPath();
            p2 = resource2.getFile().toPath();
            image1Bytes = Files.readAllBytes(p1);
            image1Bytes = Files.readAllBytes(p2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //saving image content on a mockMultiparfile
        /*file1 = new MockMultipartFile("images",
                "test-image.jpg", MediaType.IMAGE_JPEG_VALUE, image1Bytes);
        file2 = new MockMultipartFile("images",
                "test-image2.jpg", MediaType.IMAGE_JPEG_VALUE, image2Bytes);
        file3 = new MockMultipartFile("images",
                "test-image3.jpg", MediaType.IMAGE_JPEG_VALUE, image3Bytes);
        file4 = new MockMultipartFile("images",
                "test-image4.jpg", MediaType.IMAGE_JPEG_VALUE, image4Bytes);

         */
        //creating commands for new  products
        initialImages = Set.of(file1, file2);
        editedImages = Set.of( file3, file4);

        String description1 = "description product";
        String description2 = "description product2";
         productSpecs1 = new ProductSpecs(titulo1, description1,
                new PaintingStockDTO(true, PaintingStockManager.DEFAULT_STOCK_COPIES, PaintingStockManager.DEFAULT_COPIES_MADE, StockEnum.PAINTING), new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE, PricingTypeEnum.ORIGINAL ), ProductTypeEnum.PAINTING, true );
         productSpecs2 = new ProductSpecs(titulo2, description2,
                new PaintingStockDTO(true, PaintingStockManager.DEFAULT_STOCK_COPIES+2, PaintingStockManager.DEFAULT_COPIES_MADE+2, StockEnum.PAINTING), new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE.add(BigDecimal.valueOf(100)),  PaintingPriceManager.MIN_COPY_PRICE.add(BigDecimal.valueOf(100)), PricingTypeEnum.ORIGINAL ), ProductTypeEnum.PAINTING, false );
        productSpecsUpdated = new ProductSpecs(titulo1.toLowerCase(),description1,  new PaintingStockDTO(true, PaintingStockManager.DEFAULT_STOCK_COPIES, PaintingStockManager.DEFAULT_COPIES_MADE, StockEnum.PAINTING), new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE, PricingTypeEnum.ORIGINAL), ProductTypeEnum.PAINTING, true );




        product1Command = new CreateProductCommand(productSpecs1, new PaintingDomainDetails(PaintingDomainDetails.MIN_HEIGHT_CM, PaintingDomainDetails.MIN_LARGE_CM, MediumEnum.OIL_PAINT, SupportMaterialEnum.COTTON_PAPER, LocalDate.of(2025, 11,20)));

        product2Command = new CreateProductCommand(productSpecs2,  new PaintingDomainDetails(PaintingDomainDetails.MIN_HEIGHT_CM, PaintingDomainDetails.MIN_LARGE_CM, MediumEnum.OIL_PAINT, SupportMaterialEnum.COTTON_PAPER, LocalDate.of(2024, 6,10)));


        RestAssured.baseURI = "http://localhost:" + port;
        RestAssured.port = port;
       /* RestAssured.filters(
                new RequestLoggingFilter(LogDetail.ALL),
                new ResponseLoggingFilter(LogDetail.ALL)
        );
        */


        // guardo el admin


    }


    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){

        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username",mysql::getUsername);
        registry.add("spring.datasource.password",mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
        registry.add("spring.servlet.multipart.max-file-size", () -> "100MB");
        registry.add("spring.servlet.multipart.max-request-size", () -> "100MB");
    }










//    ProductSpecs productSpecs =  new ProductSpecs("Obra de prueba", "Una obra de prueba ");

    public ProductDTO postProduct(String token, Integer statusCode) throws JsonProcessingException {

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .multiPart("images", "obra1.jpg", image1Bytes, "image/jpeg")
                .multiPart("images", "obra2.jpg", image2Bytes, "image/jpeg")
                .multiPart("command", "command.json",
                        objectMapper.writeValueAsBytes(product1Command),
                        "application/json")
                .when()
                .post("/products")
                .then()
                .statusCode(statusCode)
                .extract()
                .as(ProductDTO.class);


    }



    @Test
    @DisplayName("Test para probar la creación de un producto correctamente por parte del admin")
       public void shouldCreateAProduct() throws IOException {

//System.out.println(" file 1 size: "+file1.getTotalSpace()+ " file 2 size: "+ file2.getTotalSpace());
        postProduct(tokenAdmin,200);

//    System.out.println(" new productDTO: " +  productDTO);
    }



    @Test
    @DisplayName("Test para probar la edición de un producto correctamente por parte del admin")
    public void shouldEditAProduct() throws JsonProcessingException {
        ProductDTO productCreated = postProduct(tokenAdmin,200);

        Set<ImageDomain> productImages = productCreated.images();

        UpdateProductCommand updateProductCommand = new UpdateProductCommand(productCreated.id(),productSpecsUpdated, productCreated.productDetails(),  productImages );

        ProductDTO updatedProductDto = RestAssured.given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .multiPart("addedImages", "obra3.jpg", image3Bytes, "image/jpeg")
                .multiPart("updateCommand", "command.json",
                        objectMapper.writeValueAsBytes(updateProductCommand),
                        "application/json")
                .when()
                .put("/products")
                .then()
                .statusCode(200).extract().as(ProductDTO.class);

        assertThat(updatedProductDto.name()).isEqualTo(titulo1.toLowerCase());
        assertThat(updatedProductDto.images().size()).isEqualTo(3);


    }
    @Test
    @DisplayName("Test para probar la eliminación de un producto correctamente por parte del admin")
    public void shouldDeleteAProduct() throws JsonProcessingException {
        ProductDTO productCreatedDTO = postProduct(tokenAdmin, 200);

         RestAssured.given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .when()
                .delete("/products/" + productCreatedDTO.id().toString())
                .then()
                .statusCode(200);

         RestAssured.when().get("/products/" + productCreatedDTO.id()).then().statusCode(404);

    }



    @Test
    @DisplayName("Test para evitar que algun cliente suba un producto")
    public void shouldThrowExceptionPostingProductWithTokenWithClientRole () throws JsonProcessingException {

                 RestAssured.given()
                .header("Authorization", "Bearer " + tokenUser)
                .multiPart("images", "obra1.jpg", image1Bytes, "image/jpeg")
                .multiPart("images", "obra2.jpg", image2Bytes, "image/jpeg")
                .multiPart("command", "command.json",
                        objectMapper.writeValueAsBytes(product1Command),
                        "application/json")
                .when()
                .post("/products")
                .then()
                .statusCode(403);



    }

    @Test
    @DisplayName("Test para encontrar una obra usando el controlador ")
    public void shouldFindAProductById() throws JsonProcessingException {
        ProductDTO productCreated = postProduct(tokenAdmin,  200);

        ProductDTO foundProduct = RestAssured.when().get("/products/" + productCreated.id())
                .then()
                .statusCode(200)
                .extract().as(ProductDTO.class);
        assertThat(foundProduct.name()).isEqualTo(productCreated.name());

    }

    @Test
    @DisplayName("Test para evitar que algun cliente elimine un producto")
    public void shouldThrowExceptionDeletingProductWithTokenWithClientRole () throws JsonProcessingException {
        ProductDTO productDTO = postProduct(tokenAdmin, 200);

        RestAssured.given()
                .header("Authorization", "Bearer" + tokenUser)
                .when()
                .delete("/products/" + productDTO.id())
                .then()
                .statusCode(403);

    }
    @Test
    @DisplayName("Test para evitar que alguien sin rol admin edite un producto ")
    public void shouldThrowExceptionEditingProductWithNoToken() throws JsonProcessingException {
        ProductDTO productCreated = postProduct(tokenAdmin,200);

        Set<ImageDomain> productImages = productCreated.images();

        UpdateProductCommand updateProductCommand = new UpdateProductCommand(productCreated.id(),productSpecsUpdated, productCreated.productDetails(),  productImages );

RestAssured.given().when()
        .header("Authorization", "Bearer: "+ tokenUser)
                .multiPart("addedImages", "obra3.jpg", image3Bytes, "image/jpeg")
                .multiPart("updateCommand", "command.json",
                        objectMapper.writeValueAsBytes(updateProductCommand),
                        "application/json")
                .when()
                .put("/products")
                .then()
                .statusCode(403);



    }



    @Test
    @DisplayName("Test para comprobar que la respuesta de una busqueda se un page")
    public void  shouldFindPagedProducts(){

        PageResponse<ProductDTO> response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<PageResponse<ProductDTO>>() {});

        assertThat(response.getContent().size()).isEqualTo(1);
        assertThat(response.getContent().get(0).name()).isEqualTo(product1Command.productSpecs().name());


    }


}
