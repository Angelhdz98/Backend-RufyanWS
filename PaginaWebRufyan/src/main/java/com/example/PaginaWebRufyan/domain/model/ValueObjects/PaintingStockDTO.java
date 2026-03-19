package com.example.PaginaWebRufyan.domain.model.ValueObjects;


import lombok.Getter;


@Getter
public class PaintingStockDTO implements ProductStockDTO {

   private final Boolean isOriginalAvailable;
   private final Integer availableCopies;
   private final Integer copiesMade;
   private final StockEnum stockType;

    public PaintingStockDTO(Boolean isOriginalAvailable, Integer availableCopies, Integer copiesMade) {
        this.isOriginalAvailable = isOriginalAvailable;
        this.availableCopies = availableCopies;
        this.copiesMade = copiesMade;
        this.stockType = StockEnum.PAINTING;
    }

    public PaintingStockDTO(Boolean isOriginalAvailable, Integer availableCopies, Integer copiesMade, StockEnum stockType) {
        this.isOriginalAvailable = isOriginalAvailable;
        this.availableCopies = availableCopies;
        this.copiesMade = copiesMade;
        this.stockType = StockEnum.PAINTING;
    }

    public PaintingStockDTO(){
        this.isOriginalAvailable= PaintingStockManager.DEFAULT_ORIGINAL_AVAILABLE;
        this.availableCopies = PaintingStockManager.DEFAULT_STOCK_COPIES;
        this.copiesMade = PaintingStockManager.DEFAULT_COPIES_MADE;
        this.stockType = StockEnum.PAINTING;
    }

}


