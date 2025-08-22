package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.domain.port.in.userUseCase.DeleteUserByIdUseCase;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class DeleteUserByIdController {
   private final DeleteUserByIdUseCase deleteUserByIdUseCase;

    public DeleteUserByIdController(DeleteUserByIdUseCase deleteUserByIdUseCase) {
        this.deleteUserByIdUseCase = deleteUserByIdUseCase;
    }
    @DeleteMapping
    public void  deleteUserById(@RequestBody Long userId) {
        deleteUserByIdUseCase.deleteUserById(userId);

    }

}
