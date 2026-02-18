package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

import com.example.PaginaWebRufyan.Security.Service.TokenResponse;

public interface RefreshTokenUseCase {
    TokenResponse refreshToken(String authHeader);
}
