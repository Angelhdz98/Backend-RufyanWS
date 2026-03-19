package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdateUserEmailUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateUserEmailController {
private final UpdateUserEmailUseCase updateUserEmailUseCase;
private final CurrentUserService currentUserService;
private final PasswordEncoder encoder = new BCryptPasswordEncoder();
private final UserEntityMapper userEntityMapper;
    public UpdateUserEmailController(UpdateUserEmailUseCase updateUserEmailUseCase, CurrentUserService currentUserService, UserEntityMapper userEntityMapper) {
        this.updateUserEmailUseCase = updateUserEmailUseCase;
        this.currentUserService = currentUserService;
        this.userEntityMapper = userEntityMapper;
    }
    @PutMapping("/users/update-email")
    public ResponseEntity<UserEntityDTO2> updateEmail(@RequestPart String newEmail, @RequestPart String password){

        UserDomain currentUser = currentUserService.getCurrentUser();
        if(!encoder.matches(password, currentUser.getHashedPassword())) throw new RuntimeException("No coincide la contraseña");

        return ResponseEntity.ok( userEntityMapper.toDto(updateUserEmailUseCase.updateEmail(currentUser.getId(), newEmail)));

    }

}
