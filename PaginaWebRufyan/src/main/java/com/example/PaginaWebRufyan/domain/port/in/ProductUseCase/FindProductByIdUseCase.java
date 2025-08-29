package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;

import java.util.Optional;

public interface FindProductByIdUseCase {
ProductDomain findProductById(Long id);
}
