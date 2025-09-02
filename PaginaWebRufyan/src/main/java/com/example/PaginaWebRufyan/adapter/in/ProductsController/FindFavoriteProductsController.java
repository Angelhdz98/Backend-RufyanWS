package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindFavoriteProductsUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/products/favorite")
public class FindFavoriteProductsController {
    private final FindFavoriteProductsUseCase findFavoriteProductsUseCase;

    public FindFavoriteProductsController(FindFavoriteProductsUseCase findFavoriteProductsUseCase) {
        this.findFavoriteProductsUseCase = findFavoriteProductsUseCase;
    }
    @GetMapping
    ResponseEntity<Page<ProductDTO>> getFavoriteProducts(Pageable pageable){
        return ResponseEntity.ok(findFavoriteProductsUseCase.findFavoriteProducts(pageable).map(ProductDTOMapper::toDTO));
    }

}
