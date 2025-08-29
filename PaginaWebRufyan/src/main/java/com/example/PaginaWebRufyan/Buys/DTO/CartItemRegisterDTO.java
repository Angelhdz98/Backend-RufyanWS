package com.example.PaginaWebRufyan.Buys.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRegisterDTO {
private Integer productId;
private Integer userId;
private Integer quantity;
private Boolean isOriginalSelected;
}



