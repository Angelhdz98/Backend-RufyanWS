package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Buys.Entity.CartItem;
import com.example.PaginaWebRufyan.adapter.out.persistence.CartItemDetailsAdapter;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.CartItemDetailsFactory;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomainFactory;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;

public class CartItemMapper {
/*
ProductDomain product, CartItemDetailsAdapter cartItemDetailsAdapter

// cartItem
Long id,
Product product,
 Long productId,
 CartItemDetailsAdapter cartItemDetails

 */
    public static CartItemDomain toDomain(CartItem cartItem){

        ProductDomain productDomain = ProductMapper.toDomain(cartItem.getProduct());

        assert productDomain != null;
        CartItemDetails cartItemDetails = CartItemDetailsFactory.createCartItemDetails(productDomain, cartItem.getCartItemDetails());

        return new CartItemDomain(cartItem.getId(), productDomain,cartItemDetails);
    }

    public static CartItem toEntity(CartItemDomain cartItemDomain){

        Product productPersistenceAdapter = ProductMapper.toEntity(cartItemDomain.getProduct());

        CartItemDetailsAdapter cartItemDetailsAdapter = CartItemDetailsMapper.toEntity(cartItemDomain.getDetails(),cartItemDomain.getProduct().getProductType());


        return new CartItem(cartItemDomain.getId(),productPersistenceAdapter,cartItemDetailsAdapter,cartItemDomain.getItemTotalAmount());

    }

}
