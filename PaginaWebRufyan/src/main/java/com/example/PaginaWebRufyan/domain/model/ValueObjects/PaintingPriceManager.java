package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;
@Getter
public class PaintingPriceManager extends PriceManagerBase {
   public static final BigDecimal MIN_ORIGINAL_PRICE = new BigDecimal("500");
    public static final BigDecimal MIN_COPY_PRICE = new BigDecimal("300");
   private final BigDecimal pricePerCopy;
   private final BigDecimal pricePerOriginal;


    public PaintingPriceManager(BigDecimal pricePerCopy, BigDecimal pricePerOriginal) {
        if(pricePerCopy.compareTo(MIN_COPY_PRICE)<0 )
            throw new IllegalArgumentException("El precio por copia no puede ser menor a "+MIN_COPY_PRICE);
        if(pricePerOriginal.compareTo(MIN_ORIGINAL_PRICE)<0 )
            throw new IllegalArgumentException("El precio por obra original no puede ser menor a "+MIN_ORIGINAL_PRICE);
        if(pricePerOriginal.compareTo(pricePerCopy)<=0)
            throw new IllegalArgumentException("El precio por obra original no puede ser menor o igual al precio por copia");
        this.pricePerCopy = pricePerCopy;
        this.pricePerOriginal = pricePerOriginal;
    }

    public PaintingPriceManager() {
        this.pricePerCopy = MIN_COPY_PRICE;
        this.pricePerOriginal = MIN_ORIGINAL_PRICE;
    }
    @Override
    public BigDecimal getPriceWithDetails(CartItemDetails details) {
        if(!(details instanceof PaintingItemDetails paintingDetails))
            throw new IllegalArgumentException("Los detalles deben ser de tipo PaintingDetails");
        if(paintingDetails.getIsOriginalSelected().getIsOriginalSelected()&& paintingDetails.getItemQuantity().getQuantity()>1)
            throw new IllegalArgumentException("No se puede seleccionar obra original con cantidad mayor a 1");
        return paintingDetails.getIsOriginalSelected().getIsOriginalSelected() ? pricePerOriginal : (pricePerCopy.multiply(new BigDecimal(paintingDetails.getItemQuantity().getQuantity())));
    }

    @Override
    public Map<String, Object> getPriceMap() {
        return Map.of();
    }
}
