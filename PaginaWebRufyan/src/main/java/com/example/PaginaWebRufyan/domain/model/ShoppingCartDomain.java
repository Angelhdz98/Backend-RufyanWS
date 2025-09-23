package com.example.PaginaWebRufyan.domain.model;
import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
public class ShoppingCartDomain {
    private final Long id;
    private final Long userId;
    private final  Set<CartItemDomain> items = new LinkedHashSet<>();

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

    public void deleteItem(CartItemDomain itemDomain){
        if(!items.contains(itemDomain)) throw  new ResourceNotFoundException("no se encontró el item que se quiere eliminar");
        items.remove(itemDomain);
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
