package com.example.PaginaWebRufyan.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
    private Integer quantity;
    //private Boolean isOriginalSelected;
    //private ClothingSizeEnum size;
    @ElementCollection
    @CollectionTable(name = "details_cartItem_key_value", joinColumns = @JoinColumn(name = "CartItem_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> details = new HashMap<>();

    // options for details are: boolean original, ClothingSize size,
    private BigDecimal pricePerUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shopping_cart_id")
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ShoppingCart shoppingCart;

   // public CartItem(){}


    public CartItem(ShoppingCart shoppingCart, Map<String, String> details, ClothingSizeEnum size, Boolean isOriginalSelected, Integer quantity, Product product) {
        this.shoppingCart = shoppingCart;
       details.put("size",size.toString());
       details.put("isOriginalSelected",isOriginalSelected.toString());


       this.pricePerUnit= product.getPriceManager().getPriceWithDetails(details);
        //this.size = size;
        //this.isOriginalSelected = isOriginalSelected;
        this.quantity = quantity;
        this.product = product;

    }

   /* public CartItem(Product product, Integer quantity, Boolean isOriginalSelected){
        this.product = product;
        this.quantity= quantity;
        //this.pricePerUnit= pricePerUnit;
        Map<String,Object> details = new HashMap<>();
        details.put("quantity",quantity);
        details.put("isOriginalSelected",isOriginalSelected);
        this.pricePerUnit
    }

    */


}
