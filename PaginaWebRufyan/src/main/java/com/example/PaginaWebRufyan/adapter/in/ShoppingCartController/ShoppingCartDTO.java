package com.example.PaginaWebRufyan.adapter.in.ShoppingCartController;

import java.math.BigDecimal;
import java.util.Set;

public record ShoppingCartDTO(Set<CartItemDTORecord> cartItemDTOSet, BigDecimal subTotalAmount) {
}
