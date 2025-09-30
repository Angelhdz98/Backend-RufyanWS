package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateUserCommand {
    private final String email;
    private final String password;
    private final String username;
    private final FullName fullName;
    private final LocalDate birthDate;


    public CreateUserCommand(String email, String password, String username, FullName fullName, LocalDate birthDate) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }
    protected CreateUserCommand(){
        this.email = null;
        this.password = null;
        this.username = null;
        this.fullName = null;
        this.birthDate = null;
    }
}
