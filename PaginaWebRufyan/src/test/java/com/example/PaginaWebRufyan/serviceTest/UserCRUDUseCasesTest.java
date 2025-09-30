package com.example.PaginaWebRufyan.serviceTest;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.UpdateUserCommand;
import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.EmailAlreadyUsedException;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.*;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserCRUDUseCasesTest {
    @Mock
    UserRepositoryPort userRepositoryPort;
    @InjectMocks
    CreateUserService createUserService;
    @InjectMocks
    FindUserByIdService findUserByIdService;
    @InjectMocks
    FindUserByUsernameService findUserByUsernameService;
    @InjectMocks
    FindUsersByFullNameContainingService findUsersByFullNameContainingService;
    @InjectMocks
    FindUsersByUsernameMatchService findUsersByUsernameMatchService;

    @InjectMocks
    UpdateUserService updateUserService;

    @InjectMocks
    DeleteUserByIdService deleteUserByIdService;
    @InjectMocks
    FindUsersByEmailContainingService findUsersByEmailContainingService;

    // Valid values
    String validEmail = "elpepe@gmail.com";
    String validUsername = "elpepe123";
    FullName validFullName = new FullName("Pepe","","Lopez","");
    String validPassword = "Password123$";
    BirthDate validBirthDate = new BirthDate(LocalDate.now().minusYears(20L));

    //Invalid values
    String invalidEmail1 = "elpepegmail.com";
    String invalidEmail2 = "@gmail.com";
    String invalidEmail3 = "elpepe@gmail.";
    String invalidEmail4 = "elpepe@gmail.c";

    String invalidUsername1 = "123";
    String invalidUsername2 = "ui";
    String invalidUsername3 = "123";

   CreateUserCommand validNewUser= new CreateUserCommand(
            validEmail,
            validPassword,
            validUsername,
            validFullName,
            validBirthDate.getBirthDate()
                );
   UpdateUserCommand updateCommandTest;
@BeforeEach
public void setUp(){

    updateCommandTest = new UpdateUserCommand("email@valido.com","password123", "hentrr", new FullName("hoadf","jkdfj","kjkdfjkdj","jdjkf"),LocalDate.now().minusYears(20),1L);




}
    @Test
    @DisplayName("Test para crear un usuario ")
    public void shouldCreateAUserWhenDataIsOk(){

        UserDomain mockResponse =  mock(UserDomain.class);

        when(userRepositoryPort.existsByEmail(any(String.class))).thenReturn(false);
        when(userRepositoryPort.existsByUsername(any(String.class))).
                thenReturn(false);
        when(userRepositoryPort.saveUser(any())).thenReturn(mockResponse);
//        when(mockResponse.getFullname()).thenReturn(new FullName("Rambo", "Alvaro", "Fomez", "Delao"));
  //      when(mockResponse.getEmail()).thenReturn("emailprueba@gmail.com");
    //    when(mockResponse.getUsername()).thenReturn("ramboDelaO");
     //   when(mockResponse.getBirthDate()).thenReturn(new BirthDate(LocalDate.now().minusYears(20)));

        UserDomain createdUser = createUserService.createUser(
                new CreateUserCommand(
                        validEmail,
                        validPassword,
                        validUsername,
                        validFullName,
                        validBirthDate.getBirthDate()
                )
        );
        verify(userRepositoryPort, times(1)).saveUser(any());


    }
    @Test
    @DisplayName("Test para evitar crear un usuario con username o email ya registrado")
    public void shouldThrowIllegalArgumentExceptionWhenUsernameAlreadyExist(){
        when(userRepositoryPort.existsByUsername(any())).thenReturn(true);
        when(userRepositoryPort.existsByEmail(any())).thenReturn(false);

        assertThrows(AlreadyExistIdenticatorException.class, ()->{
            createUserService.createUser(validNewUser);
        });
        verify(userRepositoryPort, times(0)).saveUser(any());


    }
    @Test
    @DisplayName("Test para evitar crear un usuario con username o email ya registrado")
    public void shouldThrowIllegalArgumentExceptionWhenEmailIsAlreadyRegisteredInAnotherAccount(){
        when(userRepositoryPort.existsByUsername(any())).thenReturn(true);
        when(userRepositoryPort.existsByEmail(any())).thenReturn(false);
         assertThrows(AlreadyExistIdenticatorException.class, ()->{
            createUserService.createUser(validNewUser);
        });
        verify(userRepositoryPort, times(0)).saveUser(any());

    }
    @Test
    @DisplayName("Test para buscar un usuario por id existente")
    public void shouldFindUserById(){
        Long id = 1L;
        UserDomain mockUser = mock(UserDomain.class);
        when(userRepositoryPort.findUserById(id)).thenReturn(Optional.of(mockUser));


        UserDomain userById = findUserByIdService.findUserById(id);
        assertThat(userById).isNotNull();
        verify(userRepositoryPort,times(1)).findUserById(id);

    }
    @Test
    @DisplayName("Test para buscar un usuario por username existente")
    public void shouldFindUserByName(){
        String searchUser= "PlonAngel";
        UserDomain mockUser = mock(UserDomain.class);
       // when(userRepositoryPort.findUserByUsername(any())).thenReturn(Optional.of(mockUser));
        when(userRepositoryPort.retrieveUserByUsername(any())).thenReturn(mockUser);


        UserDomain userById = findUserByUsernameService.findUserByUsername(searchUser);
        assertThat(userById).isNotNull();
         verify(userRepositoryPort,times(1)).retrieveUserByUsername(any());

    }

    @Test
    @DisplayName("Test para encontrar usuarios por name matching")
    public void shouldFindUsersByFullNameMatching(){


        Page<UserDomain> pagedUsersResponse = mock(Page.class);

        when(userRepositoryPort.findUsersByNameMatch(any(),any())).thenReturn(pagedUsersResponse);

        Page<UserDomain> matchUsers = findUsersByFullNameContainingService.findUsersByFullNameMatch("Figaredo", PageRequest.of(1,2));

        assertThat(matchUsers).isNotNull();
        verify(userRepositoryPort, times(1)).findUsersByNameMatch(any(), any());

    }

    @Test
    @DisplayName("Test para encontrar los usuarios que contengan el substring en su username ")
    public void shouldFindUsersByUsernameMatching(){

        Page<UserDomain> pagedUsersResponse = mock(Page.class);
        when(userRepositoryPort.findUsersByUsernameMatch(any(),any())).thenReturn(pagedUsersResponse);

        Page<UserDomain> usersResponse = findUsersByUsernameMatchService.findUsersByUsernameMatch("figaredo",PageRequest.of(0,2));

        assertThat(usersResponse).isNotNull();

        verify(userRepositoryPort, times(1)).findUsersByUsernameMatch(any(),any());
    }

    @Test
    @DisplayName("Test para encontrar los usuarios que contengan el substring en su email ")
    public void shouldFindUsersByEmailMatching(){

        Page<UserDomain> pagedUsersResponse = mock(Page.class);
        when(userRepositoryPort.findUsersByEmailMatch(any(),any())).thenReturn(pagedUsersResponse);

        Page<UserDomain> usersResponse =  findUsersByEmailContainingService.findUsersByEmailMatch("emailPart", PageRequest.of(0,2));

        assertThat(usersResponse).isNotNull();

        verify(userRepositoryPort, times(1)).findUsersByEmailMatch(any(),any());
    }



    @Test
    @DisplayName("Test para actualizar un usuario por id ")
    public void shouldUpdateUserById(){



        UserDomain mockUser = mock(UserDomain.class);

        UserDomain updatedMockUser = mock(UserDomain.class);
        when(userRepositoryPort.retrieveUserById(any())).thenReturn(mockUser);
        when(userRepositoryPort.saveUser(any())).thenReturn(mockUser);
        when(mockUser.getEmail()).thenReturn("email@gmail.com");
        when(mockUser.getUsername()).thenReturn("validUsername");


        when(userRepositoryPort.existsByUsername(any())).thenReturn(false);
        when(userRepositoryPort.existsByEmail(any())).thenReturn(false);


        UserDomain userDomain = updateUserService.updateUser(updateCommandTest);
        assertThat(userDomain).isNotNull();

        verify(userRepositoryPort, times(1)).saveUser(any());



    }


    @Test
    @DisplayName("Test para arrojar una excepción cuando el nuevo username del usuario ya esta registrado ")
    public void shouldThrowExceptionUpdatingTheEmailForANewOneAlreadyRegistered(){


        String emailPrueba = "emailprueba@gmail.com";
        //UserDomain mockUserDomain = mock(UserDomain.class);
        UserDomain mockUserRegisteredDomain = mock(UserDomain.class);
        String username = "henry";


        when(userRepositoryPort.retrieveUserById(any())).thenReturn(mockUserRegisteredDomain);

        when(mockUserRegisteredDomain.getUsername()).thenReturn("henry2"); // mismo username
        when(mockUserRegisteredDomain.getEmail()).thenReturn(emailPrueba);// diferente email


        when(userRepositoryPort.existsByUsername(any())).thenReturn(true);
        when(userRepositoryPort.existsByEmail(any())).thenReturn(false);

        assertThrows(AlreadyExistIdenticatorException.class, ()->{
            updateUserService.updateUser(updateCommandTest);
        });


    }

    @Test
    @DisplayName("Test para arrojar una excepción cuando el nuevo email del usuario ya esta registrado ")
    public void shouldThrowExceptionUpdatingAUserWhenTheUserNameIsRegistered(){

        String emailPrueba = "emailprueba@gmail.com";
        //UserDomain mockUserDomain = mock(UserDomain.class);
        UserDomain mockUserRegisteredDomain = mock(UserDomain.class);
        String username = "henry";

        when(userRepositoryPort.retrieveUserById(any())).thenReturn(mockUserRegisteredDomain);

//        when(mockUserRegisteredDomain.getUsername()).thenReturn(username); // mismo username // this sentence is not used cause the code
        when(mockUserRegisteredDomain.getEmail()).thenReturn("email@gmail.com");// diferente email


        when(userRepositoryPort.existsByEmail(any())).thenReturn(true);


        assertThrows(EmailAlreadyUsedException.class, ()->{
            updateUserService.updateUser(updateCommandTest);
        });


    }

    @Test
    @DisplayName("Test para arrojar una excepción cuando el usuario es menor de edad ")
    public void shouldThrowExceptionUpdatingAUserWithAMinorBirthDate(){

        updateCommandTest = new UpdateUserCommand("email@valido.com","password123", "hentrr", new FullName("hoadf","jkdfj","kjkdfjkdj","jdjkf"),LocalDate.now().minusYears(10),1L);

        String emailPrueba = "emailprueba@gmail.com";
        //UserDomain mockUserDomain = mock(UserDomain.class);
        UserDomain mockUserRegisteredDomain = mock(UserDomain.class);
        String username = "henry";

        when(userRepositoryPort.retrieveUserById(any())).thenReturn(mockUserRegisteredDomain);

      when(mockUserRegisteredDomain.getUsername()).thenReturn(username); // mismo username // this sentence is not used cause the code
        when(mockUserRegisteredDomain.getEmail()).thenReturn("email@gmail.com");// diferente email


        when(userRepositoryPort.existsByEmail(any())).thenReturn(false);


        assertThrows(IllegalArgumentException.class, ()->{
            updateUserService.updateUser(updateCommandTest);
        });


    }

    @Test
    @DisplayName("Test para arrojar una excepción cuando el usuario tiene una fecha invalida")
    public void shouldThrowExceptionUpdatingAUserWhenTheNewBirthDateIsInvalid(){

        updateCommandTest = new UpdateUserCommand("email@valido.com","password123", "hentrr", new FullName("hoadf","jkdfj","kjkdfjkdj","jdjkf"),LocalDate.now().plusYears(10),1L);

        String emailPrueba = "emailprueba@gmail.com";
        //UserDomain mockUserDomain = mock(UserDomain.class);
        UserDomain mockUserRegisteredDomain = mock(UserDomain.class);
        String username = "henry";

        when(userRepositoryPort.retrieveUserById(any())).thenReturn(mockUserRegisteredDomain);

        when(mockUserRegisteredDomain.getUsername()).thenReturn(username); // mismo username // this sentence is not used cause the code
        when(mockUserRegisteredDomain.getEmail()).thenReturn("email@gmail.com");// diferente email


        when(userRepositoryPort.existsByEmail(any())).thenReturn(false);
        when(userRepositoryPort.existsByUsername(any())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, ()->{
            updateUserService.updateUser(updateCommandTest);
        });


    }

    @Test
    @DisplayName("Test para encontrar los usuarios ")
    public void shouldDeleteUserSuccessfully(){

    UserDomain mockUser = mock(UserDomain.class);
    when(userRepositoryPort.retrieveUserById(any())).thenReturn(mockUser);
    deleteUserByIdService.deleteUserById(1L);
    verify(userRepositoryPort, times(1)).deleteById(any());

    }

}




















