package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
public abstract class PriceManagerBase {
    private final Long id;
    public static final String badge = "MXN";
   public abstract BigDecimal getPriceWithDetails(CartItemDetails details);
   public abstract Map<String,Object> getPriceMap();

    protected PriceManagerBase() {
         this.id = null;
    }

}
