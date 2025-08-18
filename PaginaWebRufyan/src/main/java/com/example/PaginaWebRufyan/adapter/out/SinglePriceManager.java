package com.example.PaginaWebRufyan.adapter.out;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Embeddable
@Getter
@ToString
public class SinglePriceManager implements PriceManager {
    private final BigDecimal MIN_PRICE= BigDecimal.valueOf(200);
    private BigDecimal price;

    public SinglePriceManager(){
        this.price = MIN_PRICE;
    }


    public SinglePriceManager(BigDecimal price) {
        this.price = price;
    }

}
