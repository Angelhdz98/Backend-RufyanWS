package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PriceManagerBase;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.StockManagerBase;
import lombok.Getter;

import java.util.Set;

@Getter
public abstract class ProductDomain {
private final Long id;
private final String name;
private final StockManagerBase stockManagerBase;
private final PriceManagerBase priceManagerBase;
private final Set<Image> images;
private final ProductDomainDetails productDetails;


    public ProductDomain(Long id, String name, StockManagerBase stockManagerBase, PriceManagerBase priceManagerBase, Set<Image> images, ProductDomainDetails productDetails) {
        this.id = id;
        this.name = name;
        this.stockManagerBase = stockManagerBase;
        this.priceManagerBase = priceManagerBase;
        this.images = images;
        this.productDetails = productDetails;
    }

    abstract void increaseStock(CartItemDomain itemDomain);
    abstract void decreaseStock(CartItemDomain itemDomain);



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
