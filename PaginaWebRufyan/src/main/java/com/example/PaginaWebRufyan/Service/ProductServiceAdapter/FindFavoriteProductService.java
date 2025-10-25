package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindFavoriteProductsUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindFavoriteProductService implements FindFavoriteProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public FindFavoriteProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }


    @Override
    public Page<ProductDomain> findFavoriteProducts(Pageable pageable) {
        return productRepositoryPort.findFavoriteProducts(pageable);
    }
}
