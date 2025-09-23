package com.example.PaginaWebRufyan.adapter.out;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
@Entity
@ToString
@Getter
public class ClothingStockAdapter extends StockManager {
    @ElementCollection
    @CollectionTable(name = "clothing_size_stock",
                     joinColumns = @JoinColumn(name = "clothing_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "size")
    @Column(name = "quantity")
    private Map<ClothingSizeEnum, Integer> stockPerSize= new HashMap<>();

    // en caso de no asignar stock se inicializa con cero
    public ClothingStockAdapter(){
        Map<ClothingSizeEnum, Integer> stockMap = new LinkedHashMap<>();
                Arrays.stream(ClothingSizeEnum.values()).forEach((ClothingSizeEnum clothingSizeEnum)-> stockMap.put(clothingSizeEnum,0));
                this.stockPerSize = stockMap;

    }
    public ClothingStockAdapter(Map<ClothingSizeEnum, Integer> stockPerSize) {
        this.stockPerSize = stockPerSize;
    }



}
