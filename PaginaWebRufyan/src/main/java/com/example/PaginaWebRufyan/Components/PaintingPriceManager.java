package com.example.PaginaWebRufyan.Components;

import com.example.PaginaWebRufyan.Entity.Painting;
import com.example.PaginaWebRufyan.Entity.Product;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Embeddable
@Setter
@Getter
@AllArgsConstructor
@ToString
public class PaintingPriceManager extends PriceManagerBase{
    private BigDecimal pricePerCopy;
    private BigDecimal pricePerOriginal;
    // A no args paintingPriceManager will create PaintingPriceManager with min values of Painting

    public PaintingPriceManager(){
        this.pricePerCopy = Painting.minPricePerCopy;
        this.pricePerOriginal = Painting.minPrice;
    }

    @Override
    public Object getPriceData() {
        Map<String, Object> prices = new HashMap<>();
        prices.put("pricePerCopy", pricePerCopy);
        prices.put("pricePerOriginal", pricePerOriginal);
        //prices.put("message", "Price p/Original: "+ pricePerOriginal +"/n"+"Price p/Copy: " + pricePerCopy);
        return prices;
    }

    @Override
    public void setPriceData(Product product, Map<String,String> additionalFeatures ) {
        if(additionalFeatures.containsKey("pricePerCopy")){
            setPricePerCopy(BigDecimal.valueOf(Long.parseLong(additionalFeatures.get("pricePerCopy"))) );
        }
        if (additionalFeatures.containsKey("pricePerOriginal")){
            setPricePerOriginal(BigDecimal.valueOf(Long.parseLong(additionalFeatures.get("pricePerOriginal"))));
        }


    }

    @Override
    public Map<String, BigDecimal> getPriceMap() {
        Map<String, BigDecimal> mapResponse = new HashMap<>();
        mapResponse.put("pricePerCopy",pricePerCopy);
        mapResponse.put("pricePerOriginal", pricePerOriginal);
        return mapResponse;
    }


    @Override
    public BigDecimal getPriceWithDetails(Map<String, String> details) {
        if(details.containsKey("isOriginalSelected") && Boolean.parseBoolean(details.get("isOriginalSelected"))){
            return pricePerOriginal;
        }
        else return pricePerCopy;
    }
}
