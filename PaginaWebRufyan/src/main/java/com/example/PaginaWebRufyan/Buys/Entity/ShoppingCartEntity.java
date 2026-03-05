package com.example.PaginaWebRufyan.Buys.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
   @OneToMany(  mappedBy = "shoppingCart",
           fetch = FetchType.EAGER,
           cascade = {CascadeType.ALL},
           orphanRemoval = true)
   //@ToString.Exclude
   @EqualsAndHashCode.Exclude
    private List<CartItem> itemList= new ArrayList<>();
    private BigDecimal totalAmount;
    private LocalDate updatedAt;


    @PreUpdate
    void preUpdate(){
        updatedAt = LocalDate.now();
    }



    public ShoppingCartEntity(Long id, Long userId, List<CartItem> itemList, BigDecimal totalAmount) {
        this.id = id;
        this.userId = userId;
        this.itemList = itemList;
        this.totalAmount = totalAmount;
    }
}
