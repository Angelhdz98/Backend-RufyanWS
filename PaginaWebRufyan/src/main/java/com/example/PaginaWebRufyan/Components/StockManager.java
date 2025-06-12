package com.example.PaginaWebRufyan.Components;

import com.example.PaginaWebRufyan.Entity.Product;

import java.util.Map;

public interface StockManager {

    void decreaseStock(Product product, Map<String,String> additionalFeatures);

    void increaseStock(Product product, Map<String,String> additionalFeatures);

    Object getStockInfo();


}
