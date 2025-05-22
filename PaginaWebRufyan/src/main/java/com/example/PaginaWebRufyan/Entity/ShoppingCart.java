package com.example.PaginaWebRufyan.Entity;

import com.example.PaginaWebRufyan.DTO.CartItemRegisterDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
        cartItem.getProduct().setAvailableStock(cartItem.getProduct().getAvailableStock()-cartItem.getQuantity());
    }

    public void deleteCartItem(CartItem cartItem){
        cartItem.getProduct().setAvailableStock(cartItem.getProduct().getAvailableStock()+ cartItem.getQuantity());
        this.itemList.remove(cartItem);
    }

    public void deleteCartItem(CartItemRegisterDTO cartItem){
        this.itemList.removeIf(item-> item.getProduct().getId().equals(cartItem.getProductId()) && item.getIsOriginalSelected().equals(cartItem.getIsOriginalSelected()));
                /*
                = this.itemList.stream().filter(( CartItem item)->!(

                item.getProduct().getId().equals(cartItem.getProductId())&& item.getIsOriginalSelected().equals(cartItem.getIsOriginalSelected()) ) ).collect(Collectors.toSet());
*/
    }
}
