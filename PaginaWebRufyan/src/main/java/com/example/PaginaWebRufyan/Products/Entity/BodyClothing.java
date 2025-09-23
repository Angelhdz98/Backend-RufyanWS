package com.example.PaginaWebRufyan.Products.Entity;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.*;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BodyClotheTypesEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PrintingTecniqueEnum;
import jakarta.persistence.Entity;
import lombok.*;


import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class BodyClothing extends Product {
    private String clothingMaterial;
    private PrintingTecniqueEnum printingTecnique;
    private BodyClotheTypesEnum bodyClotheType;

    public BodyClothing(Long id, String name, String description, LocalDate creationDate, SinglePriceManagerPersist priceManagerPersist, ClothingStockAdapter stockManager, Boolean isAvailable, Boolean isFavorite, Set<Image> image, ProductTypeEnum productTypeEnum, String clothingMaterial, PrintingTecniqueEnum printingTecnique, BodyClotheTypesEnum bodyClotheType) {
        super(id, name, description, creationDate, priceManagerPersist, stockManager, isAvailable, isFavorite, image, productTypeEnum);
        this.clothingMaterial = clothingMaterial;
        this.printingTecnique = printingTecnique;
        this.bodyClotheType = bodyClotheType;
    }

    public BodyClothing(String clothingMaterial, PrintingTecniqueEnum printingTecnique, BodyClotheTypesEnum bodyClotheType) {
        super();
        this.clothingMaterial = clothingMaterial;
        this.printingTecnique = printingTecnique;
        this.bodyClotheType = bodyClotheType;
    }

    public BodyClothing(Long id, String name, String description, LocalDate creationDate, SinglePriceManagerPersist singlePriceManagerPersist, ClothingStockAdapter clothingStockAdapter, Boolean isAvailable, Boolean isFavorite, Set<Image> images,ProductTypeEnum productTypeEnum , String clothingMaterial, BodyClotheTypesEnum BodyClothingType, PrintingTecniqueEnum printingTechnique) {
        super(id, name, description, creationDate, singlePriceManagerPersist, clothingStockAdapter, isAvailable, isFavorite, images, productTypeEnum);
        this.clothingMaterial = clothingMaterial;
        this.printingTecnique = printingTecnique;
        this.bodyClotheType = bodyClotheType;
    }
}