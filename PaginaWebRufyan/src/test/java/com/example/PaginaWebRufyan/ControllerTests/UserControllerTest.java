package com.example.PaginaWebRufyan.ControllerTests;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.PaginaWebRufyan.Controller.UserController;
import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Service.FavoritesProductsService;
import com.example.PaginaWebRufyan.Service.ProductService;
import com.example.PaginaWebRufyan.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	

	@MockBean
	private  UserService userService;
	@MockBean
	private FavoritesProductsService favoritesProductsService;
	@MockBean
	private ProductService productService;
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@DisplayName("Test para listar todos los usuarios")
	@Test
	void findAllUsersTestOk() throws Exception {
		
		//Given
		List<UserEntity> userList = List.of();
		userList.add(UserEntity.builder()
				.birthDate(LocalDate.of(1988, 3, 28))
				.credentialNoExpired(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.name("Ezequiel")
				.lastname("Machiwi")
				.password("soyUnChicoEnamorado")
				.email("ezequielmachiwi@gmail.com")
				.username("EzequielMach")
				.build());
		userList.add(UserEntity.builder()
				.birthDate(LocalDate.of(1999, 5, 16))
				.credentialNoExpired(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.name("Ezelik")
				.lastname("Gamez")
				.password("soyUnChicoGamez")
				.email("ezelikgamez@gmail.com")
				.username("EzelGom")
				.build() );
		given(userService.findAllUsers()).willReturn(userList);
		
		//When
		ResultActions response = mockMvc.perform(get("/users"));
		
		//Then
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.size()", is(userList.size())) );
		
		
	}
	
	@DisplayName("Test para probar el enpoint de guardar un usuario")
	@Test
	void saveUserTestOk()throws Exception {
		//given
		int id = 1; 
		UserEntity user = UserEntity.builder()
				.birthDate(LocalDate.of(1988, 3, 28))
				.credentialNoExpired(true)
				.accountNoExpired(true)
				.accountNoLocked(true)
				.name("Ezequiel")
				.lastname("Machiwi")
				.password("soyUnChicoEnamorado")
				.email("ezequielmachiwi@gmail.com")
				.username("EzequielMach")
				.build();
		
		//UserEntity userResponse= user;
		//userResponse.setId(id);
		
		given(userService.save(any(UserEntity.class))).willAnswer((invocation)->{
			UserEntity userResponse = invocation.getArgument(0);
			userResponse.setId(id);
			return userResponse;
		});
		//when
		ResultActions response = mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)));
		
		//then
		response.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is(user.getName())))
				.andExpect(jsonPath("$.lastname", is(user.getLastname())))
				.andExpect(jsonPath("$.email", is(user.getEmail())))
				.andExpect(jsonPath("$.id", is(id)));
	}
	













}


