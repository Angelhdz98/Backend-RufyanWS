package com.example.PaginaWebRufyan.ControllerTests;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.LoginCommand;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerTest {
    @Value("spring.datasource.admin-password")
    private String adminPassword;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper;

    CreateUserCommand createUserCommand = new CreateUserCommand("hdz.04@outlook.es", "contraseñadeprueba123", "artLover",new FullName("Jose", "Luis","Slobotsky",""), LocalDate.of(1996,9, 14));


    LoginCommand loginCommand = new LoginCommand("hdz.04@outlook.es","contraseñadeprueba123");

    @Test
    @DisplayName("Test para poder registrar un nuevo usuario ")
    @WithAnonymousUser
    void shouldRegisterAnUser() throws Exception {



        mockMvc.perform(post("/auth/user-register").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserCommand))).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test para impedir que un usuario autenticado haga un registro ")
    @WithMockUser(username = "JulioG", roles = {"USER","CLIENT"})
    void shouldThrowExceptionRegisteringAnUserWithAuthenticatedUser() throws Exception {
        mockMvc.perform(post("/auth/user-register").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserCommand))).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test para que un usuario se pueda autenticar ")
    @WithAnonymousUser
    void shouldLoginAnAnonymousUser() throws Exception {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginCommand)));
    }



}
