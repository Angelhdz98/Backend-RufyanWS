package com.example.PaginaWebRufyan.domain.model.ValueObjects;

public record PaintingStockDTO(
        Boolean isOriginalAvailable,
        Integer availableCopies,
        Integer copiesMade
) implements ProductStockDTO {
}
