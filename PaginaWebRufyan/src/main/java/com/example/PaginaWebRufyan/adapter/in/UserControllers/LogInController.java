package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.LoginCommand;
import com.example.PaginaWebRufyan.Security.Service.TokenResponse;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.LoginUserUseCase;
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
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginCommand loginCommand){
    return ResponseEntity.ok(loginUserUseCase.authenticate(loginCommand));
}
}
