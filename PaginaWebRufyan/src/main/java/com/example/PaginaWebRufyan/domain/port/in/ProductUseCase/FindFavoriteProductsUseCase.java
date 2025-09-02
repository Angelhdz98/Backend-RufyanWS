package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindFavoriteProductsUseCase {
    Page<ProductDomain> findFavoriteProducts(Pageable pageable);
}
