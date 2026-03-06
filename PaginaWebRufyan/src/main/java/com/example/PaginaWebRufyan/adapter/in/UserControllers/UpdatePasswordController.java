package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.UpdateUserPasswordService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdatePasswordController {
    private final UpdateUserPasswordService updateUserPasswordService;
    private final CurrentUserService currentUserService;

    public UpdatePasswordController(UpdateUserPasswordService userPasswordService, CurrentUserService currentUserService) {
        this.updateUserPasswordService = userPasswordService;
        this.currentUserService = currentUserService;
    }
    @PutMapping("/user/update-password")
    public ResponseEntity<Void> updatePassword(@RequestPart String oldPassword, @RequestPart String newPassword, @RequestPart String newPasswordConfirmation){
        UserDomain currentUser = currentUserService.getCurrentUser();
        if(!newPassword.equals(newPasswordConfirmation))throw new IllegalArgumentException("La confirmación de contraseña no coincide");

        updateUserPasswordService.updatePassword(currentUser.getId(),oldPassword,newPassword);

        return ResponseEntity.ok().build();

    }
}
