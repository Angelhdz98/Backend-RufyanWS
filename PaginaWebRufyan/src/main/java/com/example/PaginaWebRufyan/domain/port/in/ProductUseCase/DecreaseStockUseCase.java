package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;

public interface DecreaseStockUseCase {

    void decreaseStock(CartItemDomain cartItemDomain);
}
