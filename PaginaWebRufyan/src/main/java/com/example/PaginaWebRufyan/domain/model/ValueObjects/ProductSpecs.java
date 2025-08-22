package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;

import java.util.Set;

public record ProductSpecs(String name, Set<Image>productImages, ProductStockDTO productStock,
                           ProductPricingDTO productPricing, ProductTypeEnum productTypeEnum) {
}
