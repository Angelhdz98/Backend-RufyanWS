package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.UserEntity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserRegisterDTO {
private String name;
private String LastName;
@Email
private String email;
private LocalDate birthDate;
private String password;
private String username;

public UserRegisterDTO(UserEntity user){
    this.name = user.getName();
    this.LastName = user.getLastname();
    this.email = user.getEmail();
    this.birthDate = user.getBirthDate();
    this.password = user.getPassword();
    this.username= user.getUsername();
}

}
