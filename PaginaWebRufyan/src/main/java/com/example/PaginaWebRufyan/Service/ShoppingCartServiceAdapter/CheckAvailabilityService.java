package com.example.PaginaWebRufyan.Service.ShoppingCartServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import org.springframework.stereotype.Service;

@Service
public class CheckAvailabilityService {

    public boolean isStockAvailable(CartItemDomain cartItemDomain){

        switch (cartItemDomain.getProduct().getProductType()){
            case PAINTING -> {
                PaintingStockManager stockManager =
                        (PaintingStockManager)
                        cartItemDomain.getProduct().getStockManagerBase();

                PaintingItemDetails paintingDetails =
                        (PaintingItemDetails) cartItemDomain.getDetails();
                if(paintingDetails.getIsOriginalSelected() && !stockManager.getIsOriginalAvailable()){
                    return false;
                }

                return stockManager.getStockCopies() >= cartItemDomain.getDetails().getItemQuantity().getQuantity();
            }

            case CLOTHING ->{

 BodyClothingStockManager clothingStockManager = (BodyClothingStockManager) cartItemDomain.getProduct().getStockManagerBase();
ClothingItemDetails clothingItemDetails =(ClothingItemDetails)
        cartItemDomain.getDetails();
                Integer stockPerSize = clothingStockManager.getStock().get(clothingItemDetails.getClothingSizeEnum().toString());

                return stockPerSize>=clothingItemDetails.getItemQuantity().getQuantity();
            }
             default -> {

                SingleStockManager singleStockManager =
                        (SingleStockManager) cartItemDomain.getProduct().getStockManagerBase();
                return singleStockManager.getStockQuantity()>=cartItemDomain.getDetails().getItemQuantity().getQuantity();


            }

        }


    }

}
