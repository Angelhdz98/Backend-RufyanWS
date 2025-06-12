package com.example.PaginaWebRufyan.Components;

import com.example.PaginaWebRufyan.Entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Setter
@Getter
public class SingleStock implements StockManager {
    private Integer stock;
    @Override
    public void decreaseStock(Product product, Map<String, String> additionalFeatures) {
       if(Integer.parseInt(additionalFeatures.get("quantity")) >1){
             setStock(getStock()-Integer.parseInt(additionalFeatures.get("quantity")));
       }else{
           setStock(getStock()-1);
       }
    }

    @Override
    public void increaseStock(Product product, Map<String, String> additionalFeatures) {
        if(Integer.parseInt(additionalFeatures.get("quantity"))>1){
            setStock(getStock()+Integer.parseInt(additionalFeatures.get("quantity")));
        }else{
            setStock(getStock()+1);
        }


    }

    @Override
    public Object getStockInfo() {


        //stockState.put("message", "Available Stock Product");
        return stock;

    }
}
