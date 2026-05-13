

package com.example.PaginaWebRufyan.adapter.out;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.StockEnum;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@ToString(callSuper = true)
@Getter
public class OriginalStockManager extends StockManager {
    private final Integer stockCopies;
    private final Integer copiesMade;
    private final Boolean isOriginalAvailable;
    // agregar una obra al carrito no la apartará
    //private Boolean isInCart = false;


    public OriginalStockManager(Integer stockCopies, Integer copiesMade, Boolean isOriginalAvailable){
        super(0L, StockEnum.PAINTING_STOCK);
        this.stockCopies = stockCopies;
        this.copiesMade = copiesMade;
        this.isOriginalAvailable= isOriginalAvailable;

    }

    public OriginalStockManager(Integer stockCopies, Integer copiesMade){
        super(0L, StockEnum.PAINTING_STOCK);
        this.stockCopies = stockCopies;
        this.copiesMade = copiesMade;
        this.isOriginalAvailable= false;
         }


    protected OriginalStockManager(){
        super(0L, StockEnum.PAINTING_STOCK);
        this.stockCopies = 0;
        this.copiesMade = 0;
        this.isOriginalAvailable= false;
    }

    @Override
    public StockEnum getStockType() {
        return StockEnum.PAINTING_STOCK;
    }

}
