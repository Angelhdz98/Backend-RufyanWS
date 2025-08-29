package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;

public interface ShoppingCartRepositoryPort {

    ShoppingCartDomain retrieveShoppingCart(Long userId);
    ShoppingCartDomain updateShoppingCart(Long userId, ShoppingCartDomain shoppingCartDomain);
    ShoppingCartDomain emptyShoppingCart(Long userId);
}
