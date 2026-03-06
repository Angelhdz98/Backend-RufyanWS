package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.User.DTO.UserEntityDTO;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindPagedUsersUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class FindPagedUsersController {

    private final FindPagedUsersUseCase findPagedUsersUseCase;
    private final UserEntityMapper userEntityMapper;

    public FindPagedUsersController(FindPagedUsersUseCase findPagedUsersUseCase, UserEntityMapper userEntityMapper) {
        this.findPagedUsersUseCase = findPagedUsersUseCase;
        this.userEntityMapper = userEntityMapper;
    }
    @GetMapping
    ResponseEntity<Page<UserEntityDTO2>> retrievePagedUsers(Pageable pageable){
        return ResponseEntity.ok(findPagedUsersUseCase.findPagedUsers(pageable).map(userEntityMapper::toDto));
    }

}
