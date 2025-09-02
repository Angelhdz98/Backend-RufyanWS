package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindPagedProductsUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindPagedProductsService implements FindPagedProductsUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public FindPagedProductsService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }


    @Override
    public Page<ProductDomain> findPagedProducts(Pageable pageable) {
        return productRepositoryPort.findPagedProducts(pageable);
    }
}
