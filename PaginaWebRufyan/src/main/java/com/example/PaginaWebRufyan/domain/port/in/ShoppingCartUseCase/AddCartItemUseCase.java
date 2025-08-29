package com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase;

import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.CartItemCommand;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;

public interface AddCartItemUseCase {
    ShoppingCartDomain addCartItemUseCase(CartItemCommand cartItemCommand);
}
