package com.example.PaginaWebRufyan.adapter.out;

import com.example.PaginaWebRufyan.Products.Entity.Painting;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class PaintingPriceManager implements PriceManager{
    private BigDecimal pricePerCopy;
    private BigDecimal pricePerOriginal;
    // A no args paintingPriceManager will saveUser PaintingPriceManager with min values of Painting



  public PaintingPriceManager(){
        this.pricePerCopy = Painting.minPricePerCopy;
        this.pricePerOriginal = Painting.minPrice;
    }




    /*
    @Override
    public BigDecimal getPriceWithDetails(Map<String, String> details) {
        if(details.containsKey("isOriginalSelected") && Boolean.parseBoolean(details.get("isOriginalSelected"))){
            return pricePerOriginal;
        }
        else return pricePerCopy;
    }

     */





}
