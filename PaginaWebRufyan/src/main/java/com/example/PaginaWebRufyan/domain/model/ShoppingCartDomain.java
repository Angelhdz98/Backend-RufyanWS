package com.example.PaginaWebRufyan.domain.model;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Getter
public class ShoppingCartDomain {
    private final Long id;
    private final Long userId;
    private final Set<CartItemDomain> items;

    public BigDecimal getSubtotalAmount(){
        return items.stream().map(CartItemDomain::getItemTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItem(CartItemDomain itemDomain) {
        if(items.add(itemDomain))
            itemDomain.getProduct();
    }

    public void deleteItem(CartItemDomain itemDomain){
       if( !items.remove(itemDomain)) throw new ResourceNotFoundException("no se encontró el item que se quiere eliminar");
    }
}
