package com.example.PaginaWebRufyan.DomainTest.StockManagerTests;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.example.PaginaWebRufyan.domain.model.BodyClothingDomain;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.PaintingDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
public class BodyClothingStockManagerTest {

    Map<ClothingSizeEnum, Integer> stock = new HashMap<>();

    @BeforeEach
    public void setUp(){


        Arrays.stream(ClothingSizeEnum.values()).forEach((clothingSizeEnum -> {
            stock.put(clothingSizeEnum, BodyClothingStockManager.DEFAULT_STOCK_PER_SIZE);
        }));
    }


    @Test
    @DisplayName("Test para crear BodyClothingStockBodyClothing exitoso")
    public void shouldCreateBodyClothingStockManager() {


        Assertions.assertThat( new BodyClothingStockManager(stock) ).isInstanceOf(BodyClothingStockManager.class);
    }

    @Test
    @DisplayName("Test para no crear BodyClothingStock incorrectos ")
    public void shouldThrowExceptionBodyClothingStockManager() {

        stock.put(ClothingSizeEnum.M,-1);

        assertThrows
                (IllegalArgumentException.class, () -> {
                    new BodyClothingStockManager(stock);
                });


    }

    @Test
    @DisplayName("Test para incrementar el BodyClothingStock  de manera exitosa")
    public void shouldIncrementBodyClothingStock() {


     Integer valueToIncrement = 3;
        ClothingSizeEnum mSize = ClothingSizeEnum.M;
        ClothingItemDetails clothingItemDetails = new ClothingItemDetails(valueToIncrement, ClothingColorEnum.BLACK, mSize);


      BodyClothingStockManager bodyClothingStockManager = new BodyClothingStockManager(stock);

        BodyClothingDomain bodyClothingDomain = new BodyClothingDomain(bodyClothingStockManager);

        bodyClothingStockManager.increaseStock(bodyClothingDomain, clothingItemDetails);
        Assertions.assertThat(bodyClothingStockManager.getStock().get(mSize)).isEqualTo(BodyClothingStockManager.DEFAULT_STOCK_PER_SIZE+valueToIncrement);

    }




    @Test
    @DisplayName("Test para decrementar el BodyClothingStock de manera exitosa a través del stock")
    public void shouldDecrementBodyClothingStockByStock() {
        Integer valueToDecrement = 3;
        ClothingSizeEnum mSize = ClothingSizeEnum.M;
        ClothingItemDetails clothingItemDetails = new ClothingItemDetails(valueToDecrement, ClothingColorEnum.BLACK, mSize);


        BodyClothingStockManager bodyClothingStockManager = new BodyClothingStockManager(stock);

        BodyClothingDomain bodyClothingDomain = new BodyClothingDomain(bodyClothingStockManager);

        bodyClothingStockManager.decreaseStock(bodyClothingDomain, clothingItemDetails);
        Assertions.assertThat(bodyClothingStockManager.getStock().get(mSize)).isEqualTo(BodyClothingStockManager.DEFAULT_STOCK_PER_SIZE-valueToDecrement);


    }



    @Test
    @DisplayName("Test para arrojar una excepción al intentar decrementar el BodyClothingStock de una forma invalida (se decrementan más piezas de las que hay) ")
    public void shouldThrowExceptionDecrementingBodyClothingStock() {
        Integer valueToDecrement = BodyClothingStockManager.DEFAULT_STOCK_PER_SIZE+1;
        ClothingSizeEnum mSize = ClothingSizeEnum.M;
        ClothingItemDetails clothingItemDetails = new ClothingItemDetails(valueToDecrement, ClothingColorEnum.BLACK, mSize);

        BodyClothingStockManager bodyClothingStockManager = new BodyClothingStockManager(stock);

        BodyClothingDomain bodyClothingDomain = new BodyClothingDomain(bodyClothingStockManager);



        assertThrows(IllegalArgumentException.class, ()->{
                bodyClothingStockManager.decreaseStock(bodyClothingDomain, clothingItemDetails);
        });

    }


}
