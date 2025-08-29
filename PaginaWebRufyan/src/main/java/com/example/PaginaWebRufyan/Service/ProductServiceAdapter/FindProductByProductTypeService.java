package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindProductsByTypeUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FindProductByProductTypeService implements FindProductsByTypeUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public FindProductByProductTypeService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }


    @Override
    public Page<ProductDomain> findPagedProductsByType(ProductTypeEnum productType, Pageable pageable) {
        return productRepositoryPort.findProductByType(productType, pageable);
    }
}
