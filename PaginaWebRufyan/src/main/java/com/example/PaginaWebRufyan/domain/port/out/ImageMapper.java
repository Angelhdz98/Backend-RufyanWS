package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;

public class ImageMapper {
   public static ImageDomain toDomain(Image image){
    return new ImageDomain(image.getId(), image.getProductName(), image.getUrl());
    }
public static Image toEntity(ImageDomain imageDomain){

        return new Image(imageDomain.id(), imageDomain.productName(), imageDomain.url());
    }

}
