package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;

public interface CreateProductUseCase {
    ProductDomain createProduct(CreateProductCommand command);
}
