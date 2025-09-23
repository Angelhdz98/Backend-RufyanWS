package com.example.PaginaWebRufyan.DomainTest;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.example.PaginaWebRufyan.adapter.out.SinglePriceManager;
import com.example.PaginaWebRufyan.domain.model.BodyClothingDomain;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.PaintingDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartItemTest {
// CartItemTest incluirá los test los cartItemDetails



/*CarItemDetails subclasses:
  Quantity - integer

  StockableProductItemDetails

  PaintingItemDetails
  Boolean isOriginalSelected

    BodyClothingItemDetails
     ClothingSizeEnum clothingSizeEnum;
     ClothingColorEnum color;
 */

Integer correctStockValue = 2;
Integer incorrectStockValue = -1;


PaintingDomain mockPainting = Mockito.mock(PaintingDomain.class);

BodyClothingDomain mockBodyClothing = Mockito.mock(BodyClothingDomain.class);


PaintingItemDetails paintingItemOriginalSelected = new PaintingItemDetails(1,true);
PaintingItemDetails paintingItemDetailsThreeCopies = new PaintingItemDetails(3,false);

ClothingItemDetails clothingItemDetails = new ClothingItemDetails();



PaintingPriceManager paintingPriceManager = new PaintingPriceManager();

SinglePriceManager singlePriceManager = new SinglePriceManager();

Long id = 1L;


@BeforeEach
public void setUp(){

}

    @Test
    @DisplayName("Test para calcular el monto total de un CartItem de una pintura original")
    public void shouldCalculateItemTotalAmountOriginalPainting(){


    when(mockPainting.getPriceManagerBase()).thenReturn(paintingPriceManager);
    when(mockPainting.getId()).thenReturn(id);
    when(mockPainting.getName()).thenReturn("Mona Lisa");

        CartItemDomain cartItemDomain = new CartItemDomain(id, mockPainting, paintingItemOriginalSelected);

        assertThat(cartItemDomain.getItemTotalAmount()).isEqualTo(PaintingPriceManager.MIN_ORIGINAL_PRICE);
    }
    @Test
    @DisplayName("Test para calcular el monto total de un CartItem 3 copias de una pintura")
    public void shouldCalculateItemTotalAmountCopiesPainting(){


        when(mockPainting.getPriceManagerBase()).thenReturn(paintingPriceManager);
        when(mockPainting.getId()).thenReturn(id);
        when(mockPainting.getName()).thenReturn("Mona Lisa");

        CartItemDomain cartItemDomain = new CartItemDomain(id, mockPainting, paintingItemDetailsThreeCopies);
        assertThat(cartItemDomain.getProduct()).isEqualTo(mockPainting);
        BigDecimal itemTotalAmount = cartItemDomain.getItemTotalAmount();
        assertThat(itemTotalAmount).isEqualTo(PaintingPriceManager.MIN_COPY_PRICE.multiply(BigDecimal.valueOf(3)));
    }
    @Test
    @DisplayName("Test para calcular el monto total de un CartItem de una pintura original")
    public void shouldCalculateItemTotalAmountSinglePrice(){


        when(mockBodyClothing.getPriceManagerBase()).thenReturn(singlePriceManager);
        when(mockBodyClothing.getId()).thenReturn(id);
        when(mockBodyClothing.getName()).thenReturn("Mona Lisa");

        CartItemDomain cartItemDomain = new CartItemDomain(id, mockBodyClothing, clothingItemDetails);


        assertThat(cartItemDomain.getItemTotalAmount()).isEqualTo(SinglePriceManager.MIN_PRICE.multiply(BigDecimal.valueOf(clothingItemDetails.getItemQuantity().getQuantity())));

    }

    @Test
    @DisplayName("Test para crear un CartItemDetails exitosamente")
    public void shouldCreateCartItemSuccessfully() {

        StockableProductItemDetails stockableProductItemDetails = new StockableProductItemDetails(correctStockValue);
        assertThat(stockableProductItemDetails.getItemQuantity().getQuantity()).isGreaterThan(0);

        PaintingItemDetails paintingItemDetails = new PaintingItemDetails(1,true);
        assertThat(paintingItemDetails.getItemQuantity().getQuantity()).isEqualTo(1);
        assertThat(paintingItemDetails.getIsOriginalSelected().getIsOriginalSelected()).isTrue();

        PaintingItemDetails paintingItemDetails1Copy = new PaintingItemDetails(correctStockValue,false);
        assertThat(paintingItemDetails1Copy.getItemQuantity().getQuantity()).isEqualTo(correctStockValue);
        assertThat(paintingItemDetails1Copy.getIsOriginalSelected().getIsOriginalSelected()).isFalse();

        ClothingItemDetails clothingItemDetails = new ClothingItemDetails(correctStockValue, ClothingColorEnum.BLACK, ClothingSizeEnum.M);

    }

    @Test
    @DisplayName("Test para arrojar una excepción en caso de que se quiera crear  un cartItemDetails con valores inválidos")
    public void shouldThrowErrorCreatingCartItem(){

        assertThrows(IllegalArgumentException.class, ()->{
            StockableProductItemDetails stockableProductItemDetails = new StockableProductItemDetails(incorrectStockValue);
        });

        assertThrows(IllegalArgumentException.class, ()->{
            PaintingItemDetails paintingItemDetails = new PaintingItemDetails(incorrectStockValue,false);
        });
        assertThrows(IllegalArgumentException.class, ()->{
            //solo hay una original
            PaintingItemDetails paintingItemDetails = new PaintingItemDetails(correctStockValue,true);
        });

        assertThrows(IllegalArgumentException.class, ()->{
            ClothingItemDetails clothingItemDetails = new ClothingItemDetails(incorrectStockValue, ClothingColorEnum.BLACK, ClothingSizeEnum.M);
        });

    }



}
