package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.CartItem;
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
    this.isOriginalSelected = cartItem.getIsOriginalSelected();
    this.pricePerUnit = cartItem.getPricePerUnit();
}

}
