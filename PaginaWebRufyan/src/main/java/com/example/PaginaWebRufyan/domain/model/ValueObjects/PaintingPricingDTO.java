package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@Getter
public class PaintingPricingDTO implements ProductPricingDTO {

    private BigDecimal pricePerOriginal;
    private BigDecimal pricePerCopy;
    private PricingTypeEnum pricingType;

    public PaintingPricingDTO(BigDecimal pricePerOriginal, BigDecimal pricePerCopy) {
        this.pricePerOriginal = pricePerOriginal;
        this.pricePerCopy = pricePerCopy;
        this.pricingType = PricingTypeEnum.ORIGINAL;
    }

    public PaintingPricingDTO(BigDecimal pricePerOriginal, BigDecimal pricePerCopy, PricingTypeEnum pricingType) {
        this.pricePerOriginal = pricePerOriginal;
        this.pricePerCopy = pricePerCopy;
        this.pricingType = PricingTypeEnum.ORIGINAL;
    }
}
