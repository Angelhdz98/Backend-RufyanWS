package com.example.PaginaWebRufyan.adapter.out;


import com.example.PaginaWebRufyan.domain.model.ValueObjects.PricingTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "price_manager_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class PriceManagerPersist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("pricingTypeEnum")
    public abstract PricingTypeEnum getPricingType();


}
