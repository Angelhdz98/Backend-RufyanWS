package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ItemQuantity {
    private final Integer quantity;

    public ItemQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
