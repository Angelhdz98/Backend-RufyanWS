package com.example.PaginaWebRufyan.Entity;

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
    private Integer id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name="product_id")
    @JsonBackReference
    private Product product;
    private Integer quantity;
    private Boolean isOriginalSelected;
    private BigDecimal pricePerUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shopping_cart_id")
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ShoppingCart shoppingCart;

   // public CartItem(){}

    public CartItem(Product product,Integer quantity, Boolean isOriginalSelected){
        this.product = product;
        this.quantity= quantity;
        this.isOriginalSelected= isOriginalSelected;
        //this.pricePerUnit= pricePerUnit;

        if(product instanceof Painting){
            if(isOriginalSelected){
                this.pricePerUnit = BigDecimal.valueOf(product.getPrice());
            }
            else{
                Painting painting = (Painting) product;
                this.pricePerUnit = BigDecimal.valueOf(painting.getPricePerCopy());
            }

        }else{
            this.pricePerUnit = BigDecimal.valueOf(product.getPrice());
        }


    }


}
