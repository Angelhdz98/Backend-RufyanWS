package com.example.PaginaWebRufyan.DomainTest.PriceManagerTest;

import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingPriceManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class PaintingPriceManagerTest {

    @DisplayName("Test para crear un price manager de manera correcta")
    @Test
    public void shouldCreatePriceManager(){
        BigDecimal pricePerCopy = new BigDecimal("800");
        BigDecimal pricePerOriginal = new BigDecimal("2000");
        PaintingPriceManager paintingPriceManager = new PaintingPriceManager(pricePerCopy,pricePerOriginal);

        Assertions.assertThat(paintingPriceManager).isInstanceOf(PaintingPriceManager.class);
        Assertions.assertThat(paintingPriceManager.getPricePerCopy()).isEqualTo(pricePerCopy);
        Assertions.assertThat(paintingPriceManager.getPricePerOriginal()).isEqualTo(pricePerOriginal);

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
    public void shouldThrowIllegalArgumentaException(){

        BigDecimal pricePerCopy = PaintingPriceManager.MIN_COPY_PRICE;

        BigDecimal pricePerOriginal = PaintingPriceManager.MIN_ORIGINAL_PRICE.subtract(BigDecimal.ONE);

        assertThrows(IllegalArgumentException.class,()->{
            PaintingPriceManager paintingPriceManager = new PaintingPriceManager(pricePerCopy,pricePerOriginal);
        });


    }



}
