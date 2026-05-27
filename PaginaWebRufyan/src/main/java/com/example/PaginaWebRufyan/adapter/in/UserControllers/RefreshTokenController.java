package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.Exceptions.InvalidTokenException;
import com.example.PaginaWebRufyan.Security.Service.JwtService;
import com.example.PaginaWebRufyan.Security.Service.TokenResponse;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.RefreshTokenUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final JwtService jwtService;
    private final UserRepositoryPort userRepositoryPort;

    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenResponse> refreshAuth(HttpServletRequest request) {
        // 1. Validar que existan cookies
        if (request.getCookies() == null || request.getCookies().length == 0) {
            throw new InvalidTokenException("No se encontró token de refresco en cookies");
        }

        // 2. Extraer refreshToken de cookies
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new InvalidTokenException("No se encontró cookie 'refreshToken'"));

        try {
            System.out.println("TOKEN RECIBIDO: " + refreshToken);
            System.out.println("EMAIL: " + jwtService.extractEmail(refreshToken));
            System.out.println("EXPIRADO?: " + jwtService.isTokenExpired(refreshToken));
            // 3. Extraer email del refresh token
            String userEmail = jwtService.extractEmail(refreshToken);

            if (userEmail == null) {
                throw new InvalidTokenException("2No se pudo " +
                        "extraer email del refresh token");
            }

            // 4. Cargar usuario
            UserDomain currentUser = userRepositoryPort.retrieveUserByEmail(userEmail);

            // 5. Validar que el refresh token sea válido
            if (!jwtService.isTokenValid(refreshToken, currentUser)) {
                throw new InvalidTokenException("Refresh token inválido o expirado");
            }

            // 6. Generar nuevo access token
            String newAccessToken = jwtService.generateToken(currentUser);

            // 7. Construir la respuesta
            TokenResponse tokenResponse = new TokenResponse(newAccessToken, refreshToken);
            return ResponseEntity.ok(tokenResponse);

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new InvalidTokenException("Refresh token expirado");
        } catch (JwtException e) {
            throw new InvalidTokenException("Refresh token inválido: " + e.getMessage());
        }
    }
}