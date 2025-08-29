package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import lombok.Getter;

@Getter
public class CartItemDomain {
    private final Long id;
    private final ProductDomain product;
    private final CartItemDetails details;



    public CartItemDomain(Long id,  ProductDomain product, CartItemDetails details) {
        this.id = id;
        this.product = product;
        this.details = details;
    }
}
