package com.example.PaginaWebRufyan.DomainTest;


import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserDomainTest {
    //valores default validos
    Long idTest = 1L;
    String firstNameTest = "John";
    String secondName = "Luis";
    String lastNameTest = "Doe";
    String SecondLastNameTest = "Smith";
    String usernameTest = "johndoe";
    String emailTest = "johndoefun@gmail.com";
    LocalDate birthDateTest= LocalDate.now().minusYears(20);;
    FullName correctFullName = new FullName(firstNameTest, "", lastNameTest, "");
    BirthDate correctBirthDay = new BirthDate( birthDateTest);


    @Test
    @DisplayName("Test para crear un usuario de manera exitosa")
    public void shouldCreateUserDomainSuccessfully() {
        FullName fullNameTest = new FullName(firstNameTest, "", lastNameTest, "");

        UserDomain userDomain = new UserDomain(idTest, fullNameTest, correctBirthDay, usernameTest, emailTest);

        assertThat(userDomain.getId()).isGreaterThan(0L);
        assertThat(userDomain.getFullname().getFirstName()).isEqualTo(firstNameTest);


    }
    @Test
    @DisplayName("Test para evitar crear un usuario con email invalido")
    public void shouldFailCreatingUserDomainWhenEmailIsInvalid() {


        String invalidEmail = "invalid-email";

        assertThrows(IllegalArgumentException.class, () -> {
            UserDomain userDomain = new UserDomain(idTest, correctFullName, correctBirthDay, usernameTest, invalidEmail);
        });


    }
    @Test
    @DisplayName("Test para evitar crear un usuario con nombre invalido")
    public void shouldFailCreatingUserWithInvalidName()
    //invalid names, no first name, no last name, names with  number or special characters
    {
        assertThrows( IllegalArgumentException.class, () -> {

            UserDomain userDomain = new UserDomain(idTest, new FullName("", "", lastNameTest, ""), correctBirthDay, usernameTest, emailTest);

        });
        assertThrows( IllegalArgumentException.class, () -> {

            UserDomain userDomain = new UserDomain(idTest, new FullName("1efdg", "", lastNameTest, ""), correctBirthDay, usernameTest, emailTest);

        });
        assertThrows( IllegalArgumentException.class, () -> {

            UserDomain userDomain = new UserDomain(idTest, new FullName("d", "", lastNameTest, ""), correctBirthDay, usernameTest, emailTest);

        });
        assertThrows( IllegalArgumentException.class, () -> {

            UserDomain userDomain = new UserDomain(idTest, new FullName(firstNameTest, "jk1j2", lastNameTest, ""), correctBirthDay, usernameTest, emailTest);


        });
    }
    @Test
    @DisplayName("Test para evitar crear un usuario con apellido invalido")
    public void shouldFailCreatingUserWithInvalidLastname()
    //invalid names, no first name, no last name, names with  number or special characters
    {
        assertThrows( IllegalArgumentException.class, () -> {

            UserDomain userDomain = new UserDomain(idTest, new FullName(firstNameTest, "", "", ""), correctBirthDay, usernameTest, emailTest);

        });

        assertThrows( IllegalArgumentException.class, () -> {

            UserDomain userDomain = new UserDomain(idTest, new FullName(firstNameTest, "", lastNameTest, "ll1j2j"), correctBirthDay, usernameTest, emailTest);

        });
        assertThrows( IllegalArgumentException.class, () -> {

            UserDomain userDomain = new UserDomain(idTest, new FullName(firstNameTest, "", "ll1j2j", ""), correctBirthDay, usernameTest, emailTest);

        });
        assertThrows( IllegalArgumentException.class, () -> {

            UserDomain userDomain = new UserDomain(idTest, new FullName(firstNameTest, "", "l", ""), correctBirthDay, usernameTest, emailTest);

        });
    }

    @Test
    @DisplayName("Test para evitar crear un usuario con username invalido")
    public void shouldFailCreatingUserWithInvalidUsername(){
        //debe tener al menos 4 caracteres, no puede tener espacios, no puede tener caracteres especiales
         assertThrows( IllegalArgumentException.class, () -> {

            UserDomain userDomain = new UserDomain(idTest, correctFullName, correctBirthDay, "j", emailTest);

        });


        assertThrows( IllegalArgumentException.class, () -> {
            UserDomain userDomain = new UserDomain(idTest, correctFullName, correctBirthDay, "lkjdfkj!", emailTest);
        });

        assertThrows( IllegalArgumentException.class, () -> {
            UserDomain userDomain = new UserDomain(idTest, correctFullName, correctBirthDay, "lkj dfkj", emailTest);
        });

    }
    @Test
    @DisplayName("Test para evitar crear un usuario con fecha de nacimiento invalida")
    public void shouldFailCreatingUserWithInvalidBirthDate(){
        //no puede ser mayor a la fecha actual, no puede ser menor a 18 años
        assertThrows( IllegalArgumentException.class, () -> {

            UserDomain userDomain = new UserDomain(idTest, correctFullName, new BirthDate(LocalDate.now().plusDays(1)), usernameTest, emailTest);

        });
        assertThrows( IllegalArgumentException.class, () -> {
            UserDomain userDomain = new UserDomain(idTest, correctFullName, new BirthDate(LocalDate.now().minusYears(17)), usernameTest, emailTest);
        });
    }

}
