package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindPagedProductsUseCase {
    Page<ProductDomain> findPagedProducts(Pageable pageable);
}
