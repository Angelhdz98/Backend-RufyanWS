package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.adapter.out.OriginalStockAdapter;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import lombok.Getter;

import java.util.Set;

@Getter
public class PaintingDomain extends ProductDomain{

    public PaintingDomain(Long id, String name, StockManagerBase stockManagerBase, PriceManagerBase priceManagerBase, Set<Image> images, ProductDomainDetails productDetails) {
        super(id, name, stockManagerBase, priceManagerBase, images, productDetails);
    }

    @Override
    void increaseStock(CartItemDomain itemDomain) {

        getStockManagerBase()
        .increaseStock(this,itemDomain.getDetails());
    }

    @Override
    void decreaseStock(CartItemDomain itemDomain) {
        getStockManagerBase().decreaseStock(this,itemDomain.getDetails());
    }



}
