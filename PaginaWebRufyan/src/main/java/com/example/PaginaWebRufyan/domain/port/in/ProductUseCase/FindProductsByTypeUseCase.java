package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import org.springframework.data.domain.Page;

public interface FindProductsByTypeUseCase {
    Page<ProductDomain> findPagedProductsByType(ProductTypeEnum productType);
}
