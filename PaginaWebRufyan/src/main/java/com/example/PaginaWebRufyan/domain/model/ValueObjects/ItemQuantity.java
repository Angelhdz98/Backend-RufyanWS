package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import lombok.Getter;


@Getter
@Embeddable
public class ItemQuantity {
    private final Integer quantity;
    @JsonCreator
    public ItemQuantity(Integer quantity) {
        if(quantity == null || quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        this.quantity = quantity;
    }
    protected ItemQuantity() {
        this.quantity = 1; // valor dummy para Hibernate
    }

    @JsonValue
    public Integer getQuantity() {
        return quantity;
    }

}
