package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.OriginalStockAdapter;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
public class PaintingDomain extends ProductDomain{
    ;

    public PaintingDomain(Long id, String name, PaintingStockManager paintingStockManager, PaintingPriceManager paintingPriceManager, Set<ImageDomain> images, ProductDomainDetails productDetails, ProductTypeEnum productType, String description, Boolean isFavorite) {
        super(id, name, paintingStockManager, paintingPriceManager, images, productDetails, productType, description, isFavorite);
    }
    public PaintingDomain() {
        super(new PaintingStockManager(), new PaintingPriceManager(), Set.of());
    }

    public PaintingDomain(PaintingStockManager paintingStockManager, PriceManagerBase priceManagerBase) {
        super(new PaintingStockManager(), new PaintingPriceManager(), Set.of());
    }

    public PaintingDomain(StockManagerBase stockManagerBase) {
        super(stockManagerBase, new PaintingPriceManager(), Set.of());
    }

    public PaintingDomain( PriceManagerBase priceManager) {
        super(new PaintingStockManager(), priceManager, Set.of());
    }




}
