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

    public FindPagedUsersController(FindPagedUsersUseCase findPagedUsersUseCase) {
        this.findPagedUsersUseCase = findPagedUsersUseCase;
    }
    @GetMapping
    ResponseEntity<Page<UserEntityDTO2>> retrievePagedUsers(Pageable pageable){
        return ResponseEntity.ok(findPagedUsersUseCase.findPagedUsers(pageable).map(UserEntityMapper::toDto));
    }

}
