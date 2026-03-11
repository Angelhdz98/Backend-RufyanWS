package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdatePasswordUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PreAuthorize()
public class UpdatePasswordController {
    private final UpdatePasswordUseCase updateUserPasswordUseCase;
    private final CurrentUserService currentUserService;

    public UpdatePasswordController(UpdatePasswordUseCase updateUserPasswordService, CurrentUserService currentUserService) {
        this.updateUserPasswordUseCase = updateUserPasswordService;
        this.currentUserService = currentUserService;
    }

    @PutMapping("/users/update-password")
    public ResponseEntity<Void> updatePassword(@RequestPart String oldPassword, @RequestPart String newPassword, @RequestPart String newPasswordConfirmation){
        UserDomain currentUser = currentUserService.getCurrentUser();
        if(!newPassword.equals(newPasswordConfirmation))throw new IllegalArgumentException("La confirmación de contraseña no coincide");

        updateUserPasswordUseCase.updatePassword(currentUser.getId(),oldPassword,newPassword);

        return ResponseEntity.ok().build();

    }
}
