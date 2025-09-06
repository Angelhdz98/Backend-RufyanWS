package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;
@Getter
public class PaintingPriceManager extends PriceManagerBase {
   private static final BigDecimal MIN_ORIGINAL_PRICE = new BigDecimal("500");
    private static final BigDecimal MIN_COPY_PRICE = new BigDecimal("300");
   private final BigDecimal pricePerCopy;
   private final BigDecimal pricePerOriginal;


    public PaintingPriceManager(BigDecimal pricePerCopy, BigDecimal pricePerOriginal) {
        this.pricePerCopy = pricePerCopy;
        this.pricePerOriginal = pricePerOriginal;
    }

    public PaintingPriceManager() {
        this.pricePerCopy = MIN_COPY_PRICE;
        this.pricePerOriginal = MIN_ORIGINAL_PRICE;
    }
    @Override
    public BigDecimal getPriceWithDetails(CartItemDetails details) {
        return null;
    }

    @Override
    public Map<String, Object> getPriceMap() {
        return Map.of();
    }
}
