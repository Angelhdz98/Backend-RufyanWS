

package com.example.PaginaWebRufyan.adapter.out;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@ToString
@Getter
public class OriginalStockAdapter extends StockManager {
    private Integer stockCopies;
    private final Integer copiesMade;
    private Boolean isOriginalAvailable;
    // agregar una obra al carrito no la apartará
    //private Boolean isInCart = false;


    public OriginalStockAdapter(Integer stockCopies, Integer copiesMade, Boolean isOriginalAvailable){
        this.stockCopies = stockCopies;
        this.copiesMade = copiesMade;
        this.isOriginalAvailable= isOriginalAvailable;

    }

    public OriginalStockAdapter(Integer stockCopies, Integer copiesMade){
        this.stockCopies = stockCopies;
        this.copiesMade = copiesMade;
        this.isOriginalAvailable= false;
         }


    protected OriginalStockAdapter(){
        this.stockCopies = 0;
        this.copiesMade = 0;
        this.isOriginalAvailable= false;
    }

}
