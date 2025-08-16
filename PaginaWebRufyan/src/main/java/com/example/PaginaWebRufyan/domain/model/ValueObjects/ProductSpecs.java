package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.Image.Image;

import java.util.Set;

public record ProductSpecs(String name, Set<Image>productImages, ProductStockDTO productStock,
                           ProductPricingDTO productPricing) {
}
