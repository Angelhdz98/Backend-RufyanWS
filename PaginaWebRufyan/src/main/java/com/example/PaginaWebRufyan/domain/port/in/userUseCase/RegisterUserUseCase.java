package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.RegisterUserDTO;

public interface RegisterUserUseCase {
    RegisterUserDTO registerUserUseCase(CreateUserCommand createUserCommand);
}
