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
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import io.jsonwebtoken.security.Keys;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;

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


@Testcontainers
@SpringBootTest( classes = PaginaWebRufyanApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT /*, properties = {"spring.servlet.multipart.max-file-size=100MB",
        "spring.servlet.multipart.max-request-size=100MB"} */ )
@ActiveProfiles("test")
public class ProductControllerTest {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepositoryPort userRepositoryPort;

    @Value("spring.datasource.admin-password")
    private String adminPassword;


    UserDomain admin = new UserDomain(1L, new FullName("Rodrigo", "", "muñoz", "silva"), new BirthDate(LocalDate.of(1997,03,28)),"RufyanSilvaMuralista", "rufyanone@gmail.com", "contra123", RoleEnum.ROLE_ADMIN);

    UserDomain userBuyer = new UserDomain(2L, new FullName("Joel", "Alonso", "Martinez", "Pelaez"), new BirthDate(LocalDate.of(1987,03,28)),"PelaMa", "pelama@gmail.com", "contra2456");


    @Autowired
    JwtService jwtService;

    @Autowired
    AuthService authService;



    String tokenAdmin;
    String tokenUser;


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

    public ProductControllerTest() throws IOException {
    }

    @BeforeAll
    static void startDb(){


byte[] key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded();
       // System.out.println("contranesa: "+  Base64.getEncoder().encodeToString(key));

    }
    @BeforeEach
    void setUp(){

        UserDomain foundUser = userRepositoryPort.retrieveUserByEmail(admin.getEmail());

        System.out.println("usuario encontrado: "+ foundUser);

        TokenResponse login = authService.login(new LoginCommand(foundUser.getEmail(), adminPassword));


        tokenAdmin =  login.accessToken();
        tokenUser = jwtService.generateToken(userBuyer);




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
        String titulo1= "PRODUCT TEST 1";
        String titulo2= "product test 2";
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
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
        registry.add("spring.servlet.multipart.max-file-size", () -> "100MB");
        registry.add("spring.servlet.multipart.max-request-size", () -> "100MB");
    }










//    ProductSpecs productSpecs =  new ProductSpecs("Obra de prueba", "Una obra de prueba ");



    @Test
    @DisplayName("Test para probar la creación de un producto correctamente por parte del admin")
       public void shouldCreateAProduct() throws IOException {

System.out.println(" file 1 size: "+file1.getTotalSpace()+ " file 2 size: "+ file2.getTotalSpace());
        ProductDTO productDTO = RestAssured.given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .multiPart("images", "obra1.jpg", image1Bytes, "image/jpeg")
                .multiPart("images", "obra2.jpg", image2Bytes, "image/jpeg")
                .multiPart("command", "command.json",
                        objectMapper.writeValueAsBytes(product1Command),
                        "application/json")
                .when()
                .post("/products")
                .then()
                .statusCode(200)
                .extract()
                .as(ProductDTO.class);

    System.out.println(" new productDTO: " +  productDTO);
    }



    @Test
    @DisplayName("Test para probar la edición de un producto correctamente por parte del admin")
    public void shouldEditAProduct(){

    }
    @Test
    @DisplayName("Test para probar la eliminación de un producto correctamente por parte del admin")
    public void shouldDeleteAProduct(){

    }



    @Test
    @DisplayName("Test para evitar que algun cliente suba un producto")
    public void shouldThrowExceptionPostingProductWithTokenWithClientRole (){

    }


    @Test
    @DisplayName("Test para evitar que algun cliente elimine un")
    public void shouldThrowExceptionDeletingProductWithTokenWithClientRole (){

    }
    @Test
    @DisplayName("Test para evitar que alguien sin token edite un producto ")
    public void shouldThrowExceptionEditingProductWithNoToken(){

    }
    @Test
    @DisplayName("Test para evitar que alguien sin rol administrador edite un producto  ")
    public void shouldThrowExceptionEditingProductWithTokenWithClientRole (){

    }

    @Test
    @DisplayName("Test para comprobar el usuario en la base de datos evitar JWT artificiales ")
    public void shouldThrowExceptionPostingEditinOrDeletingProductWithArtificialJWT (){

    }
    @Test
    @DisplayName("Test para comprobar que la respuesta de una busqueda se un page")
    public void  shouldFindPagedProducts(){

        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract()
                .as(ProductDTO.class);
    }


}
