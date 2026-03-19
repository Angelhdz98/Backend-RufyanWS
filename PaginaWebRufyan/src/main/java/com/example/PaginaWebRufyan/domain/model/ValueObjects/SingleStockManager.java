package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;

import java.util.Map;

public class SingleStockManager extends StockManagerBase {
    private Integer stock;

    public SingleStockManager(Integer stock) {
        this.stock = stock;
    }

    @Override
    public Map<String, Integer> getStock() {
        return Map.of("stock",stock);
    }

    @Override
    public void decreaseStock(ProductDomain productDomain, CartItemDetails details) {
        Integer itemQuantity = details.getItemQuantity().getQuantity();
        if(itemQuantity>this.stock) throw new IllegalArgumentException("El stock actual es de: " + stock+ " petición de: " + itemQuantity + " no puede ser procesada");
        this.stock = stock-itemQuantity;
    }

    @Override
    public void increaseStock(ProductDomain productDomain, CartItemDetails details) {
        Integer itemQuantity = details.getItemQuantity().getQuantity();
        this.stock = stock+itemQuantity;
    }

    @Override
    public Boolean isAvailable() {
        return stock>0;
    }
}
