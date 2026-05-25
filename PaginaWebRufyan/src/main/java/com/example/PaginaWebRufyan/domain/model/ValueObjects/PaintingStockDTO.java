package com.example.PaginaWebRufyan.domain.model.ValueObjects;


import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class PaintingStockDTO implements ProductStockDTO {

   private final Boolean isOriginalAvailable;
   private final Integer stockCopies;
   private final Integer copiesMade;
   private final StockEnum stockType;

    public PaintingStockDTO(Boolean isOriginalAvailable, Integer stockCopies, Integer copiesMade) {
        this.isOriginalAvailable = isOriginalAvailable;
        this.stockCopies = stockCopies;
        this.copiesMade = copiesMade;
        this.stockType = StockEnum.PAINTING_STOCK;
    }

    public PaintingStockDTO(Boolean isOriginalAvailable, Integer stockCopies, Integer copiesMade, StockEnum stockType) {
        this.isOriginalAvailable = isOriginalAvailable;
        this.stockCopies = stockCopies;
        this.copiesMade = copiesMade;
        this.stockType = StockEnum.PAINTING_STOCK;
    }

    public PaintingStockDTO(){
        this.isOriginalAvailable= PaintingStockManager.DEFAULT_ORIGINAL_AVAILABLE;
        this.stockCopies = PaintingStockManager.DEFAULT_STOCK_COPIES;
        this.copiesMade = PaintingStockManager.DEFAULT_COPIES_MADE;
        this.stockType = StockEnum.PAINTING_STOCK;
    }

}


