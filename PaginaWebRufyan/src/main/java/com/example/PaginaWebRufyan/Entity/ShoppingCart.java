package com.example.PaginaWebRufyan.Entity;

import com.example.PaginaWebRufyan.DTO.CartItemRegisterDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
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
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private LocalDate updatedAt;

    @PreUpdate
    void preUpdate(){
        updatedAt = LocalDate.now();
    }

    public void updateTotal (){
        this.totalAmount = itemList.stream().map((CartItem item)->
        {
           return item.getPricePerUnit().multiply(BigDecimal.valueOf(item.getQuantity()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addCartItem (CartItem cartItem){

        this.itemList.add(cartItem);
        cartItem.setShoppingCart(this);
        cartItem.getProduct().setAvailableStock(cartItem.getProduct().getAvailableStock()-cartItem.getQuantity());
        updateTotal();
    }

    public void deleteCartItem(CartItem cartItem){
        cartItem.getProduct().setAvailableStock(cartItem.getProduct().getAvailableStock()+ cartItem.getQuantity());
        this.itemList.remove(cartItem);
        updateTotal();
    }


    public void deleteCartItem(CartItemRegisterDTO cartItem){

      Optional<CartItem>  optionalCartItemToDelete = this.itemList.stream().filter((CartItem item )-> item.getProduct().getId().equals(cartItem.getProductId())&& item.getIsOriginalSelected().equals(cartItem.getIsOriginalSelected()) ).findFirst();
      if (optionalCartItemToDelete.isPresent()){

         CartItem cartItemToDelete =  optionalCartItemToDelete.get();
                  cartItemToDelete.getProduct().setAvailableStock(cartItemToDelete.getProduct().getAvailableStock()+cartItem.getQuantity());
          this.itemList.remove(cartItemToDelete);
      }



                /*
                = this.itemList.stream().filter(( CartItem item)->!(

                item.getProduct().getId().equals(cartItem.getProductId())&& item.getIsOriginalSelected().equals(cartItem.getIsOriginalSelected()) ) ).collect(Collectors.toSet());

*/
        updateTotal();
    }
}
