package com.example.PaginaWebRufyan.adapter.out.persistence;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class CartItemDetailsAdapter {
private Integer quantity;

    public CartItemDetailsAdapter(Integer quantity) {
        this.quantity = quantity;
    }
    public CartItemDetailsAdapter() {
        this.quantity = 1;
    }

}
