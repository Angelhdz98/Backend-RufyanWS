package com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase;

import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.DeleteCartItemCommand;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;

public interface DeleteCartItemUseCase {
    ShoppingCartDomain deleteCartItemUseCase(DeleteCartItemCommand cartItemCommand);
}
