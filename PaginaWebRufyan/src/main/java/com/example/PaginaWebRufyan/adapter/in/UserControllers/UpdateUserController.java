package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.UpdateUserCommand;
import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdateUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UpdateUserController {

    private final UpdateUserUseCase updateUserUseCase;
    private final CurrentUserService currentUserService;
    public UpdateUserController(UpdateUserUseCase updateUserUseCase, CurrentUserService currentUserService) {
        this.updateUserUseCase = updateUserUseCase;
        this.currentUserService = currentUserService;
    }

    @PutMapping("/users")
    public ResponseEntity<UserEntityDTO2> updateUser(@RequestBody CreateUserCommand command){
        UserDomain currentUser = currentUserService.getCurrentUser();
        UpdateUserCommand updateCommand = new UpdateUserCommand(command.getEmail(), command.getPassword(), command.getUsername(), command.getFullName(), command.getBirthDate(), currentUser.getId());
        return ResponseEntity.ok(UserEntityMapper.toDto(updateUserUseCase.updateUser(updateCommand)));
    }

}
