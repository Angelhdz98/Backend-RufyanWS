package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import java.math.BigDecimal;

public record PaintingPricingDTO(
        BigDecimal pricePerOriginal,
        BigDecimal pricePerCopy) implements ProductPricingDTO {
}
