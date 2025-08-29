package com.example.PaginaWebRufyan.adapter.out.persistence;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class PaintingItemDetailsAdapter extends CartItemDetailsAdapter {
    private final Boolean isOriginalSelected;
    public PaintingItemDetailsAdapter(Integer quantity, Boolean isOriginalSelected) {
        super(quantity);
        this.isOriginalSelected = isOriginalSelected;
    }

    public PaintingItemDetailsAdapter() {
        super(1);
        this.isOriginalSelected =false;

    }
}
