package com.example.PaginaWebRufyan.adapter.in;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductPricingDTO;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductStockDTO;

import java.util.Set;

public record ProductDTO(String name, Set<ImageDomain> images, ProductStockDTO productStockDTO, ProductPricingDTO productPricingDTO, ProductTypeEnum productTypeEnum, ProductDomainDetails productDetails) {
}
