package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Security.Service.TokenResponse;

public record RegisterUserDTO(TokenResponse tokenResponse, UserEntityDTO2 userEntityDTO2) {

}
