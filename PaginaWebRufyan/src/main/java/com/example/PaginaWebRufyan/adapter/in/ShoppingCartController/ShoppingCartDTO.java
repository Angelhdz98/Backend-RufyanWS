package com.example.PaginaWebRufyan.adapter.in.ShoppingCartController;

import com.example.PaginaWebRufyan.Buys.DTO.CartItemDTO;

import java.math.BigDecimal;
import java.util.Set;

public record ShoppingCartDTO(Set<CartItemDTORecord> cartItemDTOSet, BigDecimal subTotalAmount) {
}
