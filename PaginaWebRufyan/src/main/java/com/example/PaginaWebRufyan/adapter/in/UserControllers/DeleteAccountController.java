package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.DeleteAccountUseCase;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.DeleteUserByIdUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('CLIENT')")
public class DeleteAccountController {
    private final DeleteUserByIdUseCase deleteUserByIdUseCase;
    private final CurrentUserService currentUserService;
    public DeleteAccountController(DeleteAccountUseCase deleteAccountUseCase, DeleteUserByIdUseCase deleteUserByIdUseCase, CurrentUserService currentUserService) {
        this.deleteUserByIdUseCase = deleteUserByIdUseCase;
        this.currentUserService = currentUserService;
    }

    @DeleteMapping("/users")
    ResponseEntity<Void> deleteAccount(){
        UserDomain currentUser = currentUserService.getCurrentUser();
        deleteUserByIdUseCase.deleteUserById(currentUser.getId());
        return ResponseEntity.ok().build();
    }

}
