package com.example.PaginaWebRufyan.adapter.out;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class SingleStockAdapter implements StockManager  {
    private Integer stock;
    /*@Override
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

    @Override
    public Map<String, Object> getStockMap() {
         Map<String, Object> response = new HashMap<>();
         response.put("stock",stock);
         return response;

    }

     */

}
