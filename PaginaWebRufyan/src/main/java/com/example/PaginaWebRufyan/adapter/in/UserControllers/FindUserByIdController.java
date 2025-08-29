package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByIdUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class FindUserByIdController {
    private final FindUserByIdUseCase findUserByIdUseCase;

    public FindUserByIdController(FindUserByIdUseCase findUserByIdUseCase) {
        this.findUserByIdUseCase = findUserByIdUseCase;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserEntityDTO2> retrieveUserById(@PathVariable Long userId){
        return ResponseEntity.ok( new UserEntityDTO2(findUserByIdUseCase.findUserById(userId)));
    }
}
