package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;


@Getter
@Embeddable
public class ItemQuantity {
    private final Integer quantity;

    public ItemQuantity(Integer quantity) {
        if(quantity == null || quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        this.quantity = quantity;
    }


}
