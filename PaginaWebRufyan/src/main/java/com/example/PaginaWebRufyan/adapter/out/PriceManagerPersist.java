package com.example.PaginaWebRufyan.adapter.out;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "price_manager_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
public abstract class PriceManagerPersist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
