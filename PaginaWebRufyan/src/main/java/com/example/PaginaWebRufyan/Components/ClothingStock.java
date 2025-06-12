package com.example.PaginaWebRufyan.Components;

import com.example.PaginaWebRufyan.Entity.ClothingSizeEnum;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Exceptions.InconsitentDataException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
@Embeddable
@AllArgsConstructor
@ToString
public class ClothingStock implements StockManager {
    @ElementCollection
    @CollectionTable(name = "clothing_size_stock",
                     joinColumns = @JoinColumn(name = "clothing_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "size")
    @Column(name = "quantity")
    private Map<ClothingSizeEnum, Integer> stockPerSize= new HashMap<>();




    @Override
    public void decreaseStock(Product product, Map<String, String> additionalFeatures) {
    int quantity = Integer.parseInt( additionalFeatures.get("quantity"));
    ClothingSizeEnum size = ClothingSizeEnum.valueOf(additionalFeatures.get("size")) ;
    int piecesPerSize = stockPerSize.get(size);
    if(piecesPerSize-quantity<0){
        throw new InconsitentDataException("are you trying to decrease: "+quantity+" units but there are only: "+ piecesPerSize );
    }else{
        stockPerSize.replace(size,piecesPerSize-quantity);
    }



    }

    @Override
    public void increaseStock(Product product, Map<String, String> additionalFeatures) {
        int quantity = Integer.parseInt(additionalFeatures.get("quantity"));
        ClothingSizeEnum size = ClothingSizeEnum.valueOf(additionalFeatures.get("size"));
        int piecesPerSize = stockPerSize.get(size);
        stockPerSize.replace(size,piecesPerSize+quantity);
    }

    @Override
    public Object getStockInfo() {
        return null;
    }


}
