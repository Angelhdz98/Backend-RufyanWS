package com.example.PaginaWebRufyan.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CartItemDetailsAdapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private Integer quantity;

    public CartItemDetailsAdapter(Integer quantity) {
        this.quantity = quantity;
    }
    public CartItemDetailsAdapter() {
        this.quantity = 1;
    }

}
