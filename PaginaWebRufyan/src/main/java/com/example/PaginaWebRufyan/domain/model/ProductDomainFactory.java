package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Products.Entity.BodyClothing;
import com.example.PaginaWebRufyan.Products.Entity.Painting;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.*;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductCommand;
import com.example.PaginaWebRufyan.domain.port.out.ImageMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductDomainFactory {

     private  final ImageProcessor imageProcessor;

     public ProductDomainFactory(ImageProcessor imageProcessor){
         this.imageProcessor = imageProcessor;
     }

    public  ProductDomain createProduct(ProductSpecs productSpecs,
                                        ProductDomainDetails productDetails, Set<MultipartFile> images){

        Set<ImageDomain> imageDomains = imageProcessor.processImages(images.stream().toList(), productSpecs.name());

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



                yield new PaintingDomain(null,productSpecs.name(),
                        new PaintingStockManager(paintingStock.getAvailableCopies(),paintingStock.getCopiesMade(),paintingStock.getIsOriginalAvailable()),
                        new PaintingPriceManager(paintingPricing.getPricePerCopy(),paintingPricing.getPricePerOriginal()), imageDomains, paintingDetails,
                        productSpecs.productTypeEnum(),productSpecs.description(),productSpecs.isFavorite());
            }
            case CLOTHING ->  {

               if(!(productDetails instanceof BodyClothingDomainDetails bodyClothingDomainDetails)){
                   throw new IllegalArgumentException("No tiene los detalles necesarios:  " + Arrays.toString(BodyClothingStockDTO.class.getDeclaredFields()));
               }

               if(!(productSpecs.productStock() instanceof BodyClothingStockDTO bodyClothingStockDTO)){
                   throw new IllegalArgumentException("No tiene los datos necesarios para el stock"+ Arrays.toString(BodyClothingStockDTO.class.getDeclaredFields()));
               }

               if(!(productSpecs.productPricing() instanceof SinglePricingDTO singlePriceDTO)){
                   throw new IllegalArgumentException("El producto no tiene la información necesaria declarar sus precios: "+ Arrays.toString(BodyClothingStockDTO.class.getDeclaredFields()));
               }
               yield new BodyClothingDomain(null,
                       productSpecs.name(),
                       new  BodyClothingStockManager(bodyClothingStockDTO.stockPerSize()),
                       new SinglePriceManager(singlePriceDTO.price()),
                       imageDomains,
                       bodyClothingDomainDetails,
                       productSpecs.productTypeEnum(),
                       productSpecs.description(),
                       productSpecs.isFavorite());

           }
            case CUP, PRINT -> null;

           default -> throw new IllegalArgumentException("No hay ningun tipo de producto llamado así las opciones son "+ Arrays.toString(ProductTypeEnum.values()) );
        };



    }

    public  ProductDomain createProduct(Product product){
        return switch (product.getProductTypeEnum()){
            case PAINTING -> {
                Painting paintingEntity= (Painting) product;
                
                PaintingPriceManagerPersist paintingPriceManagerPersist = (PaintingPriceManagerPersist) product.getPriceManagerPersist();
                
                OriginalStockManager originalStockManager =(OriginalStockManager) product.getStockManager();
                


                yield new PaintingDomain(product.getId(),
                        product.getName() ,new PaintingStockManager(originalStockManager.getStockCopies(),
                        originalStockManager.getCopiesMade(),
                        originalStockManager.getIsOriginalAvailable()),new PaintingPriceManager(paintingPriceManagerPersist.getPricePerCopy(), paintingPriceManagerPersist.getPricePerOriginal()),product.getImage().stream().map(ImageMapper::toDomain).collect(Collectors.toSet()), new PaintingDomainDetails(paintingEntity.getAlturaCm() ,paintingEntity.getLargoCm(),paintingEntity.getMedium(),paintingEntity.getSupportMaterial(),paintingEntity.getCreationDate()), product.getProductTypeEnum(), product.getDescription(),product.getIsFavorite());
            }
            case CUP -> null;
             case CLOTHING ->  {
                 BodyClothing bodyClothing = (BodyClothing) product;
                 var productDetails = new BodyClothingDomainDetails(bodyClothing.getClothingMaterial(),((BodyClothing) product).getBodyClotheType(),bodyClothing.getPrintingTecnique());

                 ClothingStockManager clothingStock = (ClothingStockManager) product.getStockManager();

                 SinglePriceManagerPersist priceManager = (SinglePriceManagerPersist)  product.getPriceManagerPersist();


                yield new BodyClothingDomain(0L, bodyClothing.getName(),new  BodyClothingStockManager(clothingStock.getStockPerSize()), new SinglePriceManager(priceManager.getPrice()),  bodyClothing.getImage().stream().map(ImageMapper::toDomain).collect(Collectors.toSet()), productDetails,product.getProductTypeEnum(), bodyClothing.getDescription(), bodyClothing.getIsFavorite() );

            }
            case PRINT -> null;
        };

    }

    public  ProductDomain updateProduct(UpdateProductCommand command, Set<MultipartFile> images){

        Set<ImageDomain> imageDomains = imageProcessor.processImages(images.stream().toList(), command.productSpecs().name());

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



                yield new PaintingDomain(command.productId(),command.productSpecs().name(),
                        new PaintingStockManager(paintingStock.getAvailableCopies(),paintingStock.getCopiesMade(),paintingStock.getIsOriginalAvailable()),
                        new PaintingPriceManager(paintingPricing.getPricePerCopy(),paintingPricing.getPricePerOriginal()), imageDomains, paintingDetails,
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








