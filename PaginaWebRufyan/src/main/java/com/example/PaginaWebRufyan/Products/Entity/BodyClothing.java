package com.example.PaginaWebRufyan.Products.Entity;

import com.example.PaginaWebRufyan.adapter.out.ClothingStockAdapter;
import com.example.PaginaWebRufyan.adapter.out.SinglePriceManager;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("BODYCLOTHING")
@Entity
public class BodyClothing extends Product {
    private String clothingMaterial;
    private String printingMaterial;
    @Builder.Default
    private SinglePriceManager price = new SinglePriceManager() ;
    @Builder.Default
    private ClothingStockAdapter stock = new ClothingStockAdapter();

}