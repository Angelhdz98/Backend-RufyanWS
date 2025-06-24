package com.example.PaginaWebRufyan.Components;

import com.example.PaginaWebRufyan.Entity.Product;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@MappedSuperclass
public abstract class StockManagerBase {
    public abstract void decreaseStock(Product product, Map<String, String> details);
    public abstract void increaseStock(Product product, Map<String, String> details);
    public abstract Object getStockInfo();
    public abstract Map<String,Object>getStockMap();
}
