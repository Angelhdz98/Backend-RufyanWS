package com.example.PaginaWebRufyan.Repository;

import com.example.PaginaWebRufyan.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository <CartItem,Integer> {
}
