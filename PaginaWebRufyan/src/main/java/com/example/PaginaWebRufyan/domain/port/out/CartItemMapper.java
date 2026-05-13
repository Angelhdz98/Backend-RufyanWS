package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Buys.Entity.CartItem;
import com.example.PaginaWebRufyan.adapter.out.persistence.CartItemDetailsAdapter;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.CartItemDetailsFactory;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
/*
ProductDomain product, CartItemDetailsAdapter cartItemDetailsAdapter

// cartItem
Long id,
Product product,
 Long productId,
 CartItemDetailsAdapter cartItemDetails

 */
    private final ProductMapper productMapper;

    public CartItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public  CartItemDomain toDomain(CartItem cartItem){



        ProductDomain productDomain = productMapper.toDomain(cartItem.getProduct());

        assert productDomain != null;
        CartItemDetails cartItemDetails = CartItemDetailsFactory.createCartItemDetails(productDomain, cartItem.getCartItemDetails());

        return new CartItemDomain(cartItem.getId(), productDomain,cartItemDetails);
    }

    public  CartItem toEntity(CartItemDomain cartItemDomain){

        Product productPersistenceAdapter = productMapper.toEntity(cartItemDomain.getProduct());

        CartItemDetailsAdapter cartItemDetailsAdapter = CartItemDetailsMapper.toEntity(cartItemDomain.getDetails(),cartItemDomain.getProduct().getProductType());


        return new CartItem(cartItemDomain.getId(),productPersistenceAdapter,cartItemDetailsAdapter,cartItemDomain.getItemTotalAmount(), null);

    }

}
