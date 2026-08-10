package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IsProductFavoriteController {
    private final ProductRepositoryPort productRepositoryPort;

    public IsProductFavoriteController(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }
    @GetMapping("/products/is-favorite/{productId}")
    public ResponseEntity<Boolean> isProductFavorite(@PathVariable Long productId ){
      return ResponseEntity.ok(productRepositoryPort.retrieveProductById(productId).getIsFavorite());

    }
}
