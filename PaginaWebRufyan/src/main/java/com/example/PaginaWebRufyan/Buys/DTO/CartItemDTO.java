package com.example.PaginaWebRufyan.Buys.DTO;

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
//private ProductDTO product;
    private String productName;
    private String imageUrl;
private Integer quantity;
private String details;
private BigDecimal  pricePerUnit;



}
