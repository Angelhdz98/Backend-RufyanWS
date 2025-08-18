package com.example.PaginaWebRufyan.adapter.in;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.CreateUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/v2")
public class UserController2 {
    private final CreateUserUseCase createUserUseCase;

    public UserController2(CreateUserUseCase createUserUseCase){
        this.createUserUseCase= createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<UserEntityDTO2> createNewUser(@RequestBody CreateUserCommand command){
        return   ResponseEntity.ok(new UserEntityDTO2(createUserUseCase.createUser(command)));
    }



}
