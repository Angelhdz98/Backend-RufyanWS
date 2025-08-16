package com.example.PaginaWebRufyan.domain.model.ValueObjects;


import com.example.PaginaWebRufyan.domain.model.ProductDomain;

import java.util.Map;


public abstract class StockManagerBase {


    public abstract Map<String,Integer> getStock();
    public abstract void decreaseStock(ProductDomain productDomain
    ,CartItemDetails details);
    public abstract void increaseStock(ProductDomain productDomain,CartItemDetails details);

}
