package com.example.PaginaWebRufyan.Service.LikesServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.LikesDomain;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.CreateLikeUseCase;
import com.example.PaginaWebRufyan.domain.port.out.LikesRepository;
import org.springframework.stereotype.Service;

@Service
public class MarkAsLikedService implements CreateLikeUseCase {

    private final LikesRepository likesRepository;

    public MarkAsLikedService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    @Override
    public LikesDomain markAsLiked(LikesDomain like) {
        return likesRepository.markAsLiked(like);
    }
}
