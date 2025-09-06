package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.SinglePriceManager;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;

import java.util.Set;

public class BodyClothingDomain extends ProductDomain {


    public BodyClothingDomain(Long id, String name, BodyClothingStockManager bodyClothingStockManager, SinglePriceManager singlePriceManager, Set<ImageDomain> images, ProductDomainDetails productDetails, ProductTypeEnum productType, String description, Boolean isFavorite) {
        super(id, name, bodyClothingStockManager, singlePriceManager, images, productDetails, productType, description, isFavorite);
    }

    public BodyClothingDomain() {
        super(new BodyClothingStockManager(),new SinglePriceManager(), Set.of());

    }
    public BodyClothingDomain(BodyClothingStockManager bodyClothingStockManager) {
        super(bodyClothingStockManager,new SinglePriceManager(), Set.of());

    }
    public BodyClothingDomain(SinglePriceManager singlePriceManager) {
        super(new BodyClothingStockManager(), singlePriceManager, Set.of());

    }


}
