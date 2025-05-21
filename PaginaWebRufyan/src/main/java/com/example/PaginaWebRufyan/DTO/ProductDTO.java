package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductDTO {
private Integer id;
private String name;
private Integer price;
private List<Image> images;
private String style;
private LocalDate creationDate;
private ProductsCategory category;
private String description;



public ProductDTO(Product product){
    this.id= product.getId();
    this.name= product.getName();
    this.price= product.getPrice();
    this.images= product.getImage();
    this.style = product.getStyle();
    this.creationDate= product.getCreationDate();
    this.category = product.getCategory();
    this.description= product.getDescription();
}

}
