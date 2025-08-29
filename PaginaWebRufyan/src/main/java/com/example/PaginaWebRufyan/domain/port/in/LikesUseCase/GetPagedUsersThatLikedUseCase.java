package com.example.PaginaWebRufyan.domain.port.in.LikesUseCase;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetPagedUsersThatLikedUseCase {
    Page<UserDomain> getPagedUsersThatLiked(Long productId, Pageable pageable);

}
