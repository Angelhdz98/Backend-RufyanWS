package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Set;


public record ProductSpecs(String name,
                           String description,
                           ProductStockDTO productStock,
                           ProductPricingDTO productPricing,
                           ProductTypeEnum productTypeEnum,
                           Boolean isFavorite) {
}
