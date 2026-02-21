package com.example.PaginaWebRufyan.adapter.in.LikeControllers;

import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.DislikeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('CLIENT')")
public class UnmarkAsLikedController {
    private final DislikeUseCase dislikeUseCase;

    public UnmarkAsLikedController(DislikeUseCase dislikeUseCase) {
        this.dislikeUseCase = dislikeUseCase;
    }

    @DeleteMapping("/like")
    public ResponseEntity<Void> dislike(@RequestBody LikeCommand likeCommand){
        dislikeUseCase.dislike(likeCommand);
       return ResponseEntity.noContent().build();
    }
}
