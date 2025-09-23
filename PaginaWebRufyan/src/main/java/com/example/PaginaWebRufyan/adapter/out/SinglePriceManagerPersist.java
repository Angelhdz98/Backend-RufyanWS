package com.example.PaginaWebRufyan.adapter.out;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter

public class SinglePriceManagerPersist extends PriceManagerPersist {

    private BigDecimal price;

    protected SinglePriceManagerPersist(){
        this.price = SinglePriceManager.MIN_PRICE;
    }
    public SinglePriceManagerPersist(Long id,BigDecimal price) {
        super(id);
        this.price = price;
    }



}
