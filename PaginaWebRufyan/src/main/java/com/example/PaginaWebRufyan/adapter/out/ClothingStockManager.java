package com.example.PaginaWebRufyan.adapter.out;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.StockEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
@Entity
@ToString(callSuper = true)
@Getter
public class ClothingStockManager extends StockManager {
    @ElementCollection
    @CollectionTable(name = "clothing_size_stock",
                     joinColumns = @JoinColumn(name = "clothing_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "size")
    @Column(name = "quantity")
    private Map<ClothingSizeEnum, Integer> stockPerSize= new HashMap<>();

    // en caso de no asignar stock se inicializa con cero
    public ClothingStockManager(){
        super(0L, StockEnum.CLOTHING_STOCK);
        Map<ClothingSizeEnum, Integer> stockMap = new LinkedHashMap<>();
        Arrays.stream(ClothingSizeEnum.values()).forEach((ClothingSizeEnum clothingSizeEnum)-> stockMap.put(clothingSizeEnum,0));
        this.stockPerSize = stockMap;

    }

    @Override
    public StockEnum getStockType() {
        return StockEnum.CLOTHING_STOCK;
    }

    public ClothingStockManager(Map<ClothingSizeEnum, Integer> stockPerSize) {
        super(0L,StockEnum.CLOTHING_STOCK);
        this.stockPerSize = stockPerSize;
    }



}
