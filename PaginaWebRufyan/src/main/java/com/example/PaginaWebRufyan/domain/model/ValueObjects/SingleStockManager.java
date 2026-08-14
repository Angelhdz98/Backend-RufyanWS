package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import lombok.Getter;

import java.util.Map;

public class SingleStockManager extends StockManagerBase {
    @Getter
    private Integer stockQuantity;

    public SingleStockManager(Integer stock) {
        this.stockQuantity = stock;
    }

    @Override
    public Map<String, Integer> getStock() {
        return Map.of("stock", stockQuantity);
    }

    @Override
    public void decreaseStock(ProductDomain productDomain, CartItemDetails details) {
        Integer itemQuantity = details.getItemQuantity().getQuantity();
        if(itemQuantity>this.stockQuantity) throw new IllegalArgumentException("El stock actual es de: " + stockQuantity + " petición de: " + itemQuantity + " no puede ser procesada");
        this.stockQuantity = stockQuantity -itemQuantity;
    }

    @Override
    public void increaseStock(ProductDomain productDomain, CartItemDetails details) {
        Integer itemQuantity = details.getItemQuantity().getQuantity();
        this.stockQuantity = stockQuantity +itemQuantity;
    }

    @Override
    public Boolean isAvailable() {
        return stockQuantity >0;
    }
}
