package com.example.PaginaWebRufyan.Products;

import com.example.PaginaWebRufyan.adapter.out.OriginalStockAdapter;
import com.example.PaginaWebRufyan.adapter.out.PaintingPriceManager;
import com.example.PaginaWebRufyan.Products.DTO.Product.ProductUpdateRegisterDTO;
import com.example.PaginaWebRufyan.Products.Enums.ProductsEnum;
import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.Products.Repository.PaintingRepository;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductFactory {
    @Autowired
    PaintingRepository paintingRepository;

    public static Product createProductFromRegister(ProductUpdateRegisterDTO productData){
        if (productData.getType()==null)productData.setType(ProductsEnum.PAINTING);
        switch (productData.getType()){
            case PAINTING :
                /*if(productData.getStock().containsKey("stockCopies") &&
                        productData.getStock().containsKey("isOriginalAvailable") &&
                        productData.getPriceManage().containsKey("pricePerCopy") &&
                        productData.getPriceManage().containsKey("pricePerOriginal"))

                 */
                Painting.checkConsistentData(productData);
                    int stockCopies =(int) productData.getStock().get("stockCopies");
                    Boolean isOriginalAvailable = (Boolean) productData.getStock().get("isOriginalAvailable");
                    int copiesMade = (int) productData.getStock().get("copiesMade");
                    BigDecimal pricePerCopy = (BigDecimal) productData.getPriceManage().get("pricePerCopy");
                    Boolean isInCart =(Boolean) productData.getStock().get("isInCart");
                    BigDecimal pricePerOriginal = (BigDecimal) productData.getPriceManage().get("pricePerOriginal");
                    return Painting.builder()
                            .name(productData.getName())
                            .description(productData.getDescription())
                            .creationDate(productData.getCreationDate())
                            .style(productData.getStyle())
                            .stockManager(new OriginalStockAdapter(stockCopies,copiesMade,isOriginalAvailable,isInCart))
                            .priceManager(new PaintingPriceManager(pricePerCopy,pricePerOriginal))
                            .isFavorite(productData.getIsFavorite())
                            //.category(productData.getProductsCategory())
                            .image(productData.getOldImages().stream().collect(Collectors.toSet()))
                            .favoriteOf(new HashSet<>(Set.of()))
                            .cartItems(new HashSet<>(Set.of()))
                            .orderItems(new HashSet<>(Set.of()))
                            .alturaCm(Integer.valueOf(productData.getAdditionalFeatures().get("alturaCm")))
                            .largoCm(Integer.valueOf(productData.getAdditionalFeatures().get("largoCm")))
                            .medium( productData.getAdditionalFeatures().get("medium"))
                            .supportMaterial(productData.getAdditionalFeatures().get("supportMaterial"))
                            .build();


            case BODYCLOTHING:


            default:
                throw new NoSuchElementException("There is no a type of Product for this");


        }

    }
}
