package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.Security.Service.TokenResponse;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.RefreshTokenUseCase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshTokenController {
    private final RefreshTokenUseCase refreshTokenUseCase;


    public RefreshTokenController(RefreshTokenUseCase refreshTokenUseCase) {
        this.refreshTokenUseCase = refreshTokenUseCase;
    }
    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenResponse> refreshAuth (@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader){
       return ResponseEntity.ok(refreshTokenUseCase.refreshToken(authHeader));
    }
}
