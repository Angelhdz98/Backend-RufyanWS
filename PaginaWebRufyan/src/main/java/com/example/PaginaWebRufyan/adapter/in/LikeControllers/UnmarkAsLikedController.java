package com.example.PaginaWebRufyan.adapter.in.LikeControllers;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.DislikeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('CLIENT')")
public class UnmarkAsLikedController {
    private final DislikeUseCase dislikeUseCase;
    private final CurrentUserService currentUserService;

    public UnmarkAsLikedController(DislikeUseCase dislikeUseCase, CurrentUserService currentUserService) {
        this.dislikeUseCase = dislikeUseCase;
        this.currentUserService = currentUserService;
    }

    @DeleteMapping("/like/{productId}")
    public ResponseEntity<Void> dislike(@PathVariable Long productId){

        UserDomain currentUser = currentUserService.getCurrentUser();
        dislikeUseCase.dislike(new LikeCommand(currentUser.getId(), productId));
        return ResponseEntity.noContent().build();
    }
}
