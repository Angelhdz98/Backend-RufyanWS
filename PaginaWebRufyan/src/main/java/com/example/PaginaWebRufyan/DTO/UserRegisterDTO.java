package com.example.PaginaWebRufyan.DTO;

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
private String Password;

}
