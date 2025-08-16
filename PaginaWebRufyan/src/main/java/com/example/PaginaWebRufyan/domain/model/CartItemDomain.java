package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import lombok.Getter;

@Getter
public class CartItemDomain {
    private final Long id;
    private final Long cartId;
    private final ProductDomain product;
    private final CartItemDetails details;



    public CartItemDomain(Long id, Long cartId, ProductDomain product, CartItemDetails details) {
        this.id = id;
        this.cartId = cartId;
        this.product = product;
        this.details = details;
    }
}
