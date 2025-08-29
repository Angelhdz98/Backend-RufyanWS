package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.adapter.out.persistence.BodyClothingItemDetailsAdapter;
import com.example.PaginaWebRufyan.adapter.out.persistence.CartItemDetailsAdapter;
import com.example.PaginaWebRufyan.adapter.out.persistence.PaintingItemDetailsAdapter;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;

public class CartItemDetailsFactory {

    public static CartItemDetails createCartItemDetails(ProductDomain product, CartItemDetailsCommand itemDetailsCommand){

return        switch (product.getProductType()){
            case PAINTING -> {
                PaintingItemDetailsCommand paintingItemDetailsCommand = (PaintingItemDetailsCommand) itemDetailsCommand;

                yield new PaintingItemDetails(paintingItemDetailsCommand.getQuantity(), paintingItemDetailsCommand.getIsOriginalSelected());

            }
            case CLOTHING -> {
                BodyClothingItemDetailsCommand bodyClothingItemDetailsCommand = (BodyClothingItemDetailsCommand) itemDetailsCommand;
                yield new ClothingItemDetails(bodyClothingItemDetailsCommand.getQuantity(), bodyClothingItemDetailsCommand.getColor(), bodyClothingItemDetailsCommand.getClothingSize());
            }


            case PRINT,CUP -> null;
        };


    }
    public static CartItemDetails createCartItemDetails(ProductDomain product, CartItemDetailsAdapter cartItemDetailsAdapter){

        return   switch (product.getProductType()){
            case PAINTING -> {

                PaintingItemDetailsAdapter paintingItemDetailsAdapter = (PaintingItemDetailsAdapter) cartItemDetailsAdapter;

                yield new PaintingItemDetails(paintingItemDetailsAdapter.getQuantity(), paintingItemDetailsAdapter.getIsOriginalSelected());

            }
            case CLOTHING -> {

                BodyClothingItemDetailsAdapter bodyClothingItemDetailsAdapter = (BodyClothingItemDetailsAdapter) cartItemDetailsAdapter;

                yield new ClothingItemDetails(bodyClothingItemDetailsAdapter.getQuantity(), bodyClothingItemDetailsAdapter.getClothingColorEnum(), bodyClothingItemDetailsAdapter.getClothingSizeEnum());
            }


            case PRINT,CUP -> null;
        };


    }

}
