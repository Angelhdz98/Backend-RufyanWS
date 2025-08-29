package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Products.Entity.BodyClothing;
import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.*;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductCommand;
import com.example.PaginaWebRufyan.domain.port.out.ImageMapper;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class ProductDomainFactory {

    public static ProductDomain createProduct(ProductSpecs productSpecs, ProductDomainDetails productDetails){

        Set<ImageDomain> imageDomains = ImageProcessor.processImages(productSpecs.productImages().stream().toList(), productSpecs.name());

       return switch ( productSpecs.productTypeEnum()){
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



                yield new PaintingDomain(0L,productSpecs.name(),
                        new PaintingStockManager(paintingStock.availableCopies(),paintingStock.copiesMade(),paintingStock.isOriginalAvailable()),
                        new PaintingPriceManager(paintingPricing.pricePerCopy(),paintingPricing.pricePerOriginal()), imageDomains, paintingDetails,
                        productSpecs.productTypeEnum(),productSpecs.description(),productSpecs.isFavorite());
            }
            case CUP -> null;
            case CLOTHING ->  {

                if(!(productDetails instanceof BodyClothingDomainDetails bodyClothingDomainDetails)){
                    throw new IllegalArgumentException("No tiene los detalles necesarios:  " + Arrays.toString(BodyClothingStockDTO.class.getDeclaredFields()));
                }

                if(!(productSpecs.productStock() instanceof BodyClothingStockManager bodyClothingStock)){
                    throw new IllegalArgumentException("No tiene los datos necesarios para el stock"+ Arrays.toString(BodyClothingStockDTO.class.getDeclaredFields()));
                }

                if(!(productSpecs.productPricing() instanceof SinglePriceManager singlePriceManager)){
                    throw new IllegalArgumentException("El producto no tiene la información necesaria declarar sus precios: "+ Arrays.toString(BodyClothingStockDTO.class.getDeclaredFields()));
                }
                yield new BodyClothingDomain(0L,
                        productSpecs.name(),
                        new  BodyClothingStockManager(bodyClothingStock.getStockPerSize()),
                        new SinglePriceManager(singlePriceManager.getPrice()),
                        imageDomains,
                        bodyClothingDomainDetails,
                        productSpecs.productTypeEnum(),
                        productSpecs.description(),productSpecs.isFavorite());

            }
            case PRINT -> null;
            default -> throw new IllegalArgumentException("No hay ningun tipo de producto llamado así las opciones son "+ Arrays.toString(ProductTypeEnum.values()) );
        };



    }

    public static ProductDomain createProduct(Product product){
        return switch (product.getProductTypeEnum()){
            case PAINTING -> {
                Painting paintingEntity= (Painting) product;
                
                PaintingPriceManagerPersist paintingPriceManagerPersist = (PaintingPriceManagerPersist) product.getPriceManagerPersist();
                
                OriginalStockAdapter originalStockAdapter =(OriginalStockAdapter) product.getStockManager();
                


                yield new PaintingDomain(product.getId(),
                        product.getName() ,new PaintingStockManager(originalStockAdapter.getStockCopies(),
                        originalStockAdapter.getCopiesMade(),
                        originalStockAdapter.getIsOriginalAvailable()),new PaintingPriceManager(paintingPriceManagerPersist.getPricePerCopy(), paintingPriceManagerPersist.getPricePerOriginal()),product.getImage().stream().map(ImageMapper::toDomain).collect(Collectors.toSet()), new PaintingDomainDetails(paintingEntity.getAlturaCm() ,paintingEntity.getLargoCm(),paintingEntity.getMedium(),paintingEntity.getSupportMaterial(),paintingEntity.getCreationDate()), product.getProductTypeEnum(), product.getDescription(),product.getIsFavorite());
            }
            case CUP -> null;
             case CLOTHING ->  {
                 BodyClothing bodyClothing = (BodyClothing) product;
                 var productDetails = new BodyClothingDomainDetails(bodyClothing.getClothingMaterial(),((BodyClothing) product).getBodyClotheType(),bodyClothing.getPrintingTecnique());

                 ClothingStockAdapter clothingStock = (ClothingStockAdapter) product.getStockManager();

                 SinglePriceManagerPersist priceManager = (SinglePriceManagerPersist)  product.getPriceManagerPersist();


                yield new BodyClothingDomain(0L, bodyClothing.getName(),new  BodyClothingStockManager(clothingStock.getStockPerSize()), new SinglePriceManager(priceManager.getPrice()),  bodyClothing.getImage().stream().map(ImageMapper::toDomain).collect(Collectors.toSet()), productDetails,product.getProductTypeEnum(), bodyClothing.getDescription(), bodyClothing.getIsFavorite() );

            }
            case PRINT -> null;
        };

    }

    public static ProductDomain updateProduct(UpdateProductCommand command){

        Set<ImageDomain> imageDomains = ImageProcessor.processImages(command.productSpecs().productImages().stream().toList(), command.productSpecs().name());

        imageDomains.addAll(command.updatedImages());

        return switch ( command.productSpecs().productTypeEnum()){
            case PAINTING ->   {
                if(!(command.productDomainDetails() instanceof PaintingDomainDetails paintingDetails)){
                    throw new IllegalArgumentException("No tiene los detalles necesarios:  " + Arrays.toString(PaintingDomainDetails.class.getDeclaredFields()));
                }

                if(!(command.productSpecs().productStock() instanceof PaintingStockDTO paintingStock)){
                    throw new IllegalArgumentException("No tiene los datos necesarios para el stock"+ Arrays.toString(PaintingStockDTO.class.getDeclaredFields()));
                }

                if(!(command.productSpecs().productPricing() instanceof PaintingPricingDTO paintingPricing)){
                    throw new IllegalArgumentException("La obra no tiene la información necesaria declarar sus precios: "+ Arrays.toString(PaintingStockDTO.class.getDeclaredFields()));
                }



                yield new PaintingDomain(command.userId(),command.productSpecs().name(),
                        new PaintingStockManager(paintingStock.availableCopies(),paintingStock.copiesMade(),paintingStock.isOriginalAvailable()),
                        new PaintingPriceManager(paintingPricing.pricePerCopy(),paintingPricing.pricePerOriginal()), imageDomains, paintingDetails,
                        command.productSpecs().productTypeEnum(),command.productSpecs().description(),command.productSpecs().isFavorite());
            }
            case CUP -> null;
            case CLOTHING ->  {

                if(!(command.productDomainDetails() instanceof BodyClothingDomainDetails bodyClothingDomainDetails)){
                    throw new IllegalArgumentException("No tiene los detalles necesarios:  " + Arrays.toString(BodyClothingStockDTO.class.getDeclaredFields()));
                }

                if(!(command.productSpecs().productStock() instanceof BodyClothingStockManager bodyClothingStock)){
                    throw new IllegalArgumentException("No tiene los datos necesarios para el stock"+ Arrays.toString(BodyClothingStockDTO.class.getDeclaredFields()));
                }

                if(!(command.productSpecs().productPricing() instanceof SinglePriceManager singlePriceManager)){
                    throw new IllegalArgumentException("El producto no tiene la información necesaria declarar sus precios: "+ Arrays.toString(BodyClothingStockDTO.class.getDeclaredFields()));
                }
                yield new BodyClothingDomain(0L, command.productSpecs().name(),new  BodyClothingStockManager(bodyClothingStock.getStockPerSize()), new SinglePriceManager(singlePriceManager.getPrice()), imageDomains, bodyClothingDomainDetails,command.productSpecs().productTypeEnum(), command.productSpecs().description(), command.productSpecs().isFavorite() );

            }
            case PRINT -> null;
            default -> throw new IllegalArgumentException("No hay ningun tipo de producto llamado así las opciones son "+ Arrays.toString(ProductTypeEnum.values()) );
        };


    }

}








