package com.example.PaginaWebRufyan.Buys.Entity;

import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private int id;
    @ManyToOne
    @JsonBackReference
private Product product;
private int quantity;
private BigDecimal priceAtPurchase;

}
