package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.Image;
import lombok.Getter;

@Getter
public class ImageDTO {
    private Integer id=0;
    private String productName="";


    public ImageDTO(Image image){
    this.id = image.getId();
    this.productName= image.getProductName();
    }

}
