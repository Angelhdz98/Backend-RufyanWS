package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class UpdateUserCommand extends CreateUserCommand {
    private final Long userId;

    public UpdateUserCommand(String email, String password, String username, FullName fullName, LocalDate birthDate, Long userId) {
        super(email, password, username, fullName, birthDate);
        this.userId = userId;
    }
}
