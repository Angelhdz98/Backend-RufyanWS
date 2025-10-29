package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.CreateUserUseCase;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByUsernameUseCase;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdateUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class CreateNewUserController {
    private final CreateUserUseCase createUserUseCase;

    public CreateNewUserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }
    @PostMapping(produces = "application/json", consumes =  "application/json" )
    public ResponseEntity<UserEntityDTO2> createNewUser(@RequestBody CreateUserCommand command){
        return   ResponseEntity.ok(UserEntityMapper.toDto(createUserUseCase.createUser(command)));
    }

}
