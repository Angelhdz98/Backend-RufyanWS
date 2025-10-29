package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.adapter.in.GetCommand;
import com.example.PaginaWebRufyan.adapter.in.LikeControllers.LikeCommand;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.GetPagedUsersThatLikedUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class FindUsersWhoLikedProductController {
    private final GetPagedUsersThatLikedUseCase getPagedUsersThatLikedUseCase;

    public FindUsersWhoLikedProductController(GetPagedUsersThatLikedUseCase getPagedUsersThatLikedUseCase) {
        this.getPagedUsersThatLikedUseCase = getPagedUsersThatLikedUseCase;
    }

    @GetMapping("/liked")
    ResponseEntity<Page<UserEntityDTO2>> getUsersThatLikedProductById(GetCommand getCommand){
        return ResponseEntity.ok(getPagedUsersThatLikedUseCase.getPagedUsersThatLiked(getCommand.productId(), PageRequest.of(getCommand.pageNumber().intValue(),getCommand.pageSize().intValue(), Sort.by(getCommand.sortBy()) )).map(UserEntityMapper::toDto));
    }
}
