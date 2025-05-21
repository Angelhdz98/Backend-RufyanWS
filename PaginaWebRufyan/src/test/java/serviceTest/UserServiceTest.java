package serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.example.PaginaWebRufyan.DTO.UserEntityDTO;
import com.example.PaginaWebRufyan.DTO.UserRegisterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PaginaWebRufyan.Entity.PermissionEntity;
import com.example.PaginaWebRufyan.Entity.RoleEntity;
import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.InconsitentDataException;
import com.example.PaginaWebRufyan.Repository.RoleRepository;
import com.example.PaginaWebRufyan.Repository.UserRepository;
import com.example.PaginaWebRufyan.Service.UserService;
import com.example.PaginaWebRufyan.Utils.RoleEnum;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	RoleRepository roleRepo;

	@InjectMocks
	UserService userService;
	
	private UserEntity user = new UserEntity();
	private UserEntity user2 = new UserEntity();
	private UserEntity user3 = new UserEntity();
	private UserEntity user4 = new UserEntity();

	
	
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
	assertThat(foundUser.getFullName()).isEqualTo(userResponse.getName());
	
	
	}
	
	@DisplayName("Test para encontrar un usuario por su username")
	@Test
	void findUserByUsername() {
		Integer id= 1; 
		UserEntity userResponse= user;
		UserEntityDTO userDto = new UserEntityDTO(user);
		userResponse.setId(id);
		Optional<UserEntity> optionalUser = Optional.of(user);
		given(userRepo.findUserByUsername(user.getUsername())).willReturn(optionalUser);
		
		 UserEntityDTO foundUserDto = userService.retrieveUserByUsername(user.getUsername());

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

		
		
		given(userRepo.findUserByUsername(user.getUsername())).willReturn(Optional.empty());
		given(userRepo.findUserByEmail(user.getEmail())).willReturn(Optional.empty());
		given(userRepo.findById(id)).willReturn(Optional.empty());
		given(userRepo.save(user)).willReturn(userResponse);
		given(roleRepo.findByRoleEnum(roleClient.getRoleEnum())).willReturn(Optional.of(roleClientResponse));
		
		
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

		UserRegisterDTO userRegister = new UserRegisterDTO(user);

		given(userRepo.findUserByUsername(user.getUsername())).willReturn(Optional.of(user));
		
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
		
		
		
		given(userRepo.findUserByUsername(bornedIn2025.getUsername())).willReturn(Optional.empty());
		given(userRepo.findUserByEmail(bornedIn2025.getEmail())).willReturn(Optional.empty());
		
		given(userRepo.findUserByUsername(noUsername.getUsername())).willReturn(Optional.empty());
		given(userRepo.findUserByEmail(noUsername.getEmail())).willReturn(Optional.empty());
		
		given(userRepo.findUserByUsername(noEmailUser.getUsername())).willReturn(Optional.empty());
		given(userRepo.findUserByEmail(noEmailUser.getEmail())).willReturn(Optional.empty());
		
		given(userRepo.findUserByUsername(weakPasswordUser.getUsername())).willReturn(Optional.empty());
		given(userRepo.findUserByEmail(weakPasswordUser.getEmail())).willReturn(Optional.empty());
		
		
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
	
	
	/*
	*
	*
	@DisplayName("Test para encontrar los usuarios que contengan el String de busqueda")
	@Test
	void searchUserBySubstrig () {
		UserEntity userResponse1 = user;
		userResponse1.setId(1);
		
		UserEntity userResponse2= user2;
		userResponse2.setId(2);
		String searchTerm= "Eze";  
		
		given(userRepo.findByUsernameContaining(searchTerm))
		.willReturn(List.of(userResponse1,userResponse2));
		
		given(userRepo.findByNameContainingIgnoreCase(searchTerm))
		.willReturn(List.of(userResponse1,userResponse2));
		
		List<UserEntity> matchedUsersByName = 		
		userService.searchUserWithNameMatch(searchTerm);
		
		List<UserEntity> matchedUsersByUsername = 		
		userService.searchUserWithUsernameMatch(searchTerm);
		
		assertThat(matchedUsersByName).isNotNull();
		assertThat(matchedUsersByName).contains(userResponse1);
		assertThat(matchedUsersByName).contains(userResponse2);
		
		
		assertThat(matchedUsersByUsername).isNotNull();
		assertThat(matchedUsersByUsername).contains(userResponse1);
		assertThat(matchedUsersByUsername).contains(userResponse2);
		
		
	}
	@DisplayName("Test para encontrar los usuarios que contengan el String de busqueda Paginado y ordenado")
	@Test
	void searchUserBySubstrigPagedAndSorted () {
		UserEntity userResponse1 = user;
		userResponse1.setId(1);
		
		UserEntity userResponse2= user2;
		userResponse2.setId(2);
		String searchTerm= "Eze";  
		
		Pageable pagedAndOrderedRequest= PageRequest.of(0, 12, Sort.by("birthDate").ascending());
		
		Page<UserEntity> pageResponse = new PageImpl<UserEntity>(List.of(userResponse1, userResponse2));
		
		given(userRepo.findByUsernameContaining(searchTerm, pagedAndOrderedRequest))
		.willReturn(pageResponse);
		
		given(userRepo.findByNameContainingIgnoreCase(searchTerm, pagedAndOrderedRequest))
		.willReturn(pageResponse);
		
		Page<UserEntity> matchedUsersByName = 		
		userService.searchUserWithNameMatch(searchTerm, pagedAndOrderedRequest);
		
		Page<UserEntity> matchedUsersByUsername = 		
		userService.searchUserWithUsernameMatch(searchTerm, pagedAndOrderedRequest);
		
		assertThat(matchedUsersByName).isNotNull();
		assertThat(matchedUsersByName).contains(userResponse1);
		assertThat(matchedUsersByName).contains(userResponse2);
		
		
		assertThat(matchedUsersByUsername).isNotNull();
		assertThat(matchedUsersByUsername).contains(userResponse1);
		assertThat(matchedUsersByUsername).contains(userResponse2);
		
		
	}
	*/


	

}
