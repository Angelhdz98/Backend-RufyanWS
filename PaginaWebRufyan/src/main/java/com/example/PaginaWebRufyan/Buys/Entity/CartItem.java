package com.example.PaginaWebRufyan.Buys.Entity;

import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name="product_id")
    @JsonBackReference
    private Product product;
    //private Boolean isOriginalSelected;
    //private ClothingSizeEnum size;
  /*  @ElementCollection
    @CollectionTable(name = "details_cartItem_key_value", joinColumns = @JoinColumn(name = "CartItem_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> details = new HashMap<>();
    */
    // PaintingItemDetails y clothingItemDetails
    private CartItemDetails cartItemDetails;




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
