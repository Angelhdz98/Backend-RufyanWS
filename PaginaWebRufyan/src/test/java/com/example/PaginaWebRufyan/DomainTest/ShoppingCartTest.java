package com.example.PaginaWebRufyan.DomainTest;

import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.example.PaginaWebRufyan.adapter.out.SinglePriceManager;
import com.example.PaginaWebRufyan.domain.model.*;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ClothingColorEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ClothingItemDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingItemDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingPriceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartTest {

    PaintingItemDetails paintingItemOrginalSelected = new PaintingItemDetails(1,true);
    PaintingItemDetails paintingItem2CopiesSelected = new PaintingItemDetails(2,false);

    ClothingItemDetails clothingItemDetails = new ClothingItemDetails(2, ClothingColorEnum.BLACK, ClothingSizeEnum.M);


    CartItemDomain cartMock1 = Mockito.mock(CartItemDomain.class);
    CartItemDomain cartMock2 = Mockito.mock(CartItemDomain.class);
    CartItemDomain cartMock3 = Mockito.mock(CartItemDomain.class);

    PaintingDomain productMock1 = Mockito.mock(PaintingDomain.class);
    BodyClothingDomain productMock2 = Mockito.mock(BodyClothingDomain.class);
    BigDecimal precio1Original= new BigDecimal("1000");
    BigDecimal precio2Copia=new BigDecimal(550);
    BigDecimal singlePrice = new BigDecimal("300");
    SinglePriceManager singlePriceManager = new SinglePriceManager(singlePrice);
    PaintingPriceManager  painting1Price = new PaintingPriceManager(precio2Copia,precio1Original) ;


    Long id = 1L;
@BeforeEach
    public void setUp(){
        when(cartMock1.getProduct()).thenReturn(productMock1);
        when(cartMock2.getProduct()).thenReturn(productMock2);
        when(productMock1.getId()).thenReturn(1L);
        when(productMock2.getId()).thenReturn(2L);

        when(cartMock1.getDetails()).thenReturn(paintingItemOrginalSelected);
        when(cartMock2.getDetails()).thenReturn(clothingItemDetails);
        when(cartMock3.getDetails()).thenReturn(paintingItem2CopiesSelected);

        when(productMock1.getPriceManagerBase()).thenReturn(painting1Price);
        when(productMock2.getPriceManagerBase()).thenReturn(singlePriceManager);


        when(cartMock1.getItemTotalAmount()).thenReturn(precio1Original);
        when(cartMock2.getItemTotalAmount()).thenReturn(singlePriceManager.getPrice());
        when(cartMock3.getItemTotalAmount()).thenReturn(precio2Copia);

    }


    @Test
    @DisplayName("Test para  crear un ShoppingCart exitosamente sin un  usuario asociado")
    public void shouldCreateShoppingCartNoUserRelated(){
        //petición de compra de un invitado

        ShoppingCartDomain newShoppingCartNoUser = new ShoppingCartDomain(null, null, Set.of(cartMock1, cartMock2));

        assertThat(newShoppingCartNoUser).isInstanceOf(ShoppingCartDomain.class);
        assertThat(newShoppingCartNoUser.getItems().size()).isEqualTo(2);
        assertThat(newShoppingCartNoUser.getUserId()).isNull();
        assertThat(newShoppingCartNoUser.getId()).isNull();
        assertThat(newShoppingCartNoUser.getSubtotalAmount()).isEqualTo(precio1Original.add(singlePrice));

    }
    @Test
    @DisplayName("Test para  crear un ShoppingCart exitosamente con un  usuario asociado")
    public void shouldCreateShoppingCartUserRelated(){
        //petición de compra de un invitado

 Long userId = 1L;
        ShoppingCartDomain newShoppingCartWithUser = new ShoppingCartDomain(null, id, Set.of(cartMock1, cartMock2));
        assertThat(newShoppingCartWithUser).isInstanceOf(ShoppingCartDomain.class);
        assertThat(newShoppingCartWithUser.getItems().size()).isEqualTo(2);
        assertThat(newShoppingCartWithUser.getUserId()).isEqualTo(id);
        assertThat(newShoppingCartWithUser.getSubtotalAmount()).isEqualTo(precio1Original.add(singlePriceManager.getPrice()));

    }
    @Test
    @DisplayName("Test  para agregar un cartitem a un shopping cart de manera exitosa")
    public void shouldAddCartItem(){



        ShoppingCartDomain shoppingCartWithUser = new ShoppingCartDomain(id, null, Set.of(cartMock1, cartMock2));
       shoppingCartWithUser
               .addItem(cartMock3);
        assertThat(shoppingCartWithUser.getItems().size())
                .isEqualTo(3);
        assertThat(shoppingCartWithUser.getItems()).contains(cartMock3);
        assertThat(shoppingCartWithUser.getSubtotalAmount()).isEqualTo(precio1Original.add(singlePrice).add(precio2Copia));


    }
    @Test
    @DisplayName("Test  para eliminar un cartitem a un shopping cart de manera exitosa")
    public void shouldDeleteCartItem(){



        ShoppingCartDomain shoppingCartWithUser = new ShoppingCartDomain(id, null, Set.of(cartMock1, cartMock2));
        shoppingCartWithUser
                .deleteItem(cartMock1);
        assertThat(shoppingCartWithUser.getItems().size())
                .isEqualTo(1);
        assertThat(shoppingCartWithUser.getItems()).contains(cartMock2);
        assertThat(shoppingCartWithUser.getItems()).doesNotContain(cartMock1);

        assertThat(shoppingCartWithUser.getSubtotalAmount()).isEqualTo(cartMock2.getItemTotalAmount());



    }
    @Test
    @DisplayName("Test para evitar agregar una cartitem que ya existe en el shopping cart")
    public void shouldThrowExceptionAddingAnInexistentCartItem(){


        PaintingItemDetails twoCopies = new PaintingItemDetails(2, false);
        PaintingItemDetails originalDetails = new PaintingItemDetails(1, true);

        CartItemDomain newCarItemMock = Mockito.mock(CartItemDomain.class);




        ShoppingCartDomain shoppingCartWithUser = new ShoppingCartDomain(id, null, Set.of(cartMock1, cartMock2, cartMock3));

        assertThrows(AlreadyExistIdenticatorException.class, ()-> {
            shoppingCartWithUser.addItem(cartMock2);
        });

        assertThrows(AlreadyExistIdenticatorException.class, ()-> {
            when(cartMock1.getProduct()).thenReturn(productMock1);
            when(cartMock2.getProduct()).thenReturn(productMock2);
            PaintingItemDetails paintingItemDetails = new PaintingItemDetails(3, false);
            when(cartMock1.getDetails()).thenReturn(paintingItemDetails);//3 copias

            when(newCarItemMock.getProduct()).thenReturn(productMock1);
            when(newCarItemMock.getDetails()).thenReturn(twoCopies);

            shoppingCartWithUser.addItem(newCarItemMock);
        });
        assertThrows(AlreadyExistIdenticatorException.class, ()-> {
            when(newCarItemMock.getDetails()).thenReturn(originalDetails);
            when(cartMock1.getDetails()).thenReturn(originalDetails);
            shoppingCartWithUser.addItem(newCarItemMock);
        });


    }
    @Test
    @DisplayName("Test para evitar eliminar una cartitem que no existe en el shopping cart")
    public void shouldThrowExceptionDeletingAnInexistentCartItem(){
        ShoppingCartDomain shoppingCartWithUser = new ShoppingCartDomain(id, null, Set.of(cartMock1, cartMock2));

        assertThrows(ResourceNotFoundException.class, ()-> {
            shoppingCartWithUser.deleteItem(cartMock3);
        });

    }




}
