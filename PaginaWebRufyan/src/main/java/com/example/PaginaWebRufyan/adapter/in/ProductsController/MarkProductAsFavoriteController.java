package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.Service.ProductServiceAdapter.MarkProductAsFavoriteService;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkProductAsFavoriteController {
    private final MarkProductAsFavoriteService markProductAsFavoriteService;

    public MarkProductAsFavoriteController(MarkProductAsFavoriteService markProductAsFavoriteService, CurrentUserService currentUserService) {
        this.markProductAsFavoriteService = markProductAsFavoriteService;
    }


    @PutMapping("/products/mark-as-favorite/{productId}")
    public ResponseEntity<Boolean> markProductAsFavorite(@PathVariable Long productId){
    return ResponseEntity.ok(markProductAsFavoriteService.markProductAsFavorite(productId));
    }

}
