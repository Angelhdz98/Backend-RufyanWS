package com.example.PaginaWebRufyan.domain.port.in.LikesUseCase;

import com.example.PaginaWebRufyan.adapter.in.LikeControllers.LikeCommand;
import com.example.PaginaWebRufyan.domain.model.LikeDomain;

public interface CreateLikeUseCase {

    LikeDomain markAsLiked(LikeCommand likeCommand);

}
