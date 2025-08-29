package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.Getter;

@Getter
public class PaintingItemDetailsCommand
        extends CartItemDetailsCommand {
    private final Boolean isOriginalSelected;

    public PaintingItemDetailsCommand(Integer quantity, Boolean isOriginalSelected) {
        super(quantity);

        this.isOriginalSelected = isOriginalSelected;
    }

    public PaintingItemDetailsCommand() {
        super(1);
        this.isOriginalSelected = false;
    }
}
