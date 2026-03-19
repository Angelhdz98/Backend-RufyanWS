package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

import java.util.Map;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "productType")
    @JsonSubTypes({@JsonSubTypes.Type(value= ClothingItemDetails.class, name= "CLOTHING"),
                @JsonSubTypes.Type(value = PaintingItemDetails.class, name = "PAINTING" ),
                @JsonSubTypes.Type(value = StockableProductItemDetails.class, name = "STOCKABLE" )})

@Getter
public abstract class CartItemDetails {
    private final ItemQuantity itemQuantity;

    public CartItemDetails(Integer itemQuantity) {
        this.itemQuantity = new ItemQuantity(itemQuantity);
    }

    public CartItemDetails() {
        this.itemQuantity = new ItemQuantity(1);
    }
    public abstract String showDetails();
    public abstract Map<String, String> detailsObject();
    public abstract boolean areSameDetails(CartItemDetails other);
}
