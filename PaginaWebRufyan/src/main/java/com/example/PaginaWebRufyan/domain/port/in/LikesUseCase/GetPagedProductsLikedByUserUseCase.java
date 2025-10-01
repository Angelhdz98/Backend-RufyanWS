package com.example.PaginaWebRufyan.domain.port.in.LikesUseCase;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetPagedProductsLikedByUserUseCase {
    Page<ProductDomain> getPagedProductsLikedByUser(Long userId, Pageable pageable);
}
