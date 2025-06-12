package com.example.PaginaWebRufyan.Components;

import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Exceptions.NoStockException;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OriginalStock extends StockManagerBase {
    private Integer stockCopies;
    private Boolean isOriginalAvailable;
    private Boolean isInCart = false;


    public OriginalStock(Integer stockCopies, Boolean isOriginalAvailable){
        this.stockCopies = stockCopies;
        this.isOriginalAvailable= isOriginalAvailable;
    }

    @Override
    public void decreaseStock(Product product, Map<String, String> additionalFeatures) {
        if (Boolean.valueOf(additionalFeatures.get("isOriginalSelected"))   && isOriginalAvailable && !isInCart ){
            setIsOriginalAvailable(false);
        }
        else {
            int quantity = Integer.parseInt(additionalFeatures.get("quantity"));
            if(stockCopies> quantity){
            setStockCopies(getStockCopies()-quantity);
            } else throw  new NoStockException("User ask for: "+ quantity+ " but there are just: "+ stockCopies);

        }
    }

    @Override
    public void increaseStock(Product product, Map<String, String> additionalFeatures) {
        if (Boolean.valueOf(additionalFeatures.get("isOriginalSelected"))   ){
            setIsOriginalAvailable(true);
        }
        else {
            setStockCopies(getStockCopies()+Integer.parseInt(additionalFeatures.get("quantity")));
        }
    }

    @Override
    public Object getStockInfo() {
        Map<String, Object> infoResponse = new HashMap<>();
        infoResponse.put("isOriginalAvailable",isOriginalAvailable);
        infoResponse.put("isInCart",isInCart);
        infoResponse.put("stockCopies",stockCopies);
       //String message =  String.valueOf("is original available: "+ String.valueOf(isOriginalAvailable) + "/n" +"Is in cart: "+ String.valueOf(isInCart)+"/n"+ "Available copies: " + stockCopies  );
       //infoResponse.put("ResponseMessage", message);

       return infoResponse;

    }
}
