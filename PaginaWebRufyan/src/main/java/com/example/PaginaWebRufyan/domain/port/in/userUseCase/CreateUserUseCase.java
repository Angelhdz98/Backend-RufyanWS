package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.domain.model.UserDomain;

public interface CreateUserUseCase {
    UserDomain createUser(CreateUserCommand createUserCommand);
}
