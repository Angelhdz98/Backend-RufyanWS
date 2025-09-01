package com.example.PaginaWebRufyan.Buys.Entity;

import com.example.PaginaWebRufyan.adapter.out.persistence.CartItemDetailsAdapter;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name="product_id")
    @JsonBackReference
    private Product product;
    @Embedded
    private CartItemDetailsAdapter cartItemDetails;
    private BigDecimal totalPrice;



    // options for details are: boolean original, ClothingSize size,
    // private BigDecimal pricePerUnit; // puede ser una función la que nos devuelva el valor

    /* no necesario la bidireccionalidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shopping_cart_id")
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ShoppingCart shoppingCart;
    */


   // public CartItem(){}




}
