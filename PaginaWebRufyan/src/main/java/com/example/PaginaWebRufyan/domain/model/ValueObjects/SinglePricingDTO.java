package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import java.math.BigDecimal;

public record SinglePricingDTO(BigDecimal price,
                               PricingTypeEnum pricingType) implements ProductPricingDTO {
}
