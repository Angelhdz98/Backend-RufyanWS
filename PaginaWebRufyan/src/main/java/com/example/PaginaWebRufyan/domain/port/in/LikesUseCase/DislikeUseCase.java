package com.example.PaginaWebRufyan.domain.port.in.LikesUseCase;

import com.example.PaginaWebRufyan.adapter.in.LikeControllers.LikeCommand;

public interface DislikeUseCase {

    void dislike(LikeCommand like);

}
