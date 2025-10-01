package com.example.PaginaWebRufyan.Service.LikesServiceAdapter;

import com.example.PaginaWebRufyan.adapter.out.persistence.SpringDataProductRepository;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.GetPagedProductsLikedByUserUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
 public class FindPagedProductsLikedByUserService implements GetPagedProductsLikedByUserUseCase {

    private final ProductRepositoryPort productRepositoryport;

    public FindPagedProductsLikedByUserService(ProductRepositoryPort productRepositoryport) {
        this.productRepositoryport = productRepositoryport;
    }

    @Override
    public Page<ProductDomain> getPagedProductsLikedByUser(Long userId, Pageable pageable) {
        return  productRepositoryport.findProductsLikedByUser(userId, pageable);
    }
}
