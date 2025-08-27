package com.example.PaginaWebRufyan.Products.Entity;

import com.example.PaginaWebRufyan.adapter.out.ClothingStockAdapter;
import com.example.PaginaWebRufyan.adapter.out.SinglePriceManager;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BodyClotheTypesEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PrintingTecniqueEnum;
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
    private PrintingTecniqueEnum printingTecnique;
    private BodyClotheTypesEnum bodyClotheType;


}