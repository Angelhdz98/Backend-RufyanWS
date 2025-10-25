package com.example.PaginaWebRufyan.JPAAdapterTest;

import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.adapter.in.ConverterUserEntityDomain;
import com.example.PaginaWebRufyan.domain.model.ImageProcessor;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryJPAImp;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({UserRepositoryJPAImp.class})
public class JPAUserAdapterTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    private UserRepositoryJPAImp userRepositoryJPA;

    UserDomain userTest1;
    UserDomain userTest2;
    UserDomain userTest1UsernameEdited;
    UserDomain userTest3ExistentEmail;
   UserDomain userTest4ExistentUsername;
   UserDomain userTest3;
    MockedStatic<ImageProcessor> mockedImageProcessor;
@BeforeEach
    public void setUp(){
//        mockedImageProcessor =mockStatic(ImageProcessor.class);
       /* Set<ImageDomain> mockImageSet = mock(Set.class);
        mockedImageProcessor.when(()-> ImageProcessor.processImages(anyList(),anyString(),anyString())).thenReturn(mockImageSet);
        when(mockImageSet.size()).thenReturn(4);

        */

        userTest1 = new UserDomain(null,new FullName("Enrique","", "Bravo","Dominguez"), new BirthDate(LocalDate.now().minusYears(20)),"ElEnrique","enrique@gmail.com");
    userTest1UsernameEdited = new UserDomain(1L,new FullName("Enrique","", "Bravo","Dominguez"), new BirthDate(LocalDate.now().minusYears(20)),"ElEnriqueBravillo","enrique@gmail.com");
        userTest2 = new UserDomain(null ,new FullName("Enriqueberta","Guadalupe", "De Los Angeles","Cruz"), new BirthDate(LocalDate.now().minusYears(19)),"Enriquecida","enriquecidaalmeJ@gmail.com");
    userTest3 = new UserDomain(null,new FullName("Fulis","Mori", "De Los Angeles","Cruz"), new BirthDate(LocalDate.now().minusYears(19)),"Enri","enrifulis@gmail.com");


    userTest3ExistentEmail = new UserDomain(null,new FullName("Alonso","Enrique" , "Gomez","Bruera"), new BirthDate(LocalDate.now().minusYears(45)),"ElAlonso","enrique@gmail.com");

    userTest4ExistentUsername = new  UserDomain(null,new FullName("Enrique","Enrique" , "Torres","Mora"), new BirthDate(LocalDate.now().minusYears(25)),"ElEnrique","nuevocorreo@gmail.com");

    }
/*
    findUserById();
    findUserByUsername();
    findAllUsersByIds();
    findAllUsersWhoLikedProduct();
    findUsersByUsernameMatch();
    findUsersByNameMatch();
    findUsersByEmailMatch();
    retrieveUserById();
    retrieveUserByUsername();
    saveUser(); / updateUser                    DONE

    deleteById();
    existsByEmail();
    existsByUsername();
    existById();
    deleteUser();
*/

    @Test
    @DisplayName("Test para guardar un usario en la base de datos H2 ")
    public void shouldCreateAnUser(){

        UserDomain persistedUser = userRepositoryJPA.saveUser(userTest1);
        assertThat(persistedUser).isNotNull();
        assertThat(persistedUser.getEmail()).isEqualTo(userTest1.getEmail());

    }
    @Test
    @DisplayName("Test para evitar guardar un usuario con email repetido")
    public void shouldThrowExceptionPersistingAnUserWithRepeatedEmail(){
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest1));
        assertThrows(AlreadyExistIdenticatorException.class, ()->{
            userRepositoryJPA.saveUser(userTest3ExistentEmail);
        });
    }
    @Test
    @DisplayName("Test para evitar guardar una usuario con username repetido")
    public void shouldThrowExceptionPersistingAnUserWithRepeatedUsername(){
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest1));
        assertThrows(AlreadyExistIdenticatorException.class, ()->{
            userRepositoryJPA.saveUser(userTest4ExistentUsername);
        });
    }

    @Test
    @DisplayName("Test para actualizar un usuario ")
    public void shouldUpdateAnUserSuccessfully(){

        UserDomain savedUser = userRepositoryJPA.saveUser((userTest1));
        UserDomain userDomain = new UserDomain(
                savedUser.getId(), userTest1UsernameEdited.getFullname(), userTest1UsernameEdited.getBirthDate(),userTest1UsernameEdited.getUsername(),userTest1UsernameEdited.getEmail());
        UserDomain updatedUser = userRepositoryJPA.updateUser(userDomain);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getUsername()).isEqualTo(userTest1UsernameEdited.getUsername());

    }
    @Test
    @DisplayName("Test para evitar actualizar un usuario con email repetido")
    public void shouldReturnExceptionUpdatingAnUserWithRepeatedEmail(){
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest1));
        UserEntity userEntity = entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest2));
        UserDomain userToUpdate = new UserDomain(userEntity.getId(), userTest3ExistentEmail.getFullname(), userTest3ExistentEmail.getBirthDate(), userTest3ExistentEmail.getUsername(), userTest3ExistentEmail.getEmail());

        assertThrows(AlreadyExistIdenticatorException.class, ()->{
            userRepositoryJPA.updateUser(userToUpdate);
        });

    }
    @Test
    @DisplayName("Test para evitar actualizar un usuario con userName repetido")
    public void shouldReturnExceptionUpdatingAnUserWithRepeatedUsername(){
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest1));
        UserEntity userEntity = entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest2));
        UserDomain editedUserDomain = new UserDomain(userEntity.getId(), userTest4ExistentUsername.getFullname(), userTest4ExistentUsername.getBirthDate(), userTest4ExistentUsername.getUsername(), userTest4ExistentUsername.getEmail());

        assertThrows(AlreadyExistIdenticatorException.class, ()->{
            userRepositoryJPA.updateUser(editedUserDomain);
        });


    }

    @Test
    @DisplayName("Test para buscar un usario por id ")
    public void shouldFindUserById(){
        UserEntity savedUser = entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest1));
        UserDomain findUser = userRepositoryJPA.retrieveUserById(savedUser.getId());
        assertThat(findUser).isNotNull();
        assertThat(findUser.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(findUser.getUsername()).isEqualTo(savedUser.getUsername());
    }
    @Test
    @DisplayName("Test para buscar un usuario por username")
    public void shouldFindUserByUsername(){
        UserEntity savedUser = entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest1));
        UserDomain findUser = userRepositoryJPA.retrieveUserByUsername(savedUser.getUsername());
        assertThat(findUser).isNotNull();
        assertThat(findUser.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(findUser.getUsername()).isEqualTo(savedUser.getUsername());

    }
    @Test
    @DisplayName("Test para buscar usuario que hagan match por usermame ")
    public void shouldFindUsersByUsernameMatch(){
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest1));
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest2));
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest3));

        Page<UserDomain> usersByMatching = userRepositoryJPA.findUsersByUsernameMatch("enri", PageRequest.of(0, 3));
        assertThat(usersByMatching).isNotNull();
        assertThat(usersByMatching.getContent().size()).isEqualTo(3);


    }

    @Test
    @DisplayName("Test para encontrar los usuarios por nombre completo que hagan match con el substring ")
    public void shouldFindUsersByFullNameMatch(){
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest1));
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest2));
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest3));


        Page<UserDomain> usersByMatching = userRepositoryJPA.findUsersByNameMatch("Enri", PageRequest.of(0, 3));
        assertThat(usersByMatching).isNotNull();
        assertThat(usersByMatching.getContent().size()).isGreaterThan(0);


    }

    @Test
    @DisplayName("Test para encontrar los usuarios por email que hagan match con el substring")
    public  void shouldFindUsersByEmailMatching(){
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest1));
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest2));
        entityManager.persistAndFlush(ConverterUserEntityDomain.convertToEntity(userTest3));
        Page<UserDomain> usersByMatching = userRepositoryJPA.findUsersByEmailMatch("enri", PageRequest.of(0, 3));
        assertThat(usersByMatching).isNotNull();
        assertThat(usersByMatching.getContent().size()).isGreaterThan(0);

    }


}
