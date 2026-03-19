package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.CreateUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@RequestMapping("/users")
public class CreateNewUserController {
    private final CreateUserUseCase createUserUseCase;
    private final UserEntityMapper userEntityMapper;

    public CreateNewUserController(CreateUserUseCase createUserUseCase, UserEntityMapper userEntityMapper) {
        this.createUserUseCase = createUserUseCase;
        this.userEntityMapper = userEntityMapper;
    }
    @PostMapping(produces = "application/json", consumes =  "application/json" )
    public ResponseEntity<UserEntityDTO2> createNewUser(@RequestBody CreateUserCommand command){
        return  ResponseEntity.ok(userEntityMapper.toDto(createUserUseCase.createUser(command)));
    }

}
