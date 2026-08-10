package com.example.PaginaWebRufyan.adapter.in.ProductsController;


import com.example.PaginaWebRufyan.Service.ProductServiceAdapter.UnmarkProductAsFavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnmarkProductAsFavoriteController {
    private final UnmarkProductAsFavoriteService unmarkProductAsFavoriteService;


    public UnmarkProductAsFavoriteController(UnmarkProductAsFavoriteService unmarkProductAsFavoriteService) {
        this.unmarkProductAsFavoriteService = unmarkProductAsFavoriteService;
    }

    @PutMapping("/products/unmark-as-favorite/{productId}")
    public ResponseEntity<Boolean> unmarkProductASFavorite (@PathVariable Long productId){
       return ResponseEntity.ok(unmarkProductAsFavoriteService.unmarkProductAsFavorite(productId));
    }
}
