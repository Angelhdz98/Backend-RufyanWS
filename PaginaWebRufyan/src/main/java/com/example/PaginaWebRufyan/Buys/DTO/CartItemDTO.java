package com.example.PaginaWebRufyan.Buys.DTO;

import com.example.PaginaWebRufyan.Buys.Entity.CartItem;
import com.example.PaginaWebRufyan.Products.DTO.Product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CartItemDTO {
private ProductDTO product;
private Integer quantity;
private Boolean isOriginalSelected;
private BigDecimal  pricePerUnit;

public CartItemDTO (CartItem cartItem){
    this.product = new ProductDTO(cartItem.getProduct());
    this.quantity = cartItem.getQuantity();
    this.isOriginalSelected =Boolean.valueOf(cartItem.getDetails().get("isOriginalSelected"));
    this.pricePerUnit = cartItem.getPricePerUnit();

}

}
