package com.example.PaginaWebRufyan.ControllerTests;

import com.example.PaginaWebRufyan.DTO.LoginCommand;
import com.example.PaginaWebRufyan.PaginaWebRufyanApplication;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.Security.Roles.RoleEnum;
import com.example.PaginaWebRufyan.Security.Service.AuthService;
import com.example.PaginaWebRufyan.Security.Service.JwtService;
import com.example.PaginaWebRufyan.Security.Service.TokenResponse;
import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.adapter.in.ProductLikedDto;
import com.example.PaginaWebRufyan.domain.model.PaintingDomainDetails;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.CreateProductCommand;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Testcontainers
@SpringBootTest( classes = PaginaWebRufyanApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT /*, properties = {"spring.servlet.multipart.max-file-size=100MB",
        "spring.servlet.multipart.max-request-size=100MB"} */ )
@ActiveProfiles("test")
public class LikesControllerTest {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Value("${spring.datasource.admin-password}")
    private String adminPassword;

    @Autowired
    private ImageInitializer imageInitializer;


    static UserDomain admin = new UserDomain(1L, new FullName("Rodrigo", "", "muñoz", "silva"), new BirthDate(LocalDate.of(1997,03,28)),"RufyanSilvaMuralista", "rufyanone@gmail.com", "contra123", RoleEnum.ROLE_ADMIN);




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

    Set<MultipartFile> initialImages= new HashSet<>();
    Set<MultipartFile> editedImages= new HashSet<>();


    ProductSpecs productSpecs1;
    ProductSpecs productSpecs2;
    ProductSpecs productSpecsUpdated;


    String titulo1= "PRODUCT TEST 1";
    String titulo2= "product test 2";
    CreateProductCommand product1Command;
    CreateProductCommand product2Command;
    Path p1;
    Path p2;





    @LocalServerPort
    private Integer port;

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.0")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");





    @BeforeAll
    static void startDb(){


        //byte[] key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded();
        // System.out.println("contranesa: "+  Base64.getEncoder().encodeToString(key));;




    }
    @BeforeEach
    void setUp() throws IOException {

        loginUser = authService.login(new LoginCommand(userBuyerEmail, userBuyerPassword));
        loginAdmin = authService.login(new LoginCommand(admin.getEmail(), adminPassword));


        tokenAdmin = loginAdmin.accessToken();

        tokenUser = loginUser.accessToken();

        imageInitializer.readFiles();


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
        initialImages = imageInitializer.getSet1Files();
        editedImages = imageInitializer.getSet2Files();

        String description1 = "description product";
        String description2 = "description product2";
        productSpecs1 = new ProductSpecs(titulo1, description1,
                new PaintingStockDTO(true, PaintingStockManager.DEFAULT_STOCK_COPIES, PaintingStockManager.DEFAULT_COPIES_MADE, StockEnum.PAINTING_STOCK), new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE, PricingTypeEnum.ORIGINAL ), ProductTypeEnum.PAINTING, true );
        productSpecs2 = new ProductSpecs(titulo2, description2,
                new PaintingStockDTO(true, PaintingStockManager.DEFAULT_STOCK_COPIES+2, PaintingStockManager.DEFAULT_COPIES_MADE+2, StockEnum.PAINTING_STOCK), new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE.add(BigDecimal.valueOf(100)),  PaintingPriceManager.MIN_COPY_PRICE.add(BigDecimal.valueOf(100)), PricingTypeEnum.ORIGINAL ), ProductTypeEnum.PAINTING, false );
        productSpecsUpdated = new ProductSpecs(titulo1.toLowerCase(),description1,  new PaintingStockDTO(true, PaintingStockManager.DEFAULT_STOCK_COPIES, PaintingStockManager.DEFAULT_COPIES_MADE, StockEnum.PAINTING_STOCK), new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE, PricingTypeEnum.ORIGINAL), ProductTypeEnum.PAINTING, true );




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
    public ProductDTO postProduct(String token, Integer statusCode) throws JsonProcessingException {

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .multiPart("images", "obra1.jpg", imageInitializer.getImage1Bytes(), "image/jpeg")
                .multiPart("images", "obra2.jpg", imageInitializer.getImage2Bytes(), "image/jpeg")
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
    @DisplayName("Test para marcar un producto como me gusta de manera correcta ")
    public void shouldMarkAProductAsLikedCorrectly() throws JsonProcessingException {
        ProductDTO product = postProduct(tokenAdmin, 200);

        UserDomain userClient = userRepositoryPort.retrieveUserByEmail(userBuyerEmail);

        RestAssured.given()
                .header("Authorization","Bearer "+tokenUser)
                .when()
                .contentType(ContentType.JSON)
                .post("/like/"+product.id())
                .then()
                .statusCode(200).extract().as(ProductLikedDto.class);

    }
    @Test
    @DisplayName("Test para desmarcar un producto como me gusta de manera correcta ")
    public void shouldUnmarkAProductAsLikedCorrectly() throws JsonProcessingException {
        ProductDTO product = postProduct(tokenAdmin, 200);

        UserDomain userClient = userRepositoryPort.retrieveUserByEmail(userBuyerEmail);

        ProductLikedDto productLikedDto = RestAssured.given()
                .header("Authorization", "Bearer " + tokenUser)
                .contentType(ContentType.JSON)
                .when()
                .post("/like/"+product.id())
                .then()
                .extract().as(ProductLikedDto.class);


        RestAssured.given()
                .header("Authorization", "Bearer " + tokenUser)
                .contentType(ContentType.JSON)
                .when()
                .delete("/like/"+product.id())
                .then()
                .statusCode(204);




    }

    @Test
    @DisplayName("Test para evitar que usuarios no autenticados editen los me gusta")
    public void shouldThrowExceptionEditingLikesWithInvalidJWT() throws JsonProcessingException {
        ProductDTO product = postProduct(tokenAdmin, 200);

        UserDomain userClient = userRepositoryPort.retrieveUserByEmail(userBuyerEmail);

        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)

                .post("/like/"+ product.id())
                .then()
                .statusCode(403);

    }

}

