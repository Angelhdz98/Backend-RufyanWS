package com.example.PaginaWebRufyan.Service.LikesServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.LikesDomain;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.DislikeUseCase;
import com.example.PaginaWebRufyan.domain.port.out.LikesRepository;
import org.springframework.stereotype.Service;

@Service
public class DislikeService  implements DislikeUseCase {

    private final LikesRepository likesRepository;
    public DislikeService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }


    @Override
    public void dislike(LikesDomain like) {
        likesRepository.unmarkAsLiked(like.getUserId(),like.getProductId());

    }
}
