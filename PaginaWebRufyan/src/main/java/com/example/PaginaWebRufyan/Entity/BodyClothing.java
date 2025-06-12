package com.example.PaginaWebRufyan.Entity;

import com.example.PaginaWebRufyan.Components.ClothingStock;
import com.example.PaginaWebRufyan.Components.SinglePriceManager;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("BODYCLOTHING")
@Entity
public class BodyClothing extends Product{
    private String clothingMaterial;
    private String printingMaterial;
    @Embedded
    private SinglePriceManager price;
    @Embedded
    private ClothingStock stock;
}