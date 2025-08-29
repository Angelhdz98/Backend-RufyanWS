package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateUserCommand {
    private final String email;
    private final String password;
    private final String username;
    private final FullName fullName;
    private final LocalDate birthDate;


}
