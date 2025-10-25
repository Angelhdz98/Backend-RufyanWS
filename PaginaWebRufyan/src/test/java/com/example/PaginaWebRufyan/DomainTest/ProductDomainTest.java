package com.example.PaginaWebRufyan.DomainTest;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.SinglePriceManager;
import com.example.PaginaWebRufyan.domain.model.*;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import static  org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ProductDomainTest {


    @InjectMocks
            ProductDomainFactory productDomainFactory;

    Set<MultipartFile> images = new HashSet<>();
    MultipartFile file1 = mock(MultipartFile.class);
    MultipartFile file2 = mock(MultipartFile.class);
    MultipartFile file3 = mock(MultipartFile.class);

    ImageDomain mockImage1 = mock(ImageDomain.class);
    ImageDomain mockImage2 = mock(ImageDomain.class);
    ImageDomain mockImage3 = mock(ImageDomain.class);

    Set<ImageDomain> imagesDomain = new HashSet<>();
    Set<ImageDomain> incompleteImagesSet = new HashSet<> ();


    @BeforeEach
    public  void setUp(){

        images.add(file1);
        images.add(file2);
        images.add(file3);

        imagesDomain.add(mockImage1);
        imagesDomain.add(mockImage2);
        imagesDomain.add(mockImage3);
        incompleteImagesSet.add(mockImage1);

        when(file1.getOriginalFilename()).thenReturn("night colors 1.");
        when(file1.getOriginalFilename()).thenReturn("night colors 2.");
        when(file1.getOriginalFilename()).thenReturn("night colors 3.");
        //


    }
    @Test
    @DisplayName("Test para evitar que se creen productos con menos de 2 imagen")
    public  void shouldReturnExceptionForEmptyImages(){
        ProductTypeEnum paintingType = ProductTypeEnum.PAINTING;



        PaintingStockDTO paintingStockDTO = new PaintingStockDTO(true,5,10);
        PaintingPricingDTO paintingPricingDTO = new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE);

        ProductSpecs productSpecs= new ProductSpecs("Titulo obra", "descripción x", Set.of(),paintingStockDTO, paintingPricingDTO, paintingType, true );

        ProductSpecs productSpecs2= new ProductSpecs("Titulo obra", "descripción x", Set.of(file1),paintingStockDTO, paintingPricingDTO, paintingType, true );
        try(MockedStatic<ImageProcessor> imageProcessorMocked= mockStatic(ImageProcessor.class)){
            imageProcessorMocked.when(()->ImageProcessor.processImages(any(), any())).thenReturn(incompleteImagesSet);

            assertThrows(IllegalArgumentException.class,()->productDomainFactory.createProduct(productSpecs, new PaintingDomainDetails()));

            assertThrows(IllegalArgumentException.class,()->ProductDomainFactory.createProduct(productSpecs2, new PaintingDomainDetails()));



        }




    }
    @Test
    @DisplayName("Test para evitar que se creen productos con nombre ivalido")
    public  void shouldReturnExceptionForInvalidName(){
        ProductTypeEnum paintingType = ProductTypeEnum.PAINTING;

        PaintingStockDTO paintingStockDTO = new PaintingStockDTO(true,5,10);
        PaintingPricingDTO paintingPricingDTO = new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE);

        ProductSpecs productSpecsWrongName= new ProductSpecs("Ti", "descripción x", images,paintingStockDTO, paintingPricingDTO, paintingType, true );

        try(MockedStatic<ImageProcessor> imageProcessorMocked= mockStatic(ImageProcessor.class)){
            imageProcessorMocked.when(()->ImageProcessor.processImages(any(), any())).thenReturn(imagesDomain);
            assertThrows(IllegalArgumentException.class,()->ProductDomainFactory.createProduct(productSpecsWrongName, new PaintingDomainDetails()));
        }







    }

    @Test
    @DisplayName("Test para crear los un painting usando  la clase ProductFactory")
    public void shouldCreatePaintingProduct() {
        // productSpecs
        // productDomainDetails

        ProductTypeEnum paintingType = ProductTypeEnum.PAINTING;


        PaintingStockDTO paintingStockDTO = new PaintingStockDTO(true, 5, 10);
        PaintingPricingDTO paintingPricingDTO = new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE);


        ProductSpecs productSpecs = new ProductSpecs("Titulo obra", "descripción x", images, paintingStockDTO, paintingPricingDTO, paintingType, true);

        try (MockedStatic<ImageProcessor> imageProcessorMocked = mockStatic(ImageProcessor.class)) {
            imageProcessorMocked.when(() -> ImageProcessor.processImages(any(), any())).thenReturn(imagesDomain);
            ProductDomain product = ProductDomainFactory.createProduct(productSpecs, new PaintingDomainDetails());

            Assertions.assertThat(product).isInstanceOf(PaintingDomain.class);

            Assertions.assertThat(product.getProductDetails()).isInstanceOf(PaintingDomainDetails.class);

            Assertions.assertThat(product.getStockManagerBase()).isInstanceOf(PaintingStockManager.class);

            Assertions.assertThat(product.getPriceManagerBase()).isInstanceOf(PaintingPriceManager.class);
        }

    }





    @Test
    @DisplayName("Test para arrojar una excepción creando una pintura por product details ProductFactory")
    public void shouldThrowExceptionCreatingPaintingProductForDetails(){
        // productSpecs
        // productDomainDetails

        ProductTypeEnum paintingType = ProductTypeEnum.PAINTING;

        PaintingStockDTO paintingStockDTO = new PaintingStockDTO(true,5,10);
        PaintingPricingDTO paintingPricingDTO = new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE, PaintingPriceManager.MIN_COPY_PRICE);

        ProductSpecs productSpecs= new ProductSpecs("Titulo obra", "descripción x", images, paintingStockDTO, paintingPricingDTO, paintingType, true );
        try(MockedStatic<ImageProcessor> imageProcessorMocked= mockStatic(ImageProcessor.class)){
            imageProcessorMocked.when(()->ImageProcessor.processImages(any(), any())).thenReturn(imagesDomain);

        assertThrows(IllegalArgumentException.class, ()->{
                ProductDomainFactory.createProduct(productSpecs, new PaintingDomainDetails(PaintingDomainDetails.MIN_HEIGHT_CM-1, PaintingDomainDetails.MIN_LARGE_CM, MediumEnum.OIL_PAINT, SupportMaterialEnum.COTTON_PAPER, LocalDate.now().minusWeeks(3)));
        });

        assertThrows(IllegalArgumentException.class, ()->{
            ProductDomainFactory.createProduct(productSpecs, new PaintingDomainDetails(PaintingDomainDetails.MIN_HEIGHT_CM, PaintingDomainDetails.MIN_LARGE_CM-1, MediumEnum.OIL_PAINT, SupportMaterialEnum.COTTON_PAPER, LocalDate.now().minusWeeks(3)));
        });

        assertThrows(IllegalArgumentException.class, ()->{
            ProductDomainFactory.createProduct(productSpecs, new PaintingDomainDetails(PaintingDomainDetails.MIN_HEIGHT_CM, PaintingDomainDetails.MIN_LARGE_CM, MediumEnum.OIL_PAINT, SupportMaterialEnum.COTTON_PAPER, LocalDate.now().plusDays(1)));
        });


    }
    }


    @Test
    @DisplayName("Test para crear los un BodyClothing usando  la clase ProductFactory")
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



        ProductSpecs productSpecs= new ProductSpecs("Titulo obra", "descripción x", images,bodyClothingStockDTO, singlePricingDTO , BodyClothingType, true );


        try(MockedStatic<ImageProcessor> imageProcessorMocked= mockStatic(ImageProcessor.class)){
            imageProcessorMocked.when(()->ImageProcessor.processImages(any(), any())).thenReturn(imagesDomain);
            ProductDomain bodyClothing = ProductDomainFactory.createProduct(productSpecs, new BodyClothingDomainDetails());

            Assertions.assertThat(bodyClothing).isInstanceOf(BodyClothingDomain.class);

            Assertions.assertThat(bodyClothing.getProductDetails()).isInstanceOf(BodyClothingDomainDetails.class);


            Assertions.assertThat(bodyClothing.getPriceManagerBase()).isInstanceOf(SinglePriceManager.class);

            Assertions.assertThat(bodyClothing.getStockManagerBase()).isInstanceOf(BodyClothingStockManager.class);
        }

    }

    @Test
    @DisplayName("Test para arrojar una excepción por detalles de producto creando un BodyClothing usando  la clase ProductFactory ")
    public void shouldThrowExceptionCreatingBodyClothingProductByDetails(){
        // productSpecs
        // productDomainDetails

        ProductTypeEnum BodyClothingType = ProductTypeEnum.CLOTHING;

        Map<ClothingSizeEnum,Integer> defaultClothingStock = new HashMap<>();

        Arrays.stream(ClothingSizeEnum.values()).forEach((clothingSizeEnum)->{
            defaultClothingStock.put(clothingSizeEnum,5);
        });

        BodyClothingStockDTO bodyClothingStockDTO = new BodyClothingStockDTO(defaultClothingStock);

        SinglePricingDTO singlePricingDTO =  new SinglePricingDTO(new BigDecimal("350"));



        ProductSpecs productSpecs= new ProductSpecs("Titulo obra", "descripción x", images,bodyClothingStockDTO, singlePricingDTO , BodyClothingType, true );

        try(MockedStatic<ImageProcessor> imageProcessorMocked= mockStatic(ImageProcessor.class)){
            imageProcessorMocked.when(()->ImageProcessor.processImages(any(), any())).thenReturn(imagesDomain);
            assertThrows(IllegalArgumentException.class, ()->{
                ProductDomain bodyClothing = ProductDomainFactory.createProduct(productSpecs, new BodyClothingDomainDetails("",BodyClotheTypesEnum.HOODIE,PrintingTecniqueEnum.SERIGRAPHY));

            });
        }



    }



}




















