package com.example.PaginaWebRufyan.serviceTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @Test
    @DisplayName("Test para agregar un cart item de manera exitosa")
    public void shouldAddCarItemToShoppingCartCorrectly(){

    }
    @Test
    @DisplayName("Test para evitar agregar un cartItem de un producto que no existe")
    public void shouldThrowAnExceptionAddingACartItemFromProductThatDoesNotExistToShoppingCart(){

    }
    @Test
    @DisplayName("Test para evitar agregar un cantidad producto que no hay en stock ")
    public void shouldThrowAnExceptionAddingMorePiecesThanAvailableStock(){
     //Una prueba por cada tipo de producto.

    }
    @Test
    @DisplayName("Test para evitar agregar un carrito que no tiene stock disponible ")
    public void shouldThrowAnExceptionAddingACartItemFromAProductWithNoStock(){

    }

    @Test
    @DisplayName("Test para remover item del carrito de manera exitosa")
    public void shouldRemoveCartItemFromShoppingCartSucessfully(){

    }
    @Test
    @DisplayName("Test para evitar remover un item que no está en el carrito")
    public void shouldThrowAnExceptionDeletingACartItemThatIsNotInTheShoppingCart(){

    }

}
