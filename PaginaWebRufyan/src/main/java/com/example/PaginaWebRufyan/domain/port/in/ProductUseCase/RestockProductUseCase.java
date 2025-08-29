package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;

// this should just for Admin use
public interface RestockProductUseCase {
     void increaseStock(Long productId, CartItemDetails cartItemDetails);
}



