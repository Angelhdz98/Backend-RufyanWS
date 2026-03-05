package com.example.PaginaWebRufyan.adapter.out.persistence;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
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
