package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.LoginCommand;
import com.example.PaginaWebRufyan.Security.Service.TokenResponse;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.LoginUserUseCase;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("permitAll()")
public class LogInController {
    private final LoginUserUseCase loginUserUseCase;
    public LogInController(LoginUserUseCase loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginCommand loginCommand, HttpServletResponse response){
        System.out.println("La contraseña ingresada es:"+loginCommand.password() + "El email ingresado es: "+ loginCommand.identificator());
        TokenResponse authResponse =
                loginUserUseCase.authenticate(loginCommand);
        Cookie cookie = new Cookie("refreshToken",
                authResponse.refreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        cookie.setPath("/");
        cookie.setAttribute("SameSite", "Lax");
        response.addCookie(cookie);
        return ResponseEntity.ok(authResponse);
}
}
