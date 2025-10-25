package com.example.PaginaWebRufyan.adapter.out;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PriceManagerBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

@Setter
@Getter
@ToString
public class SinglePriceManager extends PriceManagerBase {
    public static final BigDecimal MIN_PRICE= BigDecimal.valueOf(200);
    private BigDecimal price;

    public SinglePriceManager(){
        this.price = MIN_PRICE;
    }


    public SinglePriceManager(BigDecimal price) {
        if(price.compareTo(MIN_PRICE)<0)
            throw new IllegalArgumentException("El precio no puede ser menor a "+MIN_PRICE);
        this.price = price;
    }

    @Override
    public BigDecimal getPriceWithDetails(CartItemDetails details) {
        return price.multiply(new BigDecimal(details.getItemQuantity().getQuantity()));
    }

    @Override
    public Map<String, Object> getPriceMap() {
        return Map.of("Precio: ",price);
    }
}
