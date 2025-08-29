package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import java.math.BigDecimal;
import java.util.Map;

public abstract class PriceManagerBase {
    public final String badge = "MXN";
   public abstract BigDecimal getPriceWithDetails(CartItemDetails details);
   public abstract Map<String,Object> getPriceMap();


}
