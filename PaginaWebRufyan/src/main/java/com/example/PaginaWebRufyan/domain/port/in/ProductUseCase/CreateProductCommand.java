package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductSpecs;

public record CreateProductCommand(ProductSpecs productSpecs, ProductDomainDetails productDetails) {
}
