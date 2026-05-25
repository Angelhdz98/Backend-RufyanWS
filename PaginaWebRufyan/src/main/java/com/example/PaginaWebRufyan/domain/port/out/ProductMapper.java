package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Image.Image;
import com.example.PaginaWebRufyan.Products.Entity.BodyClothing;
import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.adapter.out.*;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.*;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BodyClothingStockManager;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingPriceManager;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingStockManager;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class ProductMapper {
    private final ImageMapper imageMapper;

    public ProductMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }


    public ProductDomain toDomain(Product product){
        return switch (product.getProductTypeEnum()) {
            case PAINTING -> {
                OriginalStockManager paintingStock = (OriginalStockManager) product.getStockManager();

                PaintingPriceManagerPersist priceManager = (PaintingPriceManagerPersist) product.getPriceManagerPersist();

                Painting painting = (Painting) product;

               yield new PaintingDomain(painting.getId(),painting.getName(), new PaintingStockManager(paintingStock.getStockCopies(), paintingStock.getCopiesMade(), paintingStock.getIsOriginalAvailable()), new PaintingPriceManager(priceManager.getPricePerCopy(),priceManager.getPricePerOriginal()), painting.getImages().stream().map(imageMapper::toDomain).collect(Collectors.toSet()), new PaintingDomainDetails(painting.getAlturaCm(), painting.getLargoCm(), painting.getMedium(), painting.getSupportMaterial(),painting.getCreationDate()),painting.getProductTypeEnum(), painting.getDescription(),painting.getIsFavorite());
            }
            case CLOTHING -> {

                BodyClothing bodyClothing = (BodyClothing) product;

                ClothingStockManager clothingStock = (ClothingStockManager) product.getStockManager();

                SinglePriceManagerPersist singlePriceManagerPersist = (SinglePriceManagerPersist) product.getPriceManagerPersist();

                yield new BodyClothingDomain(bodyClothing.getId(), bodyClothing.getName() ,new BodyClothingStockManager(clothingStock.getStockPerSize()), new SinglePriceManager(singlePriceManagerPersist.getPrice()) , bodyClothing.getImages().stream().map(imageMapper::toDomain).collect(Collectors.toSet()), new BodyClothingDomainDetails(bodyClothing.getClothingMaterial(),bodyClothing.getBodyClotheType(),bodyClothing.getPrintingTecnique()), bodyClothing.getProductTypeEnum(), bodyClothing.getDescription(),bodyClothing.getIsFavorite());


            }
            case CUP, PRINT -> null;

        };
    }
    public  Product toEntity(ProductDomain productDomain){

        return switch (productDomain.getProductType()){
            case PAINTING -> {

                PaintingPriceManager priceManager = (PaintingPriceManager) productDomain.getPriceManagerBase();

                PaintingStockManager paintingStockManager = (PaintingStockManager) productDomain.getStockManagerBase();

                PaintingDomainDetails paintingDomainDetails = (PaintingDomainDetails) productDomain.getProductDetails();

                Painting painting = new Painting(productDomain.getId(),
                        productDomain.getName(),
                        productDomain.getDescription(),
                        ((PaintingDomainDetails) productDomain.getProductDetails()).getCreationDate(),
                        new PaintingPriceManagerPersist(priceManager.getId(),priceManager.getPricePerCopy(), priceManager.getPricePerOriginal()),
                        new OriginalStockManager(paintingStockManager.getStockCopies(), paintingStockManager.getCopiesMade(),paintingStockManager.getIsOriginalAvailable()),
                        paintingStockManager.isAvailable(),
                        productDomain.getIsFavorite(),
                        new java.util.HashSet<>(),
                        productDomain.getProductType(),
                        paintingDomainDetails.getAlturaCm(),
                        paintingDomainDetails.getLargoCm(),
                        paintingDomainDetails.getMedium(),
                        paintingDomainDetails.getSupportMaterial());

                Set<Image> images = productDomain.getImages().stream().map((ImageDomain imageDomain)->{
                    return imageMapper.toEntity(imageDomain, painting);
                }).collect(Collectors.toSet());

                painting.setImages(images);

                yield painting;
            }
            case CLOTHING -> {
                SinglePriceManager priceManager = (SinglePriceManager) productDomain.getPriceManagerBase();
                BodyClothingStockManager bodyClothingStockManager = (BodyClothingStockManager) productDomain.getStockManagerBase();
                BodyClothingDomainDetails bodyClothingDomainDetails = (BodyClothingDomainDetails) productDomain.getProductDetails();

                BodyClothing bodyClothing = new BodyClothing(productDomain.getId(),
                        productDomain.getName(),
                        productDomain.getDescription(),
                        LocalDate.now(),
                        new SinglePriceManagerPersist(priceManager.getId(),priceManager.getPrice()),
                        new ClothingStockManager(bodyClothingStockManager.getStockPerSize()),
                        bodyClothingStockManager.isAvailable(),
                        productDomain.getIsFavorite(),
                        new java.util.HashSet<>(),
                        productDomain.getProductType(),bodyClothingDomainDetails.getMaterial().toString(),
                        bodyClothingDomainDetails.getPrintingTechnique(),
                        bodyClothingDomainDetails.getType());

                Set<Image> images = productDomain.getImages().stream().map((ImageDomain imageDomain)->{
                    return imageMapper.toEntity(imageDomain, bodyClothing);
                }).collect(Collectors.toSet());

                bodyClothing.setImages(images);

                yield bodyClothing;
            }
            case CUP -> null;

            case PRINT -> null;
        };
    }

}
