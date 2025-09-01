package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Buys.Repository.JpaShoppingCartRepository;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public class ShoppingCartRepositoryJpaImpl implements ShoppingCartRepositoryPort{
    private final JpaShoppingCartRepository shoppingCartRepository;

    public ShoppingCartRepositoryJpaImpl(JpaShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }
    private ShoppingCartDomain retrieveShoppingCartByUserId(Long userId){
        return shoppingCartRepository.findCartByUserId(userId).map(ShoppingCartMapper::toDomain).orElseThrow(()-> new ResourceNotFoundException("No se encontró carrito por el user id: "+ userId));
    }

    @Override
    public ShoppingCartDomain retrieveShoppingCart(Long userId) {
        return  retrieveShoppingCartByUserId(userId);
    }

    @Override
    public ShoppingCartDomain updateShoppingCart(Long userId, ShoppingCartDomain shoppingCartDomain) {
        retrieveShoppingCartByUserId(userId);
        return ShoppingCartMapper.toDomain(shoppingCartRepository.save(ShoppingCartMapper.toEntity(shoppingCartDomain)));
    }

    @Override
    public ShoppingCartDomain emptyShoppingCart(Long userId) {
        ShoppingCartDomain shoppingCartDomain = retrieveShoppingCartByUserId(userId);
        //shoppingCartDomain.setItems(Set.of());
        Set<CartItemDomain> items = shoppingCartDomain.getItems();
        items.removeIf( (CartItemDomain item)-> true);
        return ShoppingCartMapper.toDomain(shoppingCartRepository.save(ShoppingCartMapper.toEntity(shoppingCartDomain)));
    }

}
