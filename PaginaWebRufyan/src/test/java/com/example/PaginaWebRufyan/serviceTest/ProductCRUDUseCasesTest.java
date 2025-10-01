package com.example.PaginaWebRufyan.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.Service.ProductServiceAdapter.*;
import com.example.PaginaWebRufyan.adapter.out.SinglePriceManager;
import com.example.PaginaWebRufyan.domain.model.*;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.*;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductCRUDUseCasesTest {


    @Mock
    ProductRepositoryPort productRepositoryPort;
	//Create
    @InjectMocks
    CreateProductService createProductUseCase;
    //Read
    @InjectMocks
    FindProductByIdService findProductByIdUseCase;
    @InjectMocks
    FindPagedProductsService findPagedProductsUseCase;
    @InjectMocks
    FindProductByProductTypeService findProductsByTypeUseCase;

    @InjectMocks
    FindFavoriteProductService findFavoriteProductsUseCase;

    //Update
    @InjectMocks
    UpdateProductService updateProductByIdUseCase;
    @InjectMocks
    IncreaseStockService increaseStockUseCase;
    @InjectMocks
    DecreaseStockService decreaseStockUseCase;

    //Delete
    @InjectMocks
    DeleteProductService deleteProductByIdUseCase;

    ClassLoader classLoader = getClass().getClassLoader();

    Path imagePath1;
    Path imagePath2;
    Path imagePath3;
    Path imagePath4;

    byte[] image1Bytes;
    byte[] image2Bytes;
    byte[] image3Bytes;
    byte[] image4Bytes;

    {
        try {
            imagePath1 = Paths.get(classLoader.getResource("static/obra1.jpg").toURI());

            imagePath2 = Paths.get(classLoader.getResource("static/obra2.jpg").toURI());
            imagePath3 = Paths.get(classLoader.getResource("static/obra3.jpg").toURI());
            imagePath4 = Paths.get(classLoader.getResource("static/obra4.png").toURI());

        } catch (
                URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    Set<MultipartFile> images = new HashSet<>();
    Set<MultipartFile> addedImages = new HashSet<>();
    MockMultipartFile file1;
    MockMultipartFile file2;
    MockMultipartFile file3;
    MockMultipartFile file4;


    MultipartFile fileImageAdded = mock(MultipartFile.class);
    ProductSpecs productSpecs;
    ProductSpecs productSpecsUpdated;
    ProductDomain defaultProduct1;
    ProductDomain defaultProduct2;
    ProductDomain bodyClothingProduct;
    ProductDomain defaultProductUpdated;
    Integer stockCopies =5;
    Integer copiesMade = 10;
    Boolean isOriginalAvailable= true;

    @BeforeEach
    public  void setUp() throws IOException {



        byte[] image1Bytes = Files.readAllBytes(imagePath1);
        byte[] image2Bytes = Files.readAllBytes(imagePath2);
        byte[] image3Bytes = Files.readAllBytes(imagePath3);
        byte[] image4Bytes = Files.readAllBytes(imagePath4);

        file1 = new MockMultipartFile("obra1", "obra1.jpg", "image/jpg", image1Bytes);
        file2 =  new MockMultipartFile("obra2", "obra2.jpg", "image/jpg", image2Bytes);
        file3 = new MockMultipartFile("obra3", "obra3.jpg", "image/jpg", image3Bytes);
        file4 = new MockMultipartFile("obra4", "obra4.png", "image/png", image4Bytes);

        images.add(file1);
        images.add(file2);
        images.add(file3);

    addedImages.add(file4);


/*        Mockito.when(file1.getOriginalFilename()).thenReturn("night colors 1.");
        Mockito.when(file2.getOriginalFilename()).thenReturn("night colors 2.");
        Mockito.when(file3.getOriginalFilename()).thenReturn("night colors 3.");
        Mockito.when(file3.getOriginalFilename()).thenReturn("night colors new.");

 */
        //

         productSpecs= new ProductSpecs("Titulo obra", "descripción x", images, new PaintingStockDTO(PaintingStockManager.DEFAULT_ORIGINAL_AVAILABLE,PaintingStockManager.DEFAULT_STOCK_COPIES,PaintingStockManager.DEFAULT_COPIES_MADE), new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE,PaintingPriceManager.MIN_ORIGINAL_PRICE), ProductTypeEnum.PAINTING, true );

         productSpecsUpdated = new ProductSpecs(productSpecs.name().toLowerCase(),
                 "descripción x",
                 addedImages,
                 new PaintingStockDTO(PaintingStockManager.DEFAULT_ORIGINAL_AVAILABLE,PaintingStockManager.DEFAULT_STOCK_COPIES,PaintingStockManager.DEFAULT_COPIES_MADE), new PaintingPricingDTO(PaintingPriceManager.MIN_ORIGINAL_PRICE,PaintingPriceManager.MIN_ORIGINAL_PRICE), ProductTypeEnum.PAINTING, true );

         ImageDomain image1 = new ImageDomain(1L,productSpecs.name(),"localchost:8080/images/paintings/"+file1);
        ImageDomain image2 = new ImageDomain(2L,productSpecs.name(),"localchost:8080/images/paintings/"+file2);
        ImageDomain image3 = new ImageDomain(3L,productSpecs.name(),"localchost:8080/images/paintings/"+file3);
        ImageDomain image4 = new ImageDomain(4L,productSpecs.name(),"localchost:8080/images/paintings/"+fileImageAdded);

        defaultProduct1 = new PaintingDomain(1L,productSpecs.name(), new PaintingStockManager(stockCopies,copiesMade,true),new PaintingPriceManager(),Set.of(image1,image2, image3), new PaintingDomainDetails(), productSpecs.productTypeEnum(),productSpecs.description(),productSpecs.isFavorite());

        defaultProduct2 = new PaintingDomain(2L,productSpecs.name()+ "2.0", new PaintingStockManager(),new PaintingPriceManager(),Set.of(image1,image2, image3), new PaintingDomainDetails(), productSpecs.productTypeEnum(),productSpecs.description(),!productSpecs.isFavorite());
        bodyClothingProduct = new BodyClothingDomain(3L, "chamarra tag rufyan",new BodyClothingStockManager(),new SinglePriceManager(new BigDecimal(1200)),Set.of(image1,image2, image3),new BodyClothingDomainDetails(),ProductTypeEnum.CLOTHING,"una chamarra con aerografía",true);


         defaultProductUpdated = new PaintingDomain(1L,productSpecs.name().toLowerCase(), new PaintingStockManager(),new PaintingPriceManager(),Set.of(image1,image2,image3,image4 ), new PaintingDomainDetails(), productSpecs.productTypeEnum(),productSpecs.description(),productSpecs.isFavorite());

    }

    @Test
    @DisplayName("Test para crear un producto con los casos de uso ")
    public void shouldCreateAProduct(){

        when(productRepositoryPort.saveProduct(any(ProductDomain.class))).thenReturn(defaultProduct1);
        ProductDomain resultProduct = createProductUseCase.createProduct(new CreateProductCommand(productSpecs, new PaintingDomainDetails()));

        verify(productRepositoryPort).saveProduct(any(ProductDomain.class));
        assertThat(resultProduct.getName()).isEqualTo(productSpecs.name());
        assertThat(resultProduct.getImages()).isNotEmpty();



    }

    @Test
    @DisplayName("Test caso de uso  busqueda de un producto por su Id")
    public void  givenExistingByIdReturnOptionalProduct(){
        Long id = defaultProduct1.getId();
        Mockito.when(productRepositoryPort.retrieveProductById(id)).thenReturn(defaultProduct1);
        Mockito.when(productRepositoryPort.findProductById(id)).thenReturn(Optional.of(defaultProduct1));
        ProductDomain productById = findProductByIdUseCase.findProductById(id);

       assertThat(productById.getImages()).isNotEmpty();
       assertThat(productById.getName()).isEqualTo(productSpecs.name());
       assertThat(productById.getDescription()).isEqualTo(productSpecs.description());
       verify(productRepositoryPort).retrieveProductById(any());


    }
    @Test
    @DisplayName("Test caso de uso  busqueda de un producto por su Id no encontrado")
    public void  givenDoesNotExistByIdReturnException(){
        Long id = defaultProduct1.getId();
        Mockito.when(productRepositoryPort.findProductById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()->{
            ProductDomain productById = findProductByIdUseCase.findProductById(id);
        });

    }


    @Test
    @DisplayName("Test del caso de uso de busqueda de productos de todo tipo, respuesta paginada")
    public void ShouldReturnProductsPaged (){
        Page<ProductDomain> mockResponse = new PageImpl<>(List.of(defaultProduct1,defaultProduct2, bodyClothingProduct));

        when(productRepositoryPort.findPagedProducts(any(Pageable.class))).thenReturn(mockResponse);
        Page<ProductDomain> pagedProducts = findPagedProductsUseCase.findPagedProducts(PageRequest.of(0,10));
        assertThat(pagedProducts.getTotalElements()).isEqualTo(3L);
        assertThat(pagedProducts.getContent()).isEqualTo(mockResponse.getContent());
        verify(productRepositoryPort).findPagedProducts(any());


    }

@Test
@DisplayName("Test caso de uso: busqueda por Product type debe dar un resultado paginado")
    public void shouldReturnProductsByTypePaged(){

        ProductTypeEnum searchedType = ProductTypeEnum.PAINTING;
    Page<ProductDomain> mockPage = new PageImpl<>(List.of(defaultProduct1,defaultProduct2));
    PageRequest pageRequest = PageRequest.of(0, 2);
    when(productRepositoryPort.findProductByType(searchedType,  pageRequest)).thenReturn(mockPage);

    Page<ProductDomain> result = findProductsByTypeUseCase.findPagedProductsByType(searchedType, PageRequest.of(0,2));

    verify(productRepositoryPort).findProductByType(any(), any(Pageable.class));
    assertThat(result.getContent().size()).isEqualTo(mockPage.getContent().size());


    }

    @Test
    @DisplayName("Test caso de uso: busqueda productos favoritos debe dar un resultado paginado")
    public void shouldReturnFavoriteProductsPaged(){

        Boolean isFavorite = true;

        Page<ProductDomain> mockPage = new PageImpl<>(List.of(defaultProduct1,bodyClothingProduct));

        when(productRepositoryPort.findFavoriteProducts(any(Pageable.class))).thenReturn(mockPage);

        Page<ProductDomain> result = findFavoriteProductsUseCase.findFavoriteProducts(PageRequest.of(0,10));

        assertThat(result.getContent().size()).isEqualTo(mockPage.getContent().size());
        verify(productRepositoryPort).findFavoriteProducts(any());



    }
    @Test
    @DisplayName("Test de caso de uso para actualizar un producto por id  ")
    public void shouldReturnAnUpdatedProduct(){
        Long id = 1L;

        when(productRepositoryPort.updateProduct(any())).thenReturn(defaultProductUpdated);

        UpdateProductCommand command = new UpdateProductCommand(id, productSpecsUpdated, new PaintingDomainDetails(), defaultProductUpdated.getImages());
        ProductDomain updateProductById = updateProductByIdUseCase.updateProductById(command);

        assertThat(updateProductById.getId()).isEqualTo(defaultProductUpdated.getId());
        assertThat(updateProductById.getImages()).isNotEmpty();
        verify(productRepositoryPort).updateProduct(any());




    }

    @Test
    @DisplayName("Test de caso de uso  para incrementar el stock de un producto ")
    public void shouldReturnPaintingStockIncremented(){
        Long id = 1L;
        Integer quantity = 3;
        Boolean isOriginalSelected= false;
        CartItemDomain cartItemDomain = new CartItemDomain(67L, defaultProduct1, new PaintingItemDetails(quantity, isOriginalSelected));
        //defaultProduct1.increaseStock(cartItemDomain);
        when(productRepositoryPort.retrieveProductById(cartItemDomain.getProduct().getId())).thenReturn(defaultProduct1);
        when(productRepositoryPort.updateProduct(any())).thenReturn(defaultProduct1);
        increaseStockUseCase.increaseProductStock(cartItemDomain);

        verify(productRepositoryPort).updateProduct(any(ProductDomain.class));




    }
    @Test
    @DisplayName("Test de caso de uso para decrementar el stock de un producto ")
    public void shouldReturnPaintingStockDecremented(){
        Long id = 1L;
        Integer quantity = 3;
        Boolean isOriginalSelected= false;
        CartItemDomain cartItemDomain = new CartItemDomain(67L, defaultProduct1, new PaintingItemDetails(quantity, isOriginalSelected));

        when(productRepositoryPort.retrieveProductById(cartItemDomain.getProduct().getId())).thenReturn(defaultProduct1);
        when(productRepositoryPort.updateProduct(any())).thenReturn(defaultProduct1);
        decreaseStockUseCase.decreaseStock(cartItemDomain);

        verify(productRepositoryPort).updateProduct(any(ProductDomain.class));

    }

    @Test
    @DisplayName("Test de casos de uso para eliminar un producto por su id ")
    public void shouldDeleteReturnPainting(){

        Long id = 1L;
        ProductDomain mockProduct= mock(ProductDomain.class);
        when(productRepositoryPort.retrieveProductById(any())).thenReturn(mockProduct);

        deleteProductByIdUseCase.deleteProduct(id);
        verify(productRepositoryPort,times(1)).deleteProductById(any());

    }






}
























