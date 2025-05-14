package com.example.PaginaWebRufyan.Entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Builder
public class CartItem {
    private Product product;
    private Integer quantity;
    private Boolean isOriginalSelected;
    private BigDecimal pricePerUnit;


}
