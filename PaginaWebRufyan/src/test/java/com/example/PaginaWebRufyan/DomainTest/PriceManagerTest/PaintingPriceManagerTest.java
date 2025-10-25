package com.example.PaginaWebRufyan.DomainTest.PriceManagerTest;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class PaintingPriceManagerTest {


    PaintingItemDetails originalSelected = new PaintingItemDetails(1,true);
    Integer copiesQuantity = 2;
    PaintingItemDetails copySelected = new PaintingItemDetails(copiesQuantity,false);


    Integer quantityClothes = 3;
    ClothingItemDetails clothingItemDetails = new ClothingItemDetails(quantityClothes, ClothingColorEnum.BLACK, ClothingSizeEnum.M);

    BigDecimal clothePrice = new BigDecimal("700");
    BigDecimal priceGlobalPerCopy = new BigDecimal("800");
    BigDecimal priceGlobalPerOriginal = new BigDecimal("2000");


    @Test
    @DisplayName("Test para obtener el precio total de una obra con sus detalles")
    public void shouldGetTotalPaintingPriceWithDetails(){

        PaintingPriceManager paintingPriceManager = new PaintingPriceManager(priceGlobalPerCopy,priceGlobalPerOriginal);

assertThat(paintingPriceManager.getPriceWithDetails(originalSelected)).isEqualTo(priceGlobalPerCopy);
        assertThat(paintingPriceManager.getPriceWithDetails(copySelected)).isEqualTo(priceGlobalPerCopy.multiply(new BigDecimal(copiesQuantity)));

    }



    @DisplayName("Test para crear un price manager de manera correcta")
    @Test
    public void shouldCreatePriceManager(){
        BigDecimal pricePerCopy = new BigDecimal("800");
        BigDecimal pricePerOriginal = new BigDecimal("2000");
        PaintingPriceManager paintingPriceManager = new PaintingPriceManager(pricePerCopy,pricePerOriginal);

        assertThat(paintingPriceManager).isInstanceOf(PaintingPriceManager.class);
        assertThat(paintingPriceManager.getPricePerCopy()).isEqualTo(pricePerCopy);
        assertThat(paintingPriceManager.getPricePerOriginal()).isEqualTo(pricePerOriginal);

    }


    @DisplayName("Test para arrojar una excepción cuando el precio de una obra original es menor al de una copia  ")
    @Test
    public void shouldThrowIllegalArgumentaExceptionOriginalPriceLowerThanCopyPrice(){
        BigDecimal pricePerCopy = new BigDecimal("800");
        BigDecimal pricePerOriginal = new BigDecimal("700");

        assertThrows(IllegalArgumentException.class,()->{
            PaintingPriceManager paintingPriceManager = new PaintingPriceManager(pricePerCopy,pricePerOriginal);
        });


    }

    @DisplayName("Test para arrojar una excepción cuando el precio de la copia de una obra es menor al de el minimo establecido  ")
    @Test
    public void shouldThrowIllegalArgumentaExceptionCopyPriceLowerThanLimit(){

        BigDecimal pricePerCopy = PaintingPriceManager.MIN_COPY_PRICE.subtract(BigDecimal.ONE);
        BigDecimal pricePerOriginal = new BigDecimal("700");

        assertThrows(IllegalArgumentException.class,()->{
            PaintingPriceManager paintingPriceManager = new PaintingPriceManager(pricePerCopy,pricePerOriginal);
        });


    }
    @DisplayName("Test para arrojar una excepción cuando el precio de una obra original es menor al de el minimo establecido  ")
    @Test
    public void shouldThrowIllegalArgumentaExceptionOriginalPriceLowerThanEstablished(){

        BigDecimal pricePerCopy = PaintingPriceManager.MIN_COPY_PRICE;

        BigDecimal pricePerOriginal = PaintingPriceManager.MIN_ORIGINAL_PRICE.subtract(BigDecimal.ONE);

        assertThrows(IllegalArgumentException.class,()->{
            PaintingPriceManager paintingPriceManager = new PaintingPriceManager(pricePerCopy,pricePerOriginal);
        });


    }



}
