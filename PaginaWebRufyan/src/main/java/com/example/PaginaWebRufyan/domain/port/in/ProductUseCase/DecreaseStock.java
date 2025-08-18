package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;

public interface DecreaseStock {

    void decreaseStock(Long productId, CartItemDetails cartItemDetails);
}
