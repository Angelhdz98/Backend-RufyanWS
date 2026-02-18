package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.RegisterUserDTO;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.RegisterUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {
    private final RegisterUserUseCase registerUserUseCase;


    public RegisterUserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/auth/user-register")
    public ResponseEntity<RegisterUserDTO> registerNewUser(@RequestBody CreateUserCommand createUserCommand){
        return ResponseEntity.ok(registerUserUseCase.registerUserUseCase(createUserCommand));
    }


}
