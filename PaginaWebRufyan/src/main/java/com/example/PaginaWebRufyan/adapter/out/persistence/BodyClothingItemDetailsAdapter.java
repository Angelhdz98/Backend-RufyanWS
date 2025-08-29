package com.example.PaginaWebRufyan.adapter.out.persistence;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ClothingColorEnum;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class BodyClothingItemDetailsAdapter extends CartItemDetailsAdapter {
    private final ClothingSizeEnum clothingSizeEnum;
    private final ClothingColorEnum clothingColorEnum;
    public BodyClothingItemDetailsAdapter(Integer quantity, ClothingSizeEnum clothingSizeEnum, ClothingColorEnum clothingColorEnum) {
        super(quantity);
        this.clothingSizeEnum = clothingSizeEnum;
        this.clothingColorEnum = clothingColorEnum;
    }

    public BodyClothingItemDetailsAdapter() {
        super(1);
        this.clothingSizeEnum = ClothingSizeEnum.M;
        this.clothingColorEnum = ClothingColorEnum.BLACK;
    }
}
