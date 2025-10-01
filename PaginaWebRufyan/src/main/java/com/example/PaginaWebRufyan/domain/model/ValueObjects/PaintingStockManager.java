package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.domain.model.PaintingDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class PaintingStockManager extends StockManagerBase {
    public static final Integer DEFAULT_STOCK_COPIES = 0;
    public static final Integer DEFAULT_COPIES_MADE = 0;
    public static final Boolean DEFAULT_ORIGINAL_AVAILABLE = true;

    @Setter
    private Integer stockCopies;
    private final Integer copiesMade;
    private Boolean isOriginalAvailable;

    public PaintingStockManager() {
        this.stockCopies = DEFAULT_STOCK_COPIES;
        this.copiesMade = DEFAULT_COPIES_MADE;
        this.isOriginalAvailable = DEFAULT_ORIGINAL_AVAILABLE;

    }

    public PaintingStockManager(Integer stockCopies, Integer copiesMade, Boolean isOriginalAvailable) {
        if(stockCopies>copiesMade) throw new IllegalArgumentException("No puede haber más copias en stock de las que se hicieron");
        this.stockCopies = stockCopies;
        this.copiesMade = copiesMade;
        this.isOriginalAvailable = isOriginalAvailable;
     }

    @Override
    public Map<String, Integer> getStock() {
        return Map.of("stockCopies", stockCopies, "copiesMade", copiesMade, "isOriginalAvailable", isOriginalAvailable ? 1 : 0);
    }

    @Override
    public void decreaseStock(ProductDomain productDomain, CartItemDetails details) {
        PaintingItemDetails paintingDetails = (PaintingItemDetails) details;
        PaintingDomain painting = (PaintingDomain)
                productDomain;
        if (paintingDetails.getIsOriginalSelected().getIsOriginalSelected()) {
            if (!isOriginalAvailable) {
                throw new IllegalArgumentException("La pintura con id: " + productDomain.getId() + " no esta disponible original");
            }
            this.isOriginalAvailable = false;
        } else {
            Integer quantity = paintingDetails.getItemQuantity().getQuantity();
            if (quantity > stockCopies || quantity == 0) {
                throw new IllegalArgumentException("El stock actual es de: " + stockCopies + " petición de: " + quantity + " no puede ser procesada");
            }

            this.stockCopies = stockCopies - quantity;

        }


    }

    @Override
    public void increaseStock(ProductDomain productDomain, CartItemDetails details) {
        PaintingItemDetails paintingDetails = (PaintingItemDetails) details;
        PaintingDomain painting = (PaintingDomain)
                productDomain;
        if (paintingDetails.getIsOriginalSelected().getIsOriginalSelected()) {
            if (isOriginalAvailable) {
                throw new IllegalArgumentException("La pintura original con id: " + productDomain.getId() + " ya se encuentra  disponible");
            }
            this.isOriginalAvailable = true;
        } else {
            Integer quantity = paintingDetails.getItemQuantity().getQuantity();
            if ((quantity + stockCopies) > copiesMade || quantity == 0) {
                throw new IllegalArgumentException("Petición invalida, el numero de copias hecas es de: " + copiesMade + " el restock excedería la cantidad de copias hechas: " + quantity + stockCopies);
            }

            this.stockCopies = stockCopies + quantity;

        }
    }

    @Override
    public Boolean isAvailable() {
        return (stockCopies > 0 || isOriginalAvailable);

    }
}
