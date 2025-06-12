package com.example.PaginaWebRufyan.Entity;

import com.example.PaginaWebRufyan.Components.OriginalStock;
import com.example.PaginaWebRufyan.Components.PaintingPriceManager;
import com.example.PaginaWebRufyan.DTO.ProductImagesRegisterDTO;
import com.example.PaginaWebRufyan.DTO.ProductRegisterDTO;
import com.example.PaginaWebRufyan.Repository.PaintingRepository;
import com.example.PaginaWebRufyan.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
public class ProductFactory {
    @Autowired
    PaintingRepository paintingRepository;
    @Autowired


    public static Product createProductFromRegister(ProductImagesRegisterDTO productData){
        switch (productData.getType()){
            case PAINTING :
                if(productData.getStock().containsKey("stockCopies") &&
                        productData.getStock().containsKey("isOriginalAvailable") &&
                        productData.getPriceManage().containsKey("pricePerCopy") &&
                        productData.getPriceManage().containsKey("pricePerOriginal")){
                    int stockCopies =(int) productData.getStock().get("stockCopies");
                    Boolean isOriginalAvailable = (Boolean) productData.getStock().get("isOriginalAvailable");

                    BigDecimal pricePerCopy = (BigDecimal) productData.getPriceManage().get("pricePerCopy");
                    BigDecimal pricePerOriginal = (BigDecimal) productData.getPriceManage().get("pricePerOriginal");
                    return Painting.builder()
                            .name(productData.getName())
                            .description(productData.getDescription())
                            .creationDate(productData.getCreationDate())
                            .style(productData.getStyle())
                            .additionalFeatures(productData.getAdditionalFeatures())
                            .stockManager(new OriginalStock(stockCopies, isOriginalAvailable))
                            .priceManager(new PaintingPriceManager(pricePerCopy,pricePerOriginal))
                            .isFavorite(productData.getIsFavorite())
                            //.category(productData.getProductsCategory())
                            .image(productData.getImages())
                            .favoriteOf(new HashSet<>(Set.of()))
                            .cartItems(new HashSet<>(Set.of()))
                            .orderItems(new HashSet<>(Set.of()))
                            .alturaCm(Integer.valueOf(productData.getAdditionalFeatures().get("alturaCm")))
                            .largoCm(Integer.valueOf(productData.getAdditionalFeatures().get("largoCm")))
                            .medium( productData.getAdditionalFeatures().get("medium"))
                            .supportMaterial(productData.getAdditionalFeatures().get("supportMaterial"))
                            .build();

                }else throw new InputMismatchException(" missing values from stock and price managing");
            case BODYCLOTHING:


            default:
                throw new NoSuchElementException("There is no a type of Product for this");


        }

    }
}
