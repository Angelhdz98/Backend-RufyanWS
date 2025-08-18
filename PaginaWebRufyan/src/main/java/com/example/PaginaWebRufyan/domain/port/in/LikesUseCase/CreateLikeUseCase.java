package com.example.PaginaWebRufyan.domain.port.in.LikesUseCase;

import com.example.PaginaWebRufyan.domain.model.LikesDomain;

public interface CreateLikeUseCase {

    LikesDomain markAsLiked(LikesDomain likeDomain);

}
