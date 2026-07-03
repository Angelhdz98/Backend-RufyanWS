package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.UpdateUserInfoCommand;
import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdateUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//It does not update User email or password
@RestController
public class UpdateUserController {

    private final UpdateUserUseCase updateUserUseCase;
    private final CurrentUserService currentUserService;
    private final UserEntityMapper userEntityMapper;
    public UpdateUserController(UpdateUserUseCase updateUserUseCase, CurrentUserService currentUserService, UserEntityMapper userEntityMapper) {
        this.updateUserUseCase = updateUserUseCase;
        this.currentUserService = currentUserService;
        this.userEntityMapper = userEntityMapper;
    }

    @PutMapping("/users")
    public ResponseEntity<UserEntityDTO2> updateUser(@RequestBody UpdateUserInfoCommand updateUserInfoCommand)  {
        UserDomain currentUser = currentUserService.getCurrentUser();

        UpdateUserInfoCommand userInfoToUpdate = new UpdateUserInfoCommand(currentUser.getId(), updateUserInfoCommand.fullName(), updateUserInfoCommand.birthDate());

        return ResponseEntity.ok(userEntityMapper.toDto(updateUserUseCase.updateUser(userInfoToUpdate)));
    }

}
