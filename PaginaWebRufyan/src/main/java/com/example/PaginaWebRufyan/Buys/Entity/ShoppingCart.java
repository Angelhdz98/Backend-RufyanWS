package com.example.PaginaWebRufyan.Buys.Entity;

import com.example.PaginaWebRufyan.Buys.DTO.CartItemRegisterNew;
import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ShoppingCart {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
   @OneToMany(mappedBy = "shoppingCart",fetch = FetchType.EAGER,
           cascade = {CascadeType.ALL},orphanRemoval = true)
   //@ToString.Exclude
   @EqualsAndHashCode.Exclude
    private Set<CartItem> itemList;
    private BigDecimal totalAmount;
    private LocalDate updatedAt;


    @PreUpdate
    void preUpdate(){
        updatedAt = LocalDate.now();
    }

    public ShoppingCart(Long id, Long userId, Set<CartItem> itemList, BigDecimal totalAmount) {
        this.id = id;
        this.userId = userId;
        this.itemList = itemList;
        this.totalAmount = totalAmount;
    }
}
