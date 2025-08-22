package com.example.PaginaWebRufyan.domain.port.in.LikesUseCase;

import com.example.PaginaWebRufyan.domain.model.UserDomain;

import java.util.List;

public interface GetUsersThatLikedUseCase {
    List<UserDomain> getUsersThatLiked(Long productId);
}
