package com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase;

import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.CartItemCommand;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;

public interface AddCartItemUseCase {
    ShoppingCartDomain addCartItem(CartItemCommand cartItemCommand);
}
