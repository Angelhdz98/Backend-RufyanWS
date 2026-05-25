package com.example.PaginaWebRufyan.adapter.in.UserControllers;


import com.example.PaginaWebRufyan.Exceptions.InvalidTokenException;
import com.example.PaginaWebRufyan.Security.Service.JwtService;
import com.example.PaginaWebRufyan.Security.Service.TokenResponse;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.RefreshTokenUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class RefreshTokenController {
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final JwtService jwtService;
    private final UserRepositoryPort userRepositoryPort;

    public RefreshTokenController(RefreshTokenUseCase refreshTokenUseCase, JwtService jwtService, CurrentUserService currentUserService, UserRepositoryPort userRepositoryPort) {
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.jwtService = jwtService;
        this.userRepositoryPort = userRepositoryPort;
    }
    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenResponse> refreshAuth(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            System.out.println("Cookies:"+ cookies);
            if (request.getCookies() != null) {
                for (Cookie c : request.getCookies()) {
                    System.out.println(c.getName() + "=" + c.getValue());
                }
            }
            String refreshToken = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals("refreshToken"))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElseThrow(() -> new RuntimeException("No refresh token"));
            String userEmail =
                    jwtService.extractEmail(refreshToken);
            UserDomain currentUser = userRepositoryPort.retrieveUserByEmail(userEmail);
            if (!jwtService.isTokenValid(refreshToken, currentUser)) {
                throw new InvalidTokenException("Invalid refresh token");
            }
            String newAccessToken = jwtService.generateToken(currentUser);
            return ResponseEntity.ok(refreshTokenUseCase.refreshToken(newAccessToken));
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
