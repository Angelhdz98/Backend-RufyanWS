package com.example.PaginaWebRufyan.serviceTest;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Service.ShoppingCartServiceAdapter.AddCartItemService;
import com.example.PaginaWebRufyan.Service.ShoppingCartServiceAdapter.DeleteCartItemService;
import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.CartItemCommand;
import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.DeleteCartItemCommand;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.ShoppingCartRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @Mock
    ShoppingCartRepositoryPort shoppingCartRepositoryPort;
    @Mock
    ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    AddCartItemService addCartItemService;
    @InjectMocks
    DeleteCartItemService deleteCartItemService;

    CartItemCommand mockCartItemCommand;

    @BeforeEach
    public void setUp() {
        mockCartItemCommand = Mockito.mock(CartItemCommand.class);

    }

    @Test
    @DisplayName("Test para agregar un cart item de manera exitosa")
    public void shouldAddCarItemToShoppingCartCorrectly() {
        ProductDomain mockProduct = mock(ProductDomain.class);
        CartItemDomain cartItemMock = mock(CartItemDomain.class);
        ShoppingCartDomain shoppingCartTest = new ShoppingCartDomain(1L,1L,Set.of());
        ShoppingCartDomain updatedShoppingCartTest = new ShoppingCartDomain(1L,1L,Set.of(cartItemMock));


        when(productRepositoryPort.retrieveProductById(any())).thenReturn(mockProduct);

        when(shoppingCartRepositoryPort.updateShoppingCart(any(), any())).thenReturn(updatedShoppingCartTest);
        when(shoppingCartRepositoryPort.retrieveShoppingCart(any())).thenReturn(shoppingCartTest);
        ShoppingCartDomain shoppingCartDomain = addCartItemService.addCartItem(mockCartItemCommand);


        assertThat(shoppingCartDomain).isNotNull();
        verify(shoppingCartRepositoryPort, times(1)).updateShoppingCart(any(), any());
        verify(productRepositoryPort, times(1)).retrieveProductById(any());


    }

    @Test
    @DisplayName("Test para evitar agregar un cartItem de un producto que no existe")
    public void shouldThrowAnExceptionAddingACartItemFromProductThatDoesNotExistToShoppingCart() {
        ShoppingCartDomain mockShoppingCart= mock(ShoppingCartDomain.class);
        when(shoppingCartRepositoryPort.retrieveShoppingCart(any())).thenReturn(mockShoppingCart);
        when(productRepositoryPort.retrieveProductById(any())).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, ()->{
           addCartItemService.addCartItem(mockCartItemCommand);
        });
        verify(shoppingCartRepositoryPort, never()).updateShoppingCart(any(), any());
        verify(productRepositoryPort,times(1)).retrieveProductById(any());


    }
 // already tested on stockManager
  /*  @Test
    @DisplayName("Test para evitar agregar un cantidad producto que no hay en stock ")
    public void shouldThrowAnExceptionAddingMorePiecesThanAvailableStock() {
        //Una prueba por cada tipo de producto.


    @Test
    @DisplayName("Test para evitar agregar un carrito que no tiene stock disponible ")
    public void shouldThrowAnExceptionAddingACartItemFromAProductWithNoStock() {

    }

    }*/


    @Test
    @DisplayName("Test para remover item del carrito de manera exitosa")
    public void shouldRemoveCartItemFromShoppingCartSuccessfully() {

        CartItemDomain mockCartItemDomain = mock(CartItemDomain.class);
        Long idToDelete = 3L;
        Long userId = 13L;
        DeleteCartItemCommand deleteCartItemCommand  = new DeleteCartItemCommand(1L,idToDelete);
        when(mockCartItemDomain.getId()).thenReturn(idToDelete);
        ShoppingCartDomain testShoppingCart = new ShoppingCartDomain(12L, userId, Set.of(mockCartItemDomain));
        ShoppingCartDomain updatedTestShoppingCart = new ShoppingCartDomain(12L, userId, Set.of());
        when(shoppingCartRepositoryPort.retrieveShoppingCart(any())).thenReturn(testShoppingCart);
        when(shoppingCartRepositoryPort.updateShoppingCart(any(),any())).thenReturn(updatedTestShoppingCart);

        ShoppingCartDomain result = deleteCartItemService.deleteCartItem(deleteCartItemCommand);

        assertThat(result.getItems()).isEmpty();
        verify(shoppingCartRepositoryPort, times(1)).updateShoppingCart(any(),any());


    }

    @Test
    @DisplayName("Test para evitar remover un item que no está en el carrito")
    public void shouldThrowAnExceptionDeletingACartItemThatIsNotInTheShoppingCart() {
        Long userId = 13L;
        DeleteCartItemCommand deleteCartItemCommandTest = new DeleteCartItemCommand(userId,1L);
        ShoppingCartDomain testShoppingCart = new ShoppingCartDomain(12L, userId, Set.of());
        when(shoppingCartRepositoryPort.retrieveShoppingCart(any())).thenReturn(testShoppingCart);
        assertThrows(ResourceNotFoundException.class, ()->{

            deleteCartItemService.deleteCartItem(deleteCartItemCommandTest);

        });
        verify(shoppingCartRepositoryPort, never()).updateShoppingCart(any(),any());

    }


}
