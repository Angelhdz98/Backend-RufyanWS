package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.adapter.out.OriginalStockAdapter;
import com.example.PaginaWebRufyan.adapter.out.PaintingPriceManagerPersist;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.PaintingDomain;
import com.example.PaginaWebRufyan.domain.model.PaintingDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingPriceManager;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PaintingStockManager;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductFactoryMapper {
    public static ProductDomain toDomain(Product product) {
       return switch (product.getProductTypeEnum()) {
            case PAINTING -> {
                    Painting paintingEntity= (Painting) product;

                    PaintingPriceManagerPersist paintingPriceManagerPersist = (PaintingPriceManagerPersist) product.getPriceManagerPersist();

                    OriginalStockAdapter originalStockAdapter =(OriginalStockAdapter) product.getStockManager();

                Set<ImageDomain> images = product.getImage().stream().map(ImageMapper::toDomain).collect(Collectors.toSet());


                yield new PaintingDomain(product.getId(),
                            product.getName(),new PaintingStockManager(originalStockAdapter.getStockCopies(),
                            originalStockAdapter.getCopiesMade(),
                            originalStockAdapter.getIsOriginalAvailable()),new PaintingPriceManager(paintingPriceManagerPersist.getPricePerCopy(), paintingPriceManagerPersist.getPricePerOriginal()), images ,new PaintingDomainDetails(paintingEntity.getAlturaCm(),paintingEntity.getLargoCm(),paintingEntity.getMedium(),paintingEntity.getSupportMaterial(),paintingEntity.getCreationDate()), product.getProductTypeEnum(), product.getDescription(), product.getIsFavorite());
                }

           case PRINT,CLOTHING, CUP -> null;
        };

    }
}
