package com.example.PaginaWebRufyan.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Product product;
    private Integer quantity;
    private Boolean isOriginalSelected;
    private BigDecimal pricePerUnit;


}
