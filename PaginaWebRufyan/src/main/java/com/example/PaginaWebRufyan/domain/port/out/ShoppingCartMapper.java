package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Buys.Entity.CartItem;
import com.example.PaginaWebRufyan.Buys.Entity.ShoppingCartEntity;
import com.example.PaginaWebRufyan.domain.model.CartItemDetailsFactory;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import org.springframework.stereotype.Component;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class ShoppingCartMapper {

    private final CartItemMapper cartItemMapper;
    private final ProductMapper productMapper;

    public ShoppingCartMapper(CartItemMapper cartItemMapper, ProductMapper productMapper) {
        this.cartItemMapper = cartItemMapper;
        this.productMapper = productMapper;
    }


    public  ShoppingCartEntity toEntity(ShoppingCartDomain shoppingCartDomain){

        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        shoppingCartEntity.setId(shoppingCartDomain.getId());
        shoppingCartEntity.setUserId(shoppingCartDomain.getUserId());

        List<CartItem> cartItems = shoppingCartDomain.getItems().stream().map(cartItemMapper::toEntity).toList();
    cartItems.forEach(item-> item.setShoppingCart(shoppingCartEntity));
    shoppingCartEntity.getItemList().clear();
    shoppingCartEntity.getItemList().addAll(cartItems);
shoppingCartEntity.setTotalAmount(shoppingCartDomain.getSubtotalAmount());
        return shoppingCartEntity;

    }

 public  ShoppingCartDomain toDomain(ShoppingCartEntity shoppingCartEntity){

        List<CartItem> itemList = shoppingCartEntity.getItemList();

        /*Set<ProductDomain> productSet =      itemList
                .stream()
                .map(CartItem::getProduct)
                .collect(Collectors.toSet())
                .stream()
                .map(ProductMapper::toDomain).collect(Collectors.toSet());

         */
     Set<CartItemDomain> cartItemDomainSet = itemList.stream().map((CartItem item) -> {
         ProductDomain product = productMapper.toDomain(item.getProduct());
         assert product != null;
         CartItemDetails itemDetails = CartItemDetailsFactory.createCartItemDetails(product, item.getCartItemDetails());
         return new CartItemDomain(item.getId(), product, itemDetails);
     }).collect(Collectors.toCollection(LinkedHashSet::new));


     return new ShoppingCartDomain(shoppingCartEntity.getId(), shoppingCartEntity.getUserId(), cartItemDomainSet);
    }

}
