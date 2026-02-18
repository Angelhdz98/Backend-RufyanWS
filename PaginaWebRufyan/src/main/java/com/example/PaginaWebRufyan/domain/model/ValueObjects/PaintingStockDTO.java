package com.example.PaginaWebRufyan.domain.model.ValueObjects;


import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class PaintingStockDTO implements ProductStockDTO {

   private Boolean isOriginalAvailable;
   private Integer availableCopies;
   private Integer copiesMade;
   private StockEnum stockType;

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
}


