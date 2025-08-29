package com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase;

import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;

public interface UpdateShoppingCartUseCase {
    ShoppingCartDomain updateShoppingCart(Long userId, ShoppingCartDomain shoppingCartDomain);
}
