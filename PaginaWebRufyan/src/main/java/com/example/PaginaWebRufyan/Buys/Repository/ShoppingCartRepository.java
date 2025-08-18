package com.example.PaginaWebRufyan.Buys.Repository;

import com.example.PaginaWebRufyan.Buys.Entity.ShoppingCart;
import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    Optional<ShoppingCart> findCartByUser(UserEntity user);

}
