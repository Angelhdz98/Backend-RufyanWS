package com.example.PaginaWebRufyan.adapter.out;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.StockEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@ToString
@Getter
public class SingleStockAdapter extends StockManager  {
    private Integer stock;
    protected SingleStockAdapter(){
        super(0L, StockEnum.SINGLE_STOCK);
        this.stock = 1;
    }

    @Override
    public StockEnum getStockType() {
        return StockEnum.SINGLE_STOCK;
    }
}
