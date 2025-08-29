package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.Getter;

import java.util.Map;


@Getter
public abstract class CartItemDetails {
    private final ItemQuantity itemQuantity;

    public CartItemDetails(Integer itemQuantity) {
        this.itemQuantity = new ItemQuantity(itemQuantity);
    }

    public CartItemDetails() {
        this.itemQuantity = new ItemQuantity(1);
    }
    public abstract String showDetails();
    public abstract Map<String, String> detailsObject();
}
