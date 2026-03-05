package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Buys.Repository.JpaShoppingCartRepository;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public class ShoppingCartRepositoryJpaImpl implements ShoppingCartRepositoryPort{

    private final ShoppingCartMapper shoppingCartMapper;

    private final JpaShoppingCartRepository shoppingCartRepository;

    public ShoppingCartRepositoryJpaImpl(ShoppingCartMapper shoppingCartMapper, JpaShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartRepository = shoppingCartRepository;
    }
    @Override
    public ShoppingCartDomain createShoppingCart(Long userId) {
        ShoppingCartDomain shoppingCartDomain = new ShoppingCartDomain(0L, userId);
        return shoppingCartMapper.toDomain(shoppingCartRepository.save(shoppingCartMapper.toEntity(shoppingCartDomain)));
    }
    private ShoppingCartDomain retrieveShoppingCartByUserId(Long userId){
        return shoppingCartRepository.findCartByUserId(userId).map(shoppingCartMapper::toDomain).orElseThrow(()-> new ResourceNotFoundException("No se encontró carrito por el user id: "+ userId));
    }

    @Override
    public ShoppingCartDomain retrieveShoppingCart(Long userId) {
        return  retrieveShoppingCartByUserId(userId);
    }

    @Override
    public ShoppingCartDomain updateShoppingCart(Long userId, ShoppingCartDomain shoppingCartDomain) {
        retrieveShoppingCartByUserId(userId);
        return shoppingCartMapper.toDomain(shoppingCartRepository.save(shoppingCartMapper.toEntity(shoppingCartDomain)));
    }

    @Override
    public ShoppingCartDomain emptyShoppingCart(Long userId) {
        ShoppingCartDomain shoppingCartDomain = retrieveShoppingCartByUserId(userId);
        //shoppingCartDomain.setItems(Set.of());
        Set<CartItemDomain> items = shoppingCartDomain.getItems();
        items.removeIf( (CartItemDomain item)-> true);
        return shoppingCartMapper.toDomain(shoppingCartRepository.save(shoppingCartMapper.toEntity(shoppingCartDomain)));
    }

}
