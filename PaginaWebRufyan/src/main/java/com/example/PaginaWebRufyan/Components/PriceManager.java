package com.example.PaginaWebRufyan.Components;

import com.example.PaginaWebRufyan.Entity.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface PriceManager {


     Object getPriceData();

     BigDecimal getPriceWithDetails(Map<String,String> details);
     void setPriceData(Product product, Map <String,String> additionalFeatures);



}
