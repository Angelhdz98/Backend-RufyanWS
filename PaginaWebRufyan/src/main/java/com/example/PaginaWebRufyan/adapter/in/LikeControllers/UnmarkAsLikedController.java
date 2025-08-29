package com.example.PaginaWebRufyan.adapter.in.LikeControllers;

import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.DislikeUseCase;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/like")
public class UnmarkAsLikedController {
    private final DislikeUseCase dislikeUseCase;

    public UnmarkAsLikedController(DislikeUseCase dislikeUseCase) {
        this.dislikeUseCase = dislikeUseCase;
    }

    @DeleteMapping
    public void dislike(@RequestBody LikeCommand likeCommand){
        dislikeUseCase.dislike(likeCommand);
    }
}
