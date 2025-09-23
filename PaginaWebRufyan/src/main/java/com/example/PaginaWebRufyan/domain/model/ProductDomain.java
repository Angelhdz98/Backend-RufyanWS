package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PriceManagerBase;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.StockManagerBase;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@ToString
@Getter
public abstract class ProductDomain {
private  Long id;
private  String name;
private final StockManagerBase stockManagerBase;
private final PriceManagerBase priceManagerBase;
private final Set<ImageDomain> images;
private ProductDomainDetails productDetails;
private ProductTypeEnum productType;
private String description;
private Boolean isFavorite;

private final static Integer MIN_DESCRIPTION_LENGTH = 10;
private final static Integer MIN_LENGTH_NAME =3;
    private final static Integer MIN_LENGTH_IMAGES =2;

    public ProductDomain(StockManagerBase stockManagerBase, PriceManagerBase priceManagerBase, Set<ImageDomain> images) {
        this.stockManagerBase = stockManagerBase;
        this.priceManagerBase = priceManagerBase;
        this.images = images;
    }

    public ProductDomain(Long id, String name, StockManagerBase stockManagerBase, PriceManagerBase priceManagerBase, Set<ImageDomain> images, ProductDomainDetails productDetails, ProductTypeEnum productType, String description, Boolean isFavorite) {
        this.id = id;
        if(name.length()<MIN_LENGTH_NAME) throw new IllegalArgumentException("El nombre de un producto debe tener un minimo de " + MIN_LENGTH_NAME + "caracteres");
        this.name = name;
        this.stockManagerBase = stockManagerBase;
        this.priceManagerBase = priceManagerBase;
        if(images.size()<MIN_LENGTH_IMAGES) throw new IllegalArgumentException("Todos los productos deben contar con un minimo de " + MIN_LENGTH_IMAGES+ " imagenes");
        this.images = images;
        this.productDetails = productDetails;
        this.productType = productType;
        this.description = description;
        this.isFavorite = isFavorite;

    }

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


         if(images.size()<MIN_LENGTH_IMAGES){
            throw new IllegalArgumentException("Todos los productos deben contar con un minimo de" + MIN_LENGTH_IMAGES+ "imagenes");
         }
         if(name.length()<MIN_LENGTH_NAME){
             throw new IllegalArgumentException("El nombre de un producto debe tener un minimo de " + MIN_LENGTH_NAME + "caracteres");
         }
         if(description.length()<MIN_DESCRIPTION_LENGTH){
             throw new IllegalArgumentException("La descripción debe de tener un mínimo de: "+ MIN_DESCRIPTION_LENGTH+ " caracteres");

         }
     }
}
