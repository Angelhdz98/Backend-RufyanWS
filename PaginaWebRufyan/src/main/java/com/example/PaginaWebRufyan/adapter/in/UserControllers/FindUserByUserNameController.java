package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByUsernameUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users/by-Username")
public class FindUserByUserNameController {
    private final FindUserByUsernameUseCase findUserByUsernameUseCase;
    private final UserEntityMapper userEntityMapper;
    public FindUserByUserNameController(FindUserByUsernameUseCase findUserByUsernameUseCase, UserEntityMapper userEntityMapper) {
        this.findUserByUsernameUseCase = findUserByUsernameUseCase;
        this.userEntityMapper = userEntityMapper;
    }

    @GetMapping
    public ResponseEntity<UserEntityDTO2> retrieveUserByUsername(@RequestBody String username){
        return ResponseEntity.ok( userEntityMapper.toDto(findUserByUsernameUseCase.findUserByUsername(username)));
    }
}
