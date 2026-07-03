package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.UpdateUsernameCommand;
import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.Exceptions.ForbidenAccessException;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdateUsernameUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateUsernameController {
private final UpdateUsernameUseCase updateUsernameUseCase;
private final CurrentUserService currentUserService;
private final UserEntityMapper userEntityMapper;
private final PasswordEncoder encoder = new BCryptPasswordEncoder();


    public UpdateUsernameController(UpdateUsernameUseCase updateUsername, CurrentUserService currentUserService, UserEntityMapper userEntityMapper) {
        this.updateUsernameUseCase = updateUsername;
        this.currentUserService = currentUserService;
        this.userEntityMapper = userEntityMapper;
    }
    @PutMapping("/user/update-username")
    public ResponseEntity<UserEntityDTO2> updateUsername(@RequestBody UpdateUsernameCommand updateUsernameCommand){
        UserDomain currentUser = currentUserService.getCurrentUser();

        if(!encoder.matches(updateUsernameCommand.password(),
                currentUser.getHashedPassword())){
throw new ForbidenAccessException("Contraseña incorrecta");
        }


        UserEntityDTO2 dto = userEntityMapper.toDto(updateUsernameUseCase.updateUsername(new UpdateUsernameCommand(currentUser.getId(), updateUsernameCommand.newUsername(), updateUsernameCommand.password())));

        return ResponseEntity.ok(dto);


    }

}
