package com.example.PaginaWebRufyan.adapter.out;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@ToString
@Getter
public class SingleStockAdapter extends StockManager  {
    private Integer stock;
    protected SingleStockAdapter(){
        this.stock = 1;
    }
}
