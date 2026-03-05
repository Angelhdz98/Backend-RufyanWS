package com.example.PaginaWebRufyan.adapter.in.LikeControllers;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.adapter.in.ProductLikedDto;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.CreateLikeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('CLIENT')")
public class MarkAsLikedController {
   private final CreateLikeUseCase createLikeUseCase;
   private final CurrentUserService currentUserService;
    public MarkAsLikedController(CreateLikeUseCase createLikeUseCase, CurrentUserService currentUserService) {
        this.createLikeUseCase = createLikeUseCase;
        this.currentUserService = currentUserService;
    }

    @PostMapping("/like/{productId}")
    ResponseEntity<ProductLikedDto> markProductAsLiked(@PathVariable Long productId){
        UserDomain currentUser = currentUserService.getCurrentUser();

        return ResponseEntity.ok( new ProductLikedDto(createLikeUseCase.markAsLiked( new LikeCommand(currentUser.getId(), productId)).getProductId()));
    }

}
