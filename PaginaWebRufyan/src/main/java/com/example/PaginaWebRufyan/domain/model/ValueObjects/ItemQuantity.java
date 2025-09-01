package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Embeddable
public class ItemQuantity {
    private final Integer quantity;

    public ItemQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
