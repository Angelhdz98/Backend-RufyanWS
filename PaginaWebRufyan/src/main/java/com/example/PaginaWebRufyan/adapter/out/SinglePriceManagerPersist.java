package com.example.PaginaWebRufyan.adapter.out;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.PricingTypeEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class SinglePriceManagerPersist extends PriceManagerPersist {

    private BigDecimal price;

    protected SinglePriceManagerPersist(){
        this.price = SinglePriceManager.MIN_PRICE;
    }

    public SinglePriceManagerPersist(Long id,BigDecimal price) {
        this.price = price;
    }


    @Override
    public PricingTypeEnum getPricingType() {
        return PricingTypeEnum.SIMPLE;
    }
}
