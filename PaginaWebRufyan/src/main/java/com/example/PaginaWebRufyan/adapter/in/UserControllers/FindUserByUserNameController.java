package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByUsernameUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class FindUserByUserNameController {
    private final FindUserByUsernameUseCase findUserByUsernameUseCase;

    public FindUserByUserNameController(FindUserByUsernameUseCase findUserByUsernameUseCase) {
        this.findUserByUsernameUseCase = findUserByUsernameUseCase;
    }

    @GetMapping
    public ResponseEntity<UserEntityDTO2> retrieveUserByUsername(@RequestBody String username){
        return ResponseEntity.ok( new UserEntityDTO2(findUserByUsernameUseCase.findUserByUsername(username)));
    }
}
