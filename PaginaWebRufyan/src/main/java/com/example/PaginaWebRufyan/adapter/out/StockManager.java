package com.example.PaginaWebRufyan.adapter.out;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "stock_manager_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
public abstract class StockManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

}
