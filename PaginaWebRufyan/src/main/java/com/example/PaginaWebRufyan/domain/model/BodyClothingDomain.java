package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PriceManagerBase;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.StockManagerBase;

import java.util.Set;

public class BodyClothingDomain extends ProductDomain {
    public BodyClothingDomain(Integer id, String name, StockManagerBase stockManagerBase, PriceManagerBase priceManagerBase, Set<Image> images, ProductDomainDetails productDetails) {
        super(id, name, stockManagerBase, priceManagerBase, images, productDetails);
    }

    @Override
    void increaseStock(CartItemDomain itemDomain) {
         getStockManagerBase()
         .increaseStock(this,itemDomain.getDetails());
    }

    @Override
    void decreaseStock(CartItemDomain itemDomain) {
        getStockManagerBase()
                .decreaseStock(this,itemDomain.getDetails());
    }
}
