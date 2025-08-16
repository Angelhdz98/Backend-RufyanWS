package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor

public class IsOriginalSelected {
    private final Boolean isOriginalSelected;


    //if isOriginal is not settled will be false
    public IsOriginalSelected() {
        this.isOriginalSelected = false;
    }
}
