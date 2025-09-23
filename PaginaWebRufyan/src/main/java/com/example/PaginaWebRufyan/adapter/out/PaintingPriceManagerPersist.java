package com.example.PaginaWebRufyan.adapter.out;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingPriceManager;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@AllArgsConstructor
public class PaintingPriceManagerPersist extends PriceManagerPersist {

    private Long id;
    private BigDecimal pricePerCopy;
    private BigDecimal pricePerOriginal;
    // A no args paintingPriceManager will saveUser PaintingPriceManager with min values of Painting


protected PaintingPriceManagerPersist(){
        this.pricePerCopy = PaintingPriceManager.MIN_COPY_PRICE;
        this.pricePerOriginal = PaintingPriceManager.MIN_ORIGINAL_PRICE;
    }










}
