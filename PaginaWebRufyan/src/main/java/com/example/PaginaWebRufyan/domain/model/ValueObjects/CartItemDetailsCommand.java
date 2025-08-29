package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.Getter;

@Getter
public abstract class CartItemDetailsCommand {
    private final Integer quantity;

    protected CartItemDetailsCommand(Integer quantity) {
        this.quantity = quantity;
    }

}
