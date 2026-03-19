package com.example.PaginaWebRufyan.ControllerTests;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.LoginCommand;
import com.example.PaginaWebRufyan.DTO.RegisterUserDTO;
import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.PaginaWebRufyanApplication;
import com.example.PaginaWebRufyan.Security.Roles.RoleEnum;
import com.example.PaginaWebRufyan.Security.Service.AuthService;
import com.example.PaginaWebRufyan.Security.Service.TokenResponse;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = PaginaWebRufyanApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Value("${spring.datasource.admin-password}")
    private String adminPassword;

    @Autowired
    private ImageInitializer imageInitializer;

    @Autowired
    private AuthService authService;

    @Autowired
    UserDataInitializer userDataInitializer;

    static UserDomain admin = new UserDomain(1L, new FullName("Rodrigo", "", "muñoz", "silva"), new BirthDate(LocalDate.of(1997, 03, 28)), "RufyanSilvaMuralista", "rufyanone@gmail.com", "contra123", RoleEnum.ROLE_ADMIN);

    @Value("${spring.datasource.user-buyer-password}")
    private String userBuyerPassword;
    @Value("${spring.datasource.user-buyer-email}")
    private String userBuyerEmail;


    String tokenAdmin;
    String tokenUser;

    static TokenResponse loginUser;
    static TokenResponse loginAdmin;
    String email = "plonatbike@gmail.com";
    String newUserPassword = "myPassword123";

    String username = "PlonAngel";
    private CreateUserCommand createUserCommand;

    {
        LocalDate birthDate = LocalDate.of(1989, 11, 20);
        createUserCommand = new CreateUserCommand(email,
                newUserPassword,
                username, new FullName("Arturo", "", "Manriquez", "Guzman"), birthDate);
    }

    ;


    @LocalServerPort
    private Integer port;
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0").withDatabaseName("testdb").withUsername("test").withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
        registry.add("spring.servlet.multipart.max-file-size", () -> "100MB");
        registry.add("spring.servlet.multipart.max-request-size", () -> "100MB");
    }

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        RestAssured.port = port;
        loginUser = authService.login(new LoginCommand(userBuyerEmail, userBuyerPassword));
        loginAdmin = authService.login(new LoginCommand(admin.getEmail(), adminPassword));


        tokenAdmin = loginAdmin.accessToken();

        tokenUser = loginUser.accessToken();
    }

    @Test
    @DisplayName("Un usuario se puede registrar de manera correcta")
    public void shouldRegisterAnUserCorrectly() throws JsonProcessingException {


        RegisterUserDTO responseUser = RestAssured.given()
                .multiPart("createUserCommand", "createUserCommand.json", objectMapper.writeValueAsString(createUserCommand), "application/json")
                .post("/auth/user-register")
                .then()
                .statusCode(200)
                .extract().as(RegisterUserDTO.class);

        assertThat(responseUser.userEntityDTO2().email()).isEqualTo(email);
        assertThat(responseUser.userEntityDTO2().username()).isEqualTo(username);


    }

    @Test
    @DisplayName("Se evita que se genere un registro cuando ya se está autenticado")
    public void shouldThrowExceptionRegisteringWhileAuthenticated() throws JsonProcessingException {


        RestAssured.given().header("Authorization", "Bearer " + tokenUser)
                .multiPart("createUserCommand",
                        "createUserCommand.json",
                        objectMapper.writeValueAsString(createUserCommand), "application/json")
                .post("/auth/user-register")
                .then().statusCode(403);

    }

    /*
    @Test
    @DisplayName("test para comprobar que un usuario pueda eliminar su propia cueenta de manera correcta ")
    public void shouldDeleteUserHisOwnAccount(){
        RestAssured.given()
                .header("Authorization", "Bearer "+tokenUser)
                .delete("/users")
                .then()
                .statusCode(200);

    }

     */
    @Test
    @DisplayName("Test para comprobar que un usuario pueda editar sus datos de manera correcta ")
    public void shouldEditUserHisOwnAccount() throws JsonProcessingException {

        UserDomain userDomainToEdit = userRepositoryPort.retrieveUserByEmail(userBuyerEmail);

        String editedPassword = newUserPassword + "456";
        String editedUsername = username + "masmaduro";
        LocalDate editedBirthDate = LocalDate.of(1990, 11, 10);
        FullName editedName = new FullName("Marco", "Arturo", "Sandoval", "Manriquez");
        CreateUserCommand editUserCommand = new CreateUserCommand(email, editedPassword, editedUsername, editedName, editedBirthDate);

        UserEntityDTO2 editedUser = RestAssured.given().header("Authorization", "Bearer " + tokenUser)
                .multiPart("createUserCommand",
                        "createUserCommand.json",
                        objectMapper.writeValueAsString(editUserCommand), "application/json")
                .put("/users")
                .then().statusCode(200)
                .extract().as(UserEntityDTO2.class);

        assertThat(editedUser.username()).isEqualTo(editedUsername);
        assertThat(editedUser.fullname()).isEqualTo(editedName.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate testDate = LocalDate.parse(editedUser.birthDate(), formatter);
        assertThat(testDate).isEqualTo(editedBirthDate);

    }

    @Test
    @DisplayName("el admin pueda eliminar a cualquier otro usuario ")
    public void shouldDeleteAnyUserByAdmin() throws Exception {
        UserDomain userToDelete = userRepositoryPort.retrieveUserByEmail(userBuyerEmail);
        UserDomain adminUser = userRepositoryPort.retrieveUserById(1L);
        System.out.println("admin user despues de que el admin haya cambiado la contraseña: " + adminUser);
        RestAssured.given().header("Authorization", "Bearer " + tokenAdmin)
                .delete("/users/" + userToDelete.getId())
                .then().statusCode(200);
        assertThrows(ResourceNotFoundException.class, () -> {
            userRepositoryPort.retrieveUserByEmail(userBuyerEmail);
        });

        userDataInitializer.run();


    }

    @Test
    @DisplayName("un cliente no pueda eliminar la cuenta de algún otro usuario ")
    public void shouldDenyDeletingAUserByAClientUser() {
        UserDomain userToDelete = userRepositoryPort.retrieveUserByEmail(userBuyerEmail);
        RestAssured.given().header("Authorization", "Bearer " + tokenUser)
                .delete("/users" + userToDelete.getId())
                .then().statusCode(403);

    }

    @Test
    @DisplayName("El cliente puede actualizar su contraseña")
    public void shouldEditPasswordUser() {
        String newPassword = "newPassword123";
        RestAssured.given()
                .header("Authorization", "Bearer " + tokenUser)
                .multiPart("oldPassword", userBuyerPassword)
                .multiPart("newPassword", newPassword)
                .multiPart("newPasswordConfirmation", newPassword)
                .put("/users/update-password")
                .then()
                .statusCode(200);
        /*

         */
        assertThrows(BadCredentialsException.class, () -> {
            TokenResponse loginUser = authService.login(new LoginCommand(userBuyerEmail, userBuyerPassword));
            //Resturn Back password

        });

        TokenResponse finalLoginUser = authService.login(new LoginCommand(userBuyerEmail, newPassword));


        RestAssured.given()
                .header("Authorization", "Bearer " + finalLoginUser.accessToken())
                .multiPart("oldPassword", newPassword)
                .multiPart("newPassword", userBuyerPassword)
                .multiPart("newPasswordConfirmation", userBuyerPassword)
                .put("/users/update-password")
                .then()
                .statusCode(200);


    }


    @Test
    @DisplayName("El administrador puede actualizar su contraseña")
    public void shouldEditPasswordAdmin() {

        String newPassword = "newPassword123";

        // cambiar password
        RestAssured.given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .multiPart("oldPassword", adminPassword)
                .multiPart("newPassword", newPassword)
                .multiPart("newPasswordConfirmation", newPassword)
                .put("/users/update-password")
                .then()
                .statusCode(200);

        // verificar que el login con contraseña vieja falla
        assertThrows(BadCredentialsException.class, () -> {
            authService.login(new LoginCommand(admin.getEmail(), adminPassword));
        });

        // login con nueva contraseña
        TokenResponse correctLogin = authService.login(
                new LoginCommand(admin.getEmail(), newPassword)
        );

        // turn back password
        RestAssured.given()
                .header("Authorization", "Bearer " + correctLogin.accessToken())
                .multiPart("oldPassword", newPassword)
                .multiPart("newPassword", adminPassword)
                .multiPart("newPasswordConfirmation", adminPassword)
                .put("/users/update-password")
                .then()
                .statusCode(200);

    }
    @Test
    @DisplayName("El admin puede actualizar su email")
    public void shouldChangeAdminEmail(){

        String newEmail = "nuevo_email_rufyan@gmail.com";


        UserEntityDTO2 response = RestAssured.given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .multiPart("newEmail", newEmail)
                .multiPart("password", adminPassword)
                .put("/users/update-email")
                .then()
                .statusCode(200)
                .extract().as(UserEntityDTO2.class);

        assertThat(response.email()).isEqualTo(newEmail);
// turn back email
        String newTokenAdmin = authService.login(new LoginCommand(response.email(),adminPassword)).accessToken();

        UserEntityDTO2 response2 = RestAssured.given()
                .header("Authorization", "Bearer " + newTokenAdmin)
                .multiPart("newEmail", admin.getEmail())
                .multiPart("password", adminPassword)
                .put("/users/update-email")
                .then()
                .statusCode(200)
                .extract().as(UserEntityDTO2.class);


    }
    @Test
    @DisplayName("El admin no puede actualizar su email sin indicar la contraseña correcta")
    public void shouldThrowExceptionChangingAdminEmailWithNoPassword(){

        String newEmail = "nuevo_email_rufyan@gmail.com";


        String wrongPassword = adminPassword + "jklsj";
         RestAssured.given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .multiPart("newEmail", newEmail)
                .multiPart("password", wrongPassword)
                .put("/users/update-email")
                .then()
                .statusCode(403);


    }

    @Test
    @DisplayName("El cliente puede cambiar su email de manera correcta")
    public void shouldChangeClientEmail(){
        String newEmail = "nuevo_email_usuario@gmail.com";


        UserEntityDTO2 response = RestAssured.given()
                .header("Authorization", "Bearer " + tokenUser)
                .multiPart("newEmail", newEmail)
                .multiPart("password", userBuyerPassword)
                .put("/users/update-email")
                .then()
                .statusCode(200)
                .extract().as(UserEntityDTO2.class);

        assertThat(response.email()).isEqualTo(newEmail);
        String newTokenUser = authService
                                    .login(new LoginCommand(newEmail,userBuyerPassword)).accessToken();

        UserEntityDTO2 response2 = RestAssured.given()
                .header("Authorization", "Bearer " + newTokenUser)
                .multiPart("newEmail", userBuyerEmail)
                .multiPart("password", userBuyerPassword)
                .put("/users/update-email")
                .then()
                .statusCode(200)
                .extract().as(UserEntityDTO2.class);

    }
    @Test
    @DisplayName("El admin no puede actualizar su email sin indicar la contraseña correcta")
    public void shouldThrowExceptionChangingClientEmailWithNoPassword(){

        String newEmail = "nuevo_email_rufyan@gmail.com";


        String wrongPassword = userBuyerPassword + "jklsj";
        RestAssured.given()
                .header("Authorization", "Bearer " + tokenUser)
                .multiPart("newEmail", newEmail)
                .multiPart("password", wrongPassword)
                .put("/users/update-email")
                .then()
                .statusCode(403);


    }

}



