package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Entity.BodyClothing;
import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.adapter.out.*;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.*;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BodyClothingStockManager;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingPriceManager;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingStockManager;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDomain toDomain(Product product){
        return switch (product.getProductTypeEnum()) {
            case PAINTING -> {
                OriginalStockAdapter paintingStock = (OriginalStockAdapter) product.getStockManager();

                PaintingPriceManagerPersist priceManager = (PaintingPriceManagerPersist) product.getPriceManagerPersist();

                Painting painting = (Painting) product;

               yield new PaintingDomain(product.getId(),product.getName(), new PaintingStockManager(paintingStock.getStockCopies(), paintingStock.getCopiesMade(), paintingStock.getIsOriginalAvailable()), new PaintingPriceManager(priceManager.getPricePerCopy(),priceManager.getPricePerOriginal()), product.getImage().stream().map(ImageMapper::toDomain).collect(Collectors.toSet()), new PaintingDomainDetails(painting.getAlturaCm(), painting.getLargoCm(), painting.getMedium(), painting.getSupportMaterial(),painting.getCreationDate()),product.getProductTypeEnum(), product.getDescription(),product.getIsFavorite());
            }
            case CLOTHING -> {

                BodyClothing bodyClothing = (BodyClothing) product;

                ClothingStockAdapter clothingStock = (ClothingStockAdapter) product.getStockManager();

                SinglePriceManagerPersist singlePriceManagerPersist = (SinglePriceManagerPersist) product.getPriceManagerPersist();

                yield new BodyClothingDomain(bodyClothing.getId(), bodyClothing.getName() ,new BodyClothingStockManager(clothingStock.getStockPerSize()), new SinglePriceManager(singlePriceManagerPersist.getPrice()) , bodyClothing.getImage().stream().map(ImageMapper::toDomain).collect(Collectors.toSet()), new BodyClothingDomainDetails(bodyClothing.getClothingMaterial(),bodyClothing.getBodyClotheType(),bodyClothing.getPrintingTecnique()), bodyClothing.getProductTypeEnum(), bodyClothing.getDescription(),bodyClothing.getIsFavorite());


            }
            case CUP, PRINT -> null;

        };
    }
    public static Product toEntity(ProductDomain productDomain){

        return switch (productDomain.getProductType()){
            case PAINTING -> {

                PaintingPriceManager priceManager = (PaintingPriceManager) productDomain.getPriceManagerBase();

                PaintingStockManager paintingStockManager = (PaintingStockManager) productDomain.getStockManagerBase();

                PaintingDomainDetails paintingDomainDetails = (PaintingDomainDetails) productDomain.getProductDetails();

                Set<Image> images = productDomain.getImages().stream().map(ImageMapper::toEntity).collect(Collectors.toSet());

                yield new Painting(productDomain.getId(),productDomain.getName(),productDomain.getDescription(), LocalDate.now(), new PaintingPriceManagerPersist(priceManager.getPricePerCopy(), priceManager.getPricePerOriginal()), new OriginalStockAdapter(paintingStockManager.getStockCopies(), paintingStockManager.getCopiesMade(),paintingStockManager.getIsOriginalAvailable()),paintingStockManager.isAvailable(),productDomain.getIsFavorite(), images, productDomain.getProductType(), paintingDomainDetails.getAlturaCm(), paintingDomainDetails.getLargoCm(), paintingDomainDetails.getMedium(), paintingDomainDetails.getSupportMaterial());
            }
            case CUP -> null;
            case CLOTHING -> null;
            case PRINT -> null;
        };
    }

}
