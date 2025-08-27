package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.CartItemDomain;

public interface IncreaseStockUseCase {
    void increaseProductStock(CartItemDomain cartItemDomain);
}
