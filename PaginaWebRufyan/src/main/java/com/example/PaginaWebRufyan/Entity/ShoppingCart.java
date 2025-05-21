package com.example.PaginaWebRufyan.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
@Setter
public class ShoppingCart {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(mappedBy = "shoppingCart")
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserEntity user;
   @OneToMany(mappedBy = "shoppingCart",
           cascade = {CascadeType.ALL},orphanRemoval = true)
   @ToString.Exclude
   @EqualsAndHashCode.Exclude
   @Builder.Default
    private Set<CartItem> itemList = new HashSet<>();

    private LocalDate updatedAt;

    @PreUpdate
    void preUpdate(){
        updatedAt = LocalDate.now();
    }

    public void addCartItem (CartItem cartItem){

        this.itemList.add(cartItem);
        cartItem.setShoppingCart(this);
    }

    public void deleteCartItem(CartItem cartItem){
        this.itemList.remove(cartItem);
    }

}
