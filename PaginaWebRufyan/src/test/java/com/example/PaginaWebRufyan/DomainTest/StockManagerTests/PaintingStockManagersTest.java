package com.example.PaginaWebRufyan.DomainTest.StockManagerTests;

import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.PaintingDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingItemDetails;
import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingStockManager;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


// Por cada tipo de stock:
// Verificar limites en cada caso:
// crear stock manager
// consultar stock info
// Decrementar el Stock
// Incrementar el stock

/* tipos de Stock:
    done- OriginalStock - para pinturas y otras objetos originales
    BodyClothingStock- para prendas de ropa del tronco superior que manejan tallas desde XS a XL
    singleStock - Stock que solo maneja un producto restockeable y un stock sencillo solo un numero
     que determina la unidades restantes

 */


@ExtendWith(MockitoExtension.class)
public class PaintingStockManagersTest {
    @Test
    @DisplayName("Test para crear un stock manager de obra de arte exitoso")
    public void shouldCreateOriginalStockManager() {
        Integer stockCopies = 0;
        Integer copiesMade = 10;
        Boolean isOriginalAvailable = true;

        Assertions.assertThat(new PaintingStockManager(stockCopies, copiesMade, isOriginalAvailable)).isInstanceOf(PaintingStockManager.class);
    }

    @Test
    @DisplayName("Test para no crear stock managers incorrectos ")
    public void shouldThrowExceptionOriginalStockManager() {
        Integer stockCopies = 11;
        Integer copiesMade = 10;
        Boolean isOriginalAvailable = false;

        Integer invalidCopiesMade = 0;
        Integer moreCopiesThanMade = copiesMade + 1;


        assertThrows
                (IllegalArgumentException.class, () -> {
                    new PaintingStockManager(stockCopies, copiesMade, isOriginalAvailable);
                });
        assertThrows
                (IllegalArgumentException.class, () -> {
                    new PaintingStockManager(moreCopiesThanMade, copiesMade, isOriginalAvailable);
                });
        assertThrows(IllegalArgumentException.class, () -> {
            new PaintingStockManager(stockCopies, invalidCopiesMade, isOriginalAvailable);
        });


    }

    @Test
    @DisplayName("Test para incrementar el PaintingStock (stock de copias) de manera exitosa")
    public void shouldIncrementPaintingStock() {


        Integer quantityItemDetails = 3;
        Integer initialQuantityValue = 8;

        PaintingItemDetails paintingItemDetails = new PaintingItemDetails(quantityItemDetails, false);

        PaintingStockManager paintingStock = new PaintingStockManager(initialQuantityValue, 12, true);

        PaintingDomain paintingDomain = new PaintingDomain(paintingStock);

        paintingStock.increaseStock(paintingDomain, paintingItemDetails);
        Assertions.assertThat(paintingStock.getStockCopies()).isEqualTo(initialQuantityValue + quantityItemDetails);

    }

    @Test
    @DisplayName("Test para arrojar una excepción al intentar incrementar el PaintingStock de una forma invalida (más obras de las que se hicieron) (marcar OriginalAvailable = true cuando ya es true) ")
    public void shouldThrowExceptionIncrementingPaintingStock() {


        Integer quantityItemDetails = 5;
        Integer initialQuantityValue = 8;
        Integer copiesMade =12;

        PaintingItemDetails paintingItemDetails = new PaintingItemDetails(quantityItemDetails, false);

        PaintingStockManager paintingStock = new PaintingStockManager(initialQuantityValue, copiesMade, true);

        PaintingDomain paintingDomain = new PaintingDomain(paintingStock);

        assertThrows(IllegalArgumentException.class,()->{
            paintingStock.increaseStock(paintingDomain, paintingItemDetails);
        });

    }

    @Test
    @DisplayName("Test para cambiar la disponibilidad de pieza original en el PaintingStock de manera exitosa")
    public void shouldMakeAvailableOriginalPaintingStock() {


        Integer quantityItemDetails = 1;
        Integer initialQuantityValue = 8;
        Boolean initialBooleanValue = false;

        Boolean isOriginalSelected = true;

        PaintingItemDetails paintingItemDetails = new PaintingItemDetails(quantityItemDetails, isOriginalSelected);

        PaintingStockManager paintingStock = new PaintingStockManager(initialQuantityValue, 12, initialBooleanValue);

        PaintingDomain paintingDomain = new PaintingDomain(paintingStock);

        paintingStock.increaseStock(paintingDomain, paintingItemDetails);
        Assertions.assertThat(paintingStock.getIsOriginalAvailable()).isEqualTo(true);

    }

    @Test
    @DisplayName("Test para decrementar el PaintingStock de manera exitosa a través del stock")
    public void shouldDecrementPaintingStockByStock() {
        Integer quantityItemDetails = 3;
        Integer initialQuantityValue = 8;

        PaintingItemDetails paintingItemDetails = new PaintingItemDetails(quantityItemDetails, false);

        PaintingStockManager paintingStock = new PaintingStockManager(initialQuantityValue, 12, true);

        PaintingDomain paintingDomain = new PaintingDomain(paintingStock);

        paintingStock.decreaseStock(paintingDomain, paintingItemDetails);

        Assertions.assertThat(paintingStock.getStockCopies()).isEqualTo(initialQuantityValue - quantityItemDetails);

    }
    @Test
    @DisplayName("Test para decrementar el PaintingStock de manera exitosa a través producto (painting)")
    public void shouldDecrementPaintingStockbyProduct() {
        Integer quantityItemDetails = 3;
        Integer initialQuantityValue = 8;

        PaintingItemDetails paintingItemDetails = new PaintingItemDetails(quantityItemDetails, false);

        PaintingStockManager paintingStock = new PaintingStockManager(initialQuantityValue, 12, true);

        PaintingDomain paintingDomain = new PaintingDomain(paintingStock);

        CartItemDomain cartItemDomain = new CartItemDomain(1L, paintingDomain, paintingItemDetails);

        paintingDomain.decreaseStock(cartItemDomain);

        Assertions.assertThat(((PaintingStockManager)paintingDomain.getStockManagerBase()).getStockCopies()).isEqualTo(initialQuantityValue - quantityItemDetails);

    }


    @Test
    @DisplayName("Test para arrojar una excepción al intentar decrementar el PaintingStock de una forma invalida (se decrementan mas obras de las que hay) (marcar OriginalAvailable = false cuando ya es false) ")
    public void shouldThrowExceptionDecrementingPaintingStock() {
        Integer quantityItemDetails = 9;
        Integer initialQuantityValue = 8;

        PaintingItemDetails paintingItemDetails = new PaintingItemDetails(quantityItemDetails, false);

        PaintingStockManager paintingStock = new PaintingStockManager(initialQuantityValue, 12, true);

        PaintingDomain paintingDomain = new PaintingDomain(paintingStock);

        CartItemDomain cartItemDomain = new CartItemDomain(1L, paintingDomain,paintingItemDetails);


        assertThrows(IllegalArgumentException.class, ()->{
            paintingDomain.decreaseStock(cartItemDomain);
            paintingStock.decreaseStock(paintingDomain, paintingItemDetails);
        });


    }



}

















