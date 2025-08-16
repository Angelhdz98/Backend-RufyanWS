package com.example.PaginaWebRufyan.Image.DTO;

import com.example.PaginaWebRufyan.Image.Image;
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
