package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.persistence.BodyClothingItemDetailsAdapter;
import com.example.PaginaWebRufyan.adapter.out.persistence.CartItemDetailsAdapter;
import com.example.PaginaWebRufyan.adapter.out.persistence.PaintingItemDetailsAdapter;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ClothingItemDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingItemDetails;

public class CartItemDetailsMapper {
    public static CartItemDetailsAdapter toEntity(CartItemDetails cartItemDetailsDomain, ProductTypeEnum productTypeEnum){
 return switch (productTypeEnum){
     case PAINTING -> {
        PaintingItemDetails paintingItemDetails = (PaintingItemDetails) cartItemDetailsDomain;

         yield new PaintingItemDetailsAdapter(paintingItemDetails.getItemQuantity().getQuantity(),paintingItemDetails.getIsOriginalSelected().getIsOriginalSelected());
     }
     case CLOTHING -> {
         ClothingItemDetails clothingItemDetails = (ClothingItemDetails) cartItemDetailsDomain;
         yield new BodyClothingItemDetailsAdapter(clothingItemDetails.getItemQuantity().getQuantity(), clothingItemDetails.getClothingSizeEnum(), clothingItemDetails.getColor());
     }
     case CUP -> null;
      case PRINT -> null;
 };
    }

    public static CartItemDetails toDomain(CartItemDetailsAdapter cartItemDetailsAdapter, ProductTypeEnum productTypeEnum){

       return switch (productTypeEnum){
           case PAINTING -> {
               PaintingItemDetailsAdapter paintingItemDetailsAdapter = (PaintingItemDetailsAdapter) cartItemDetailsAdapter;
               yield new PaintingItemDetails(paintingItemDetailsAdapter.getQuantity(), paintingItemDetailsAdapter.getIsOriginalSelected());
           }
           case CLOTHING -> {
               BodyClothingItemDetailsAdapter bodyClothingItemDetailsAdapter = (BodyClothingItemDetailsAdapter) cartItemDetailsAdapter;

               yield new ClothingItemDetails( bodyClothingItemDetailsAdapter.getQuantity(),bodyClothingItemDetailsAdapter.getClothingColorEnum(),bodyClothingItemDetailsAdapter.getClothingSizeEnum());

           }
           case CUP -> null;
           case PRINT -> null;
       };

    }
}
