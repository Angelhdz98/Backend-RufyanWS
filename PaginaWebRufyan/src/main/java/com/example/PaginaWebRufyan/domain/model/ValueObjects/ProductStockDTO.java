 package com.example.PaginaWebRufyan.domain.model.ValueObjects;

 import com.example.PaginaWebRufyan.adapter.out.ClothingStockManager;
 import com.example.PaginaWebRufyan.adapter.out.StockManager;
 import com.fasterxml.jackson.annotation.JsonSubTypes;
 import com.fasterxml.jackson.annotation.JsonTypeInfo;
 import lombok.NoArgsConstructor;


 @JsonTypeInfo(
         use = JsonTypeInfo.Id.NAME,
         include = JsonTypeInfo.As.EXISTING_PROPERTY,
         property = "stockType",
         visible = true
 )
 @JsonSubTypes({
         @JsonSubTypes.Type(value = PaintingStockDTO.class, name = "PAINTING"),
         @JsonSubTypes.Type(value = BodyClothingStockDTO.class, name = "CLOTHING"),
         @JsonSubTypes.Type(value = SimpleStockDTO.class, name = "SIMPLE")
 })
public interface ProductStockDTO {
}

