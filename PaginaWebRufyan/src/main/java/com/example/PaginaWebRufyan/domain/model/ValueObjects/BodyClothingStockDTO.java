package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.util.Map;

@JsonSubTypes.Type(value = BodyClothingStockDTO.class, name = "CLOTHING")
public record BodyClothingStockDTO(Map<ClothingSizeEnum, Integer> stockPerSize,
                                   StockEnum stockType) implements  ProductStockDTO {
}
