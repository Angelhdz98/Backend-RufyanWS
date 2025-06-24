package com.example.PaginaWebRufyan.Components;

import com.example.PaginaWebRufyan.Entity.Product;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;
@Setter
@Embeddable
@Getter
@AllArgsConstructor
@ToString
public class SinglePriceManager implements PriceManager {
    private final BigDecimal MIN_PRICE= BigDecimal.valueOf(200);
    private BigDecimal price;
    public SinglePriceManager(){
        this.price = MIN_PRICE;
    }
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
