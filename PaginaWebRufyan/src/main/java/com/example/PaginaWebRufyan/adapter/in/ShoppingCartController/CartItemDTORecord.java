package com.example.PaginaWebRufyan.adapter.in.ShoppingCartController;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;

import java.math.BigDecimal;

public record CartItemDTORecord(Long id,
                                Long productId,
                                String productName,
                                String imageUrl,
                                CartItemDetails details,
                                BigDecimal totalPrice) {
}
