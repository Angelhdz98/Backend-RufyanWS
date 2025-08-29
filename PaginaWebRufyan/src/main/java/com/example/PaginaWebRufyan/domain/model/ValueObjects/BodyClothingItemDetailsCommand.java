package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import lombok.Getter;

@Getter
public class BodyClothingItemDetailsCommand extends CartItemDetailsCommand {
    private final ClothingSizeEnum clothingSize;
    private final ClothingColorEnum color;

    protected BodyClothingItemDetailsCommand(Integer quantity, ClothingSizeEnum clothingSize, ClothingColorEnum color) {
        super(quantity);
        this.clothingSize = clothingSize;
        this.color = color;
    }
}
