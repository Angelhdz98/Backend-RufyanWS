package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Buys.Entity.CartItem;
import com.example.PaginaWebRufyan.Buys.Entity.ShoppingCartEntity;
import com.example.PaginaWebRufyan.domain.model.CartItemDetailsFactory;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingCartMapper {

    public static ShoppingCartEntity toEntity(ShoppingCartDomain shoppingCartDomain){
     Set<CartItemDomain> items = shoppingCartDomain.getItems();
     //Set<Product> productSet = items.stream().map((CartItemDomain cartItemDomain )->ProductMapper.toEntity( cartItemDomain.getProduct()) ).collect(Collectors.toSet());

        Set<CartItem> cartItems = items.stream().map(CartItemMapper::toEntity).collect(Collectors.toSet());


        return new ShoppingCartEntity(shoppingCartDomain.getId(),shoppingCartDomain.getUserId(),cartItems, shoppingCartDomain.getSubtotalAmount());
    }

 public static ShoppingCartDomain toDomain(ShoppingCartEntity shoppingCartEntity){

        Set<CartItem> itemList = shoppingCartEntity.getItemList();

        /*Set<ProductDomain> productSet =      itemList
                .stream()
                .map(CartItem::getProduct)
                .collect(Collectors.toSet())
                .stream()
                .map(ProductMapper::toDomain).collect(Collectors.toSet());

         */
        LinkedHashSet<CartItemDomain> cartItemDomainSet = (LinkedHashSet<CartItemDomain>) itemList.stream().map((CartItem item) -> {
            ProductDomain product = ProductMapper.toDomain(item.getProduct());
            assert product != null;
            CartItemDetails itemDetails = CartItemDetailsFactory.createCartItemDetails(product, item.getCartItemDetails());
            return new CartItemDomain(item.getId(), product, itemDetails);
        }).collect(Collectors.toSet());

        return new ShoppingCartDomain(shoppingCartEntity.getId(), shoppingCartEntity.getUserId(), cartItemDomainSet);
    }

}
