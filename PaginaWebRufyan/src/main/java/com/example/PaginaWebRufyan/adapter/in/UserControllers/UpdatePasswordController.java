package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.User.DTO.UpdatePasswordCommand;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdatePasswordUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdatePasswordController {
    private final UpdatePasswordUseCase updateUserPasswordUseCase;
    private final CurrentUserService currentUserService;

    public UpdatePasswordController(UpdatePasswordUseCase updateUserPasswordService, CurrentUserService currentUserService) {
        this.updateUserPasswordUseCase = updateUserPasswordService;
        this.currentUserService = currentUserService;
    }

    @PutMapping("/user/update-password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordCommand updatePasswordCommand){
        UserDomain currentUser = currentUserService.getCurrentUser();
        if( !updatePasswordCommand.newPassword().equals(updatePasswordCommand.newPasswordConfirmation())) {
            throw new IllegalArgumentException("La confirmación de contraseña no coincide");
        }
        updateUserPasswordUseCase.updatePassword(currentUser.getId(),updatePasswordCommand.oldPassword(),updatePasswordCommand.newPassword());

        return ResponseEntity.ok().build();

    }
}
