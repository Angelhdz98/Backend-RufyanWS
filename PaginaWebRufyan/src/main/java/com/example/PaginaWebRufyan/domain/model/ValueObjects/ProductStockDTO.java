 package com.example.PaginaWebRufyan.domain.model.ValueObjects;

 import com.fasterxml.jackson.annotation.JsonSubTypes;
 import com.fasterxml.jackson.annotation.JsonTypeInfo;


 @JsonTypeInfo(
         use = JsonTypeInfo.Id.NAME,
         include = JsonTypeInfo.As.EXISTING_PROPERTY,
         property = "stockType",
         visible = true
 )
 @JsonSubTypes({
         @JsonSubTypes.Type(value = PaintingStockDTO.class, name = "PAINTING_STOCK"),
         @JsonSubTypes.Type(value = BodyClothingStockDTO.class, name = "CLOTHING_STOCK"),
         @JsonSubTypes.Type(value = SimpleStockDTO.class, name = "SINGLE_STOCK")
 })
public interface ProductStockDTO {
}

