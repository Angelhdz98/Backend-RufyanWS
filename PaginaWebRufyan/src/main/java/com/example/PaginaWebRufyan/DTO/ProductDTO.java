package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@SuperBuilder
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ProductDTO {
private String name;
private List<Image> images;
private String style;
private LocalDate creationDate;

private String description;
private Map<String, Object> price;
private Map<String, Object> stock;
private Map<String, String> additionalFeatures;



public ProductDTO(Product product){

    this.name= product.getName();
    this.images= product.getImage();
    this.style = product.getStyle();
    this.creationDate= product.getCreationDate();
    this.description= product.getDescription();
    this.additionalFeatures= product.getAdditionalFeatures();

if(product.getPriceManager().getPriceData() instanceof Map<?,?>){
    this.price = (Map<String, Object>) product.getPriceManager().getPriceData();
}
this.stock = (Map<String,Object>) product.getStockManager().getStockInfo();


}


}
