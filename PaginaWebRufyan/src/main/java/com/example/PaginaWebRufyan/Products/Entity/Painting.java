package com.example.PaginaWebRufyan.Products.Entity;
import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.OriginalStockAdapter;
import com.example.PaginaWebRufyan.adapter.out.PaintingPriceManagerPersist;
import com.example.PaginaWebRufyan.adapter.out.PriceManagerPersist;
import com.example.PaginaWebRufyan.adapter.out.StockManager;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.MediumEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.SupportMaterialEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("PAINTING")
@Entity
@Getter
public class Painting extends Product {


    private final Integer alturaCm;
    private final Integer largoCm;
    private final MediumEnum medium;
    private final SupportMaterialEnum supportMaterial;



    public Painting(Long id, String name, String description, LocalDate creationDate, PriceManagerPersist priceManagerPersist, StockManager stockManager, Boolean isAvailable, Boolean isFavorite, Set<Image> image, ProductTypeEnum productTypeEnum, Integer alturaCm, Integer largoCm, MediumEnum medium, SupportMaterialEnum supportMaterial) {
        super(id, name, description, creationDate, priceManagerPersist, stockManager, isAvailable, isFavorite, image, productTypeEnum);
        this.alturaCm = alturaCm;
        this.largoCm = largoCm;
        this.medium = medium;
        this.supportMaterial = supportMaterial;
    }

    public Painting(Integer alturaCm, Integer largoCm, MediumEnum medium, SupportMaterialEnum supportMaterial) {
        this.alturaCm = alturaCm;
        this.largoCm = largoCm;
        this.medium = medium;
        this.supportMaterial = supportMaterial;
    }
}