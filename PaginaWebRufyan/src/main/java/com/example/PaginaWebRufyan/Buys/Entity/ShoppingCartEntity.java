package com.example.PaginaWebRufyan.Buys.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ShoppingCartEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
   @OneToMany(fetch = FetchType.EAGER,
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

    public ShoppingCartEntity(Long id, Long userId, Set<CartItem> itemList, BigDecimal totalAmount) {
        this.id = id;
        this.userId = userId;
        this.itemList = itemList;
        this.totalAmount = totalAmount;
    }
}
