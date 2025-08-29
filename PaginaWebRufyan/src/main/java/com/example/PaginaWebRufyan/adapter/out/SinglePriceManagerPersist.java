package com.example.PaginaWebRufyan.adapter.out;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class SinglePriceManagerPersist implements PriceManagerPersist {
    private BigDecimal price;
}
