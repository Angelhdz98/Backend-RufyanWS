package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PriceManagerBase;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.StockManagerBase;

import java.util.Set;

public class BodyClothingDomain extends ProductDomain {


    public BodyClothingDomain(Long id, String name, StockManagerBase stockManagerBase, PriceManagerBase priceManagerBase, Set<ImageDomain> images, ProductDomainDetails productDetails, ProductTypeEnum productType, String description, Boolean isFavorite) {
        super(id, name, stockManagerBase, priceManagerBase, images, productDetails, productType, description, isFavorite);
    }

    public BodyClothingDomain() {
    }


}
