package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;

import java.util.Map;

public record BodyClothingStockDTO(Map<ClothingSizeEnum, Integer> stockPerSize) implements  ProductStockDTO {
}
