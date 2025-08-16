package com.example.PaginaWebRufyan.adapter.out;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
@Embeddable
@ToString
public class ClothingStockAdapter implements StockManager {
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

/*@Override
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


     */

}
