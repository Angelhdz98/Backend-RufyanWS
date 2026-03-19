package com.example.PaginaWebRufyan.domain.model;
import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Getter
@ToString
public class ShoppingCartDomain {
    private final Long id;
    private final Long userId;
    private final  Set<CartItemDomain> items =new HashSet<>();

    public BigDecimal getSubtotalAmount(){
        return items.stream().map(CartItemDomain::getItemTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItem(CartItemDomain itemDomain) {
        items.forEach(( CartItemDomain actualItem)->{
           if( actualItem.getDetails().areSameDetails(itemDomain.getDetails()) &&
                   itemDomain.getProduct().equals(actualItem.getProduct()))
               throw new AlreadyExistIdenticatorException("El item que se quiere agregar ya existe en el carrito");
        });
        items.add(itemDomain);
    }

    public void deleteItem(Long cartItemIdToDelete){
          items.removeIf(item-> item.getId().equals(cartItemIdToDelete));
    }

    public ShoppingCartDomain(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }
    public ShoppingCartDomain(Long id, Long userId, Set<CartItemDomain> itemsToAdd) {
        this.id = id;
        this.userId = userId;
        this.items.addAll(itemsToAdd);
    }
}
