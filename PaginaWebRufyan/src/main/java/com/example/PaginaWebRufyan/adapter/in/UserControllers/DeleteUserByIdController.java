package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.domain.port.in.userUseCase.DeleteUserByIdUseCase;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class DeleteUserByIdController {
   private final DeleteUserByIdUseCase deleteUserByIdUseCase;

    public DeleteUserByIdController(DeleteUserByIdUseCase deleteUserByIdUseCase) {
        this.deleteUserByIdUseCase = deleteUserByIdUseCase;
    }
    @DeleteMapping("/users/{userId}")
    public void  deleteUserById(@PathVariable Long userId) {
        deleteUserByIdUseCase.deleteUserById(userId);

    }

}
