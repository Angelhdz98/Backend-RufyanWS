package com.example.PaginaWebRufyan.adapter.out;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.StockEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "stock_manager_type", discriminatorType = DiscriminatorType.STRING)
public abstract class StockManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Enumerated(EnumType.STRING)
    @Column(name = "stock_type")
    protected StockEnum stockType;

    public StockManager(){
        this.id=0L;
        this.stockType = StockEnum.SINGLE_STOCK;

    }

    public StockManager(Long id, StockEnum stockType){
        this.id=id;
        this.stockType = stockType;

    }

    @JsonProperty("stockType")
    public abstract StockEnum getStockType();

}
