package com.example.PaginaWebRufyan.serviceTest;

import com.example.PaginaWebRufyan.Security.Roles.RoleEnum;
import com.example.PaginaWebRufyan.Security.Service.JwtService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class JWTServiceTest {


    private JwtService jwtServiceTest;
    UserDomain userClientTest =   new UserDomain(0L, new FullName("user","Client","test",""),new BirthDate(LocalDate.of(1998,1,3)),"PlonAngel98","plonatbike@gmail.com","contraseñaHasheada");
    UserDomain userAdminTest =   new UserDomain(0L, new FullName("User","Admin","Test",""),new BirthDate(LocalDate.of(1998,1,3)),"PlonAngelAdmin98", "hernandeztroeesjoseangel@gmail.com","contraseñaHasheada2", RoleEnum.ROLE_ADMIN);
    String tokenClient=null;
    String tokenAdmin =null;


    @BeforeEach
    public void init(){



         jwtServiceTest = new JwtService( "TESTSECRETKEYSUPERLARGADE32OMASCARACTERES123456asdasd",
                3600000, 3600000*24 );
        tokenClient = jwtServiceTest.generateToken(userClientTest);
        tokenAdmin = this.jwtServiceTest.generateToken(userAdminTest);

    }
    @Test
    @DisplayName("Test para generar un jwt de cliente")
    public void shouldGenerateJWT(){

        assertTrue(tokenClient.startsWith("ey"));
        assertNotNull(tokenClient);
        assertTrue(tokenAdmin.startsWith("ey"));
        assertNotNull(tokenAdmin);
    }


    @Test
    @DisplayName("Test para extraer información del token de cliente")
    public void shouldExtractClientInfo(){
    //name role

        assertThat(jwtServiceTest.extractEmail(tokenClient)).isEqualTo(userClientTest.getEmail());
        assertThat(jwtServiceTest.extractRole(tokenClient)).isEqualTo(userClientTest.getRole().name());
        assertThat(jwtServiceTest.extractClaims(tokenClient).get("name")).isEqualTo(userClientTest.getFullname().getFullName());


    }

    @Test
    @DisplayName("Test para extraer información del token de administrador")
    public void shouldExtractAdminInfo(){
        //name role

        assertThat(jwtServiceTest.extractEmail(tokenAdmin)).isEqualTo(userAdminTest.getEmail());
        assertThat(jwtServiceTest.extractRole(tokenAdmin)).isEqualTo(userAdminTest.getRole().name());
        assertThat(jwtServiceTest.extractClaims(tokenAdmin).get("name")).isEqualTo(userAdminTest.getFullname().getFullName());

    }
    @Test
    @DisplayName("Debe detectar un token invalido por expiración  ")
    public void shouldDetectExpiration(){
        String expiredAdminToken = jwtServiceTest.generateExpiredToken(userAdminTest);
        String expiredClientToken = jwtServiceTest.generateExpiredToken(userClientTest);
        assertThrows(ExpiredJwtException.class,()->jwtServiceTest.isTokenValid(expiredAdminToken,userAdminTest));
        assertThrows(ExpiredJwtException.class,()->jwtServiceTest.isTokenValid(expiredClientToken, userClientTest));

    }

    @Test
    @DisplayName(" Test para que la firma de administrador y cliente sean validas")
    public void shouldBeAValidAdminFirm(){

        assertThat(jwtServiceTest.isTokenValid(tokenAdmin, userAdminTest)).isTrue();
        assertThat(jwtServiceTest.isTokenValid(tokenClient, userClientTest)).isTrue();
    }
    @Test
    @DisplayName(" Test para que la firma de cliente sea invalida por email")
    public void shouldBeAInvalidFirmForWrongEmail(){

        UserDomain wrongEmailAdmin = new UserDomain(userAdminTest.getId(),userAdminTest.getFullname(),userAdminTest.getBirthDate(),userAdminTest.getUsername(),"hwefdfalsdkjf@gmail.com",userAdminTest.getHashedPassword());
        UserDomain wrongEmailClient = new UserDomain(userClientTest.getId(),userClientTest.getFullname(),userClientTest.getBirthDate(),userClientTest.getUsername(),"hwefdfalsdkjf@gmail.com",userClientTest.getHashedPassword());

        assertThat(jwtServiceTest.isTokenValid(tokenAdmin,wrongEmailAdmin )).isFalse();

        assertThat(jwtServiceTest.isTokenValid(tokenClient,wrongEmailClient)).isFalse();

    }



}
