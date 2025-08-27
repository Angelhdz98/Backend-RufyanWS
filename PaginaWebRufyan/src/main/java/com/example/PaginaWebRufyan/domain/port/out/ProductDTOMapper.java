package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.adapter.out.SinglePriceManager;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;

public class ProductDTOMapper {

    public static ProductDTO toDTO(ProductDomain productDomain){
      return  switch (productDomain.getProductType()){
            case PAINTING -> {
                PaintingStockManager paintingStockManager = (PaintingStockManager) productDomain.getStockManagerBase();
               PaintingPriceManager paintingPriceManager = (PaintingPriceManager) productDomain.getPriceManagerBase();
                yield new ProductDTO(productDomain.getName(),productDomain.getImages(), new PaintingStockDTO(paintingStockManager.getIsOriginalAvailable(), paintingStockManager.getStockCopies(), paintingStockManager.getCopiesMade()), new PaintingPricingDTO(paintingPriceManager.getPricePerOriginal(),paintingPriceManager.getPricePerCopy()),productDomain.getProductType(),productDomain.getProductDetails());
            }
            case CLOTHING ->{
                BodyClothingStockManager bodyClothingStockManager = (BodyClothingStockManager) productDomain.getStockManagerBase();
                SinglePriceManager singlePriceManager = (SinglePriceManager) productDomain.getPriceManagerBase();

                yield new ProductDTO(productDomain.getName(),productDomain.getImages(),new BodyClothingStockDTO(bodyClothingStockManager.getStockPerSize()), new SinglePricingDTO(singlePriceManager.getPrice()),productDomain.getProductType(),productDomain.getProductDetails());
            }
            case CUP, PRINT -> null;
            default -> throw new IllegalStateException("Unexpected value: " + productDomain.getProductType());
        };
    }
}
