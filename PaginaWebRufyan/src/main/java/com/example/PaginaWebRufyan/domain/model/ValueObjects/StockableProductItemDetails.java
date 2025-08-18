package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.Getter;

import java.util.Map;

@Getter
public class StockableProductItemDetails extends CartItemDetails {


    public StockableProductItemDetails(Integer quantity) {
        super(quantity);
    }
    public StockableProductItemDetails() {
        super(1);
    }

    @Override
    public String showDetails() {
        return "Piezas: " + getItemQuantity();
    }

    @Override
    public Map<String, String> detailsObject() {
        return Map.of(getItemQuantity().getClass().getSimpleName(), getItemQuantity().getQuantity().toString());

    }
}
