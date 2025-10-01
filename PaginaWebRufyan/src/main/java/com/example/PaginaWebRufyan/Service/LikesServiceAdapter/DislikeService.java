package com.example.PaginaWebRufyan.Service.LikesServiceAdapter;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.adapter.in.LikeControllers.LikeCommand;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.DislikeUseCase;
import com.example.PaginaWebRufyan.domain.port.out.LikesRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DislikeService  implements DislikeUseCase {

    private final LikesRepositoryPort likesRepositoryPort;
    public DislikeService(LikesRepositoryPort likesRepositoryPort) {
        this.likesRepositoryPort = likesRepositoryPort;
    }


    @Override
    public void dislike(LikeCommand like) {
        if (!likesRepositoryPort.existsLike(like.userId(), like.productId())) throw new ResourceNotFoundException("No se ha marcado la obra como \"me gusta\" ");
        likesRepositoryPort.unmarkAsLiked(like.userId(),like.productId());
    }
}
