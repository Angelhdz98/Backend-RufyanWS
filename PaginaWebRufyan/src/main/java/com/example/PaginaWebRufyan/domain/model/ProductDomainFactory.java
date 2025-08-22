package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProductDomainFactory {

    public ProductDomain createProduct(ProductSpecs productSpecs, ProductDomainDetails productDetails){
        return switch (productSpecs.productTypeEnum()){
            case PAINTING ->   {
               if(!(productDetails instanceof PaintingDomainDetails paintingDetails)){
                   throw new IllegalArgumentException("No tiene los detalles necesarios:  " + Arrays.toString(PaintingDomainDetails.class.getDeclaredFields()));
               }

               if(!(productSpecs.productStock() instanceof PaintingStockDTO paintingStock)){
                   throw new IllegalArgumentException("No tiene los datos necesarios para el stock"+ Arrays.toString(PaintingStockDTO.class.getDeclaredFields()));
               }

                if(!(productSpecs.productPricing() instanceof PaintingPricingDTO paintingPricing)){
                    throw new IllegalArgumentException("La obra no tiene la información necesaria declarar sus precios: "+ Arrays.toString(PaintingStockDTO.class.getDeclaredFields()));
                }


                yield new PaintingDomain(0L,productSpecs.name(),new PaintingStockManager(paintingStock.availableCopies(),paintingStock.copiesMade(),paintingStock.isOriginalAvailable()), new PaintingPriceManager(paintingPricing.pricePerCopy(),paintingPricing.pricePerOriginal()), productSpecs.productImages(), paintingDetails );
            }
            case CUP -> null;
            case CLOTHING -> null;
            case PRINT -> null;
            default -> throw new IllegalArgumentException("No hay ningun tipo de producto llamado así las opciones son "+ Arrays.toString(ProductTypeEnum.values()) );
        };

    }
}
