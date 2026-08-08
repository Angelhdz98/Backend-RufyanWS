package com.example.PaginaWebRufyan.adapter.in.LikeControllers;


import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.out.LikesRepositoryPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class isProductLikedController {
    private final CurrentUserService currentUserService;
    private final LikesRepositoryPort likesRepository;


    public isProductLikedController(CurrentUserService currentUserService, LikesRepositoryPort likesRepository) {
        this.currentUserService = currentUserService;
        this.likesRepository = likesRepository;
    }
   @GetMapping("is-product-liked/{productId}")
    ResponseEntity<Boolean> isProductLikedBy(@PathVariable Long productId) {
       UserDomain currentUser = currentUserService.getCurrentUser();
       return ResponseEntity.ok(likesRepository.existsLike(currentUser.getId(),
               productId));

    }

}
