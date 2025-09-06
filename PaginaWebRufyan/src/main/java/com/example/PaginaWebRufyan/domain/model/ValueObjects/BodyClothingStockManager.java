package com.example.PaginaWebRufyan.domain.model.ValueObjects;


import com.example.PaginaWebRufyan.Exceptions.NoStockException;
import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class BodyClothingStockManager extends StockManagerBase {
    private final Map<ClothingSizeEnum, Integer> stockPerSize;
    public final static Integer DEFAULT_STOCK_PER_SIZE = 5;



    public BodyClothingStockManager(Map<ClothingSizeEnum, Integer> stockPerSize) {
        this.stockPerSize = stockPerSize;
    }

    public BodyClothingStockManager() {
        Map<ClothingSizeEnum, Integer> stock = new HashMap<>();
        Arrays.stream(ClothingSizeEnum.values()).forEach(clothingSizeEnum -> {
            stock.put(clothingSizeEnum,DEFAULT_STOCK_PER_SIZE);
        });
        this.stockPerSize = stock ;
    }


    @Override
    public Map<String, Integer> getStock() {
        Map<String, Integer> responseMap = new HashMap<>();
        stockPerSize.forEach((ClothingSizeEnum key,Integer value)-> responseMap.put(key.toString(),value));
        return responseMap;
    }

    @Override
    public void decreaseStock(ProductDomain productDomain, CartItemDetails details) {
            if(details instanceof ClothingItemDetails clothingItemDetails)
            {
               if(!stockPerSize.containsKey(clothingItemDetails.getClothingSizeEnum()) ||
                       stockPerSize.get(clothingItemDetails.getClothingSizeEnum())<= 0){
                   throw new NoStockException("No hay existencia de la talla: "+ clothingItemDetails.getClothingSizeEnum().toString());
               } else if ((stockPerSize.get(clothingItemDetails.getClothingSizeEnum())- clothingItemDetails.getItemQuantity().getQuantity())<0) {
                   throw  new NoStockException("No se cuenta con el stock suficiente; nuestro stock: " + stockPerSize.get(clothingItemDetails.getClothingSizeEnum())+ " cantidad en el pedido: "+ clothingItemDetails.getItemQuantity().getQuantity());
               }
               stockPerSize.put(clothingItemDetails.getClothingSizeEnum(), stockPerSize.get(clothingItemDetails.getClothingSizeEnum())-clothingItemDetails.getItemQuantity().getQuantity());



            }
            throw new RuntimeException("Tipo equivocado de CartItemDetails, se necesita una instancia de ClotingItemDetails");



    }

    @Override
    public void increaseStock(ProductDomain productDomain, CartItemDetails details) {
        if(details instanceof ClothingItemDetails clothingItemDetails){
            stockPerSize.put(clothingItemDetails.getClothingSizeEnum(), stockPerSize.get(clothingItemDetails.getClothingSizeEnum()) +clothingItemDetails.getItemQuantity().getQuantity() );
        }

        throw new RuntimeException("Tipo equivocado de CartItemDetails, se necesita una instancia de ClotingItemDetails");


    }

    @Override
    public Boolean isAvailable() {
        for (ClothingSizeEnum clothingSizeEnum : stockPerSize.keySet()) {
            if(stockPerSize.get(clothingSizeEnum)>0)
                return true;
        }
        return false;

    }
}










