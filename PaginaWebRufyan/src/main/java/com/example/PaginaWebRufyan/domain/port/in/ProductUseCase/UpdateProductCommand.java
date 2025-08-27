package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductSpecs;

import java.util.Set;

public record UpdateProductCommand(Long userId,
        ProductSpecs productSpecs,
                                   ProductDomainDetails productDomainDetails,
                                   Set<ImageDomain> updatedImages) {
}
