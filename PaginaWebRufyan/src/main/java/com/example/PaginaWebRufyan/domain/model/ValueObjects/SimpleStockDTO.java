package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("SIMPLE")
public record SimpleStockDTO(Integer stock,
                             StockEnum stockType) implements ProductStockDTO {
}
