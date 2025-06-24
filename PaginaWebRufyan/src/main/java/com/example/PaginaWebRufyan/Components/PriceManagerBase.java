package com.example.PaginaWebRufyan.Components;

import com.example.PaginaWebRufyan.Entity.Product;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@MappedSuperclass
@Getter
@Setter
public abstract class PriceManagerBase {
    public abstract BigDecimal getPriceWithDetails(Map<String, String> details);

    public abstract Object getPriceData();
    public abstract void setPriceData(Product product, Map <String,String> additionalFeatures);
    public abstract  Map<String,BigDecimal> getPriceMap();

}
