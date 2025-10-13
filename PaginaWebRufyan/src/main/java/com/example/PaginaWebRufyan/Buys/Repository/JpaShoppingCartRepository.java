package com.example.PaginaWebRufyan.Buys.Repository;

import com.example.PaginaWebRufyan.Buys.Entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    Optional<ShoppingCartEntity> findCartByUserId(Long userId);

}
