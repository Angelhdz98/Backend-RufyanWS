package com.example.PaginaWebRufyan.adapter.in.ShoppingCartController;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;

public record AddCartItemRequestDTO(
        Long productId,
        CartItemDetails cartItemDetails
                                 ) {
}
