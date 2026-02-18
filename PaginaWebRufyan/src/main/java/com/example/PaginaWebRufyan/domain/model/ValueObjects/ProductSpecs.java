package com.example.PaginaWebRufyan.domain.model.ValueObjects;


import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;



public record ProductSpecs(String name,
                           String description,
                           ProductStockDTO productStock,
                           ProductPricingDTO productPricing,
                           ProductTypeEnum productTypeEnum,
                           Boolean isFavorite) {
}
