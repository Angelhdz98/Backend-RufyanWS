package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByIdUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/find-users")
public class FindUserByIdController {
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final UserEntityMapper userEntityMapper;
    public FindUserByIdController(FindUserByIdUseCase findUserByIdUseCase, UserEntityMapper userEntityMapper) {
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.userEntityMapper = userEntityMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserEntityDTO2> retrieveUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userEntityMapper.toDto(findUserByIdUseCase.findUserById(userId)));
    }
}
