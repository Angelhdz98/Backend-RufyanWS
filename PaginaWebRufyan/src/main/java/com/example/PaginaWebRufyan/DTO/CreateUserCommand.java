package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class CreateUserCommand {
    private String email;
    private String password;
    private String username;
    private FullName fullName;
    private LocalDate birthDate;


    public CreateUserCommand(String email, String password, String username, FullName fullName, LocalDate birthDate) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }
    public CreateUserCommand(){
    }
}
