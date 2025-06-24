package com.example.PaginaWebRufyan.Components;

import com.example.PaginaWebRufyan.Entity.Product;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;
@Setter
public class SinglePriceManager implements PriceManager {

    private BigDecimal price;
    @Override
    public Object getPriceData() {
        //Map<String, Object> priceResponse = new HashMap<>();
        //priceResponse.put("price",price);
        //priceResponse.put("message","price: "+ price);

        return price;
    }

    @Override
    public void setPriceData(Product product, Map<String, String> additionalFeatures) {
       if(additionalFeatures.containsKey("price")){
           setPrice(BigDecimal.valueOf(Long.parseLong(additionalFeatures.get("price"))));
       }
    }

    @Override
    public BigDecimal getPriceWithDetails(Map<String, String> details) {
        return price;
    }
}
