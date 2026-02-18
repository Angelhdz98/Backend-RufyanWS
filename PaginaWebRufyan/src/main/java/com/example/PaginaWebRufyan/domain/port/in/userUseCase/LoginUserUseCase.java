package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

import com.example.PaginaWebRufyan.DTO.LoginCommand;
import com.example.PaginaWebRufyan.Security.Service.TokenResponse;

public interface LoginUserUseCase {
    TokenResponse authenticate(LoginCommand loginCommand);
}
