package com.example.PaginaWebRufyan.Products.DTO.Product;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@SuperBuilder
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ProductDTO {
private String name="";
private List<Image> images= new ArrayList<>();
private String style="";
private LocalDate creationDate= LocalDate.now();

private String description ="";
private Map<String, BigDecimal> price;
private Map<String, Object> stock;
private Map<String, String> additionalFeatures;



public ProductDTO(Product product){

    this.name= product.getName();
    this.images= product.getImage();
    this.style = product.getStyle();
    this.creationDate= product.getCreationDate();
    this.description= product.getDescription();
    this.additionalFeatures= product.getAdditionalFeatures();

    this.price =  product.getPriceManager().getPriceMap();

this.stock = (Map<String,Object>) product.getStockManager().getStockMap();


}


}
