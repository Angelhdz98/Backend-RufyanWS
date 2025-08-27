package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.domain.model.PaintingDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor

public class PaintingStockManager extends StockManagerBase {


    @Setter
    private Integer stockCopies;
    private final Integer copiesMade;
    private Boolean isOriginalAvailable;




    @Override
    public Map<String, Integer> getStock() {
        return Map.of("stockCopies",stockCopies,"copiesMade", copiesMade,"isOriginalAvailable", isOriginalAvailable?1:0);
     }

    @Override
    public void decreaseStock(ProductDomain productDomain,CartItemDetails details) {
        PaintingItemDetails paintingDetails = (PaintingItemDetails) details;
        PaintingDomain painting = (PaintingDomain)
productDomain;
        if(paintingDetails.getIsOriginalSelected().getIsOriginalSelected()) {
            if(!isOriginalAvailable){throw new IllegalArgumentException("La pintura con id: " + productDomain.getId()+" no esta disponible original");}
            this.isOriginalAvailable = false;
        }else {
            Integer quantity =paintingDetails.getItemQuantity().getQuantity();
            if(quantity> stockCopies|| quantity==0){
                throw new IllegalArgumentException("El stock actual es de: "+ stockCopies+" petición de: "+ quantity +" no puede ser procesada");
            }

            this.stockCopies=stockCopies-quantity;

        }





    }

    @Override
    public void increaseStock(ProductDomain productDomain, CartItemDetails details) {
        PaintingItemDetails paintingDetails = (PaintingItemDetails) details;
        PaintingDomain painting = (PaintingDomain)
                productDomain;
        if(paintingDetails.getIsOriginalSelected().getIsOriginalSelected()) {
            if(isOriginalAvailable){throw new IllegalArgumentException("La pintura original con id: " + productDomain.getId()+ " ya se encuentra  disponible");
            }
            this.isOriginalAvailable = false;
        }else {
            Integer quantity =paintingDetails.getItemQuantity().getQuantity();
            if(quantity + stockCopies> copiesMade || quantity==0){
                throw new IllegalArgumentException("Petición invalida, el numero de copias hecas es de: "+copiesMade +" el restock excedería la cantidad de copias hechas: "+ quantity+stockCopies);
            }

            this.stockCopies=stockCopies-quantity;

        }
    }

    @Override
    public Boolean isAvailable() {
        return (stockCopies>0 || isOriginalAvailable );

    }
}
