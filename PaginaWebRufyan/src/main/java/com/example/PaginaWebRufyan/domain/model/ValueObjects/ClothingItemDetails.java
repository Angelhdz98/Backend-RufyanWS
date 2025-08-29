package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Component
public class ClothingItemDetails extends CartItemDetails {

    private final ClothingSizeEnum clothingSizeEnum;
    private final ClothingColorEnum color;

    public ClothingItemDetails(ClothingColorEnum color, ClothingSizeEnum clothingSizeEnum) {
        super(1);
        this.color = color;
        this.clothingSizeEnum = clothingSizeEnum;
    }

    public ClothingItemDetails(Integer quantity, ClothingColorEnum color, ClothingSizeEnum clothingSizeEnum) {
        super(quantity);
        this.color = color;
        this.clothingSizeEnum = clothingSizeEnum;
    }



    @Override
    public String showDetails() {
        return   "Cantidad: "+ this.getItemQuantity() + "\n" + "Talla: " + clothingSizeEnum+ "\n"+ "Color: " + color.toString();
    }

    @Override
    public Map<String, String> detailsObject() {
        Map<String, String> detailsObject = new LinkedHashMap<>();
        detailsObject.put(ClothingSizeEnum.class.getSimpleName(),clothingSizeEnum.toString());
        detailsObject.put(ClothingColorEnum.class.getSimpleName(),color.toString());
        detailsObject.put(ItemQuantity.class.getSimpleName(),getItemQuantity().getQuantity().toString());

        return detailsObject;
    }


}
