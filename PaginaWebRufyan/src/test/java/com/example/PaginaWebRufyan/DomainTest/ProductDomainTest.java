package com.example.PaginaWebRufyan.DomainTest;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.domain.model.*;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ProductDomainTest {



    @Test
    @DisplayName("Test para crear los un painting usando  la clase ProductFactory")
    public void shouldCreatePaintingProduct(){
        // productSpecs
        // productDomainDetails

        ProductTypeEnum paintingType = ProductTypeEnum.PAINTING;

        PaintingStockDTO paintingStockDTO = new PaintingStockDTO(true,5,10);
        PaintingPricingDTO paintingPricingDTO = new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE);

        ProductSpecs productSpecs= new ProductSpecs("Titulo obra", "descripción x", Set.of(),paintingStockDTO, paintingPricingDTO, paintingType, true );


        Assertions.assertThat(ProductDomainFactory.createProduct(productSpecs,new PaintingDomainDetails())).isInstanceOf(PaintingDomain.class);

    }
    @Test
    @DisplayName("Test para crear los un painting usando  la clase ProductFactory")
    public void shouldThrowExceptionCreatingPaintingProductForDetails(){
        // productSpecs
        // productDomainDetails

        ProductTypeEnum paintingType = ProductTypeEnum.PAINTING;

        PaintingStockDTO paintingStockDTO = new PaintingStockDTO(true,5,10);
        PaintingPricingDTO paintingPricingDTO = new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE);

        ProductSpecs productSpecs= new ProductSpecs("Titulo obra", "descripción x", Set.of(),paintingStockDTO, paintingPricingDTO, paintingType, true );


        Assertions.assertThat(ProductDomainFactory.createProduct(productSpecs,new PaintingDomainDetails())).isInstanceOf(PaintingDomain.class);

    }


    @Test
    @DisplayName("Test para crear los un painting usando  la clase ProductFactory")
    public void shouldCreateBodyClothingProduct(){
        // productSpecs
        // productDomainDetails

        ProductTypeEnum BodyClothingType = ProductTypeEnum.CLOTHING;

        Map<ClothingSizeEnum,Integer> defaultClothingStock = new HashMap<>();

        Arrays.stream(ClothingSizeEnum.values()).forEach((clothingSizeEnum)->{
            defaultClothingStock.put(clothingSizeEnum,5);
        });

        BodyClothingStockDTO bodyClothingStockDTO = new BodyClothingStockDTO(defaultClothingStock);

        SinglePricingDTO singlePricingDTO =  new SinglePricingDTO(new BigDecimal("350"));



        ProductSpecs productSpecs= new ProductSpecs("Titulo obra", "descripción x", Set.of(),bodyClothingStockDTO, singlePricingDTO , BodyClothingType, true );


        Assertions.assertThat(ProductDomainFactory.createProduct(productSpecs,new BodyClothingDomainDetails())).isInstanceOf(BodyClothingDomain.class);

    }
}




















