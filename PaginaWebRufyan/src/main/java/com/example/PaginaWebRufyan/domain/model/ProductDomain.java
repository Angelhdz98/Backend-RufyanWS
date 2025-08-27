package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PriceManagerBase;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.StockManagerBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class ProductDomain {
private  Long id;
private  String name;
private  StockManagerBase stockManagerBase;
private  PriceManagerBase priceManagerBase;
private Set<ImageDomain> images;
private ProductDomainDetails productDetails;
private ProductTypeEnum productType;
private String description;
private Boolean isFavorite;


    public void increaseStock(CartItemDomain itemDomain){
        stockManagerBase.increaseStock(itemDomain.getProduct(), itemDomain.getDetails());
    }
    public void decreaseStock(CartItemDomain itemDomain){
        stockManagerBase.decreaseStock(itemDomain.getProduct(), itemDomain.getDetails());
    }

    Set<ImageDomain> deleteImageById(Long imageId){
        return images.stream().filter((imageDomain)-> !(imageDomain.id().equals(imageId))).collect(Collectors.toSet());
    }

    Set<ImageDomain> addImage(ImageDomain newImage){
      images.add(newImage);
      return images;
    }

    Set<ImageDomain> addAllImages(Set<ImageDomain> newImages){
        images.addAll(newImages);
        return images;
    }




     void validateProduct(){
         int minLengthName =3;
         int minImages =1;

         if(images.size()<minImages){
            throw new IllegalArgumentException("Todos los productos deben contar con un minimo de" + minImages+ "imagenes");
         }
         if(name.length()<minLengthName){
             throw new IllegalArgumentException("El nombre de un producto debe tener un minimo de " + minLengthName + "caracteres");
         }
     }
}
