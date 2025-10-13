package com.example.PaginaWebRufyan.JPAAdapterTest;

import com.example.PaginaWebRufyan.Products.Enums.ClothingSizeEnum;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.domain.model.*;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.*;
import com.example.PaginaWebRufyan.domain.port.out.ProductMapper;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryJPAImpl;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import static  com.example.PaginaWebRufyan.domain.port.out.ProductMapper.toDomain;
import static  com.example.PaginaWebRufyan.domain.port.out.ProductMapper.toEntity;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

import static com.example.PaginaWebRufyan.domain.model.ProductDomainFactory.createProduct;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import({ImageStorageProperties.class,ProductRepositoryJPAImpl.class})
//@AutoConfigureTestDatabase
public class JPAProductAdaptersTest {


    @Autowired
    private TestEntityManager entityManager;

    @TempDir
    static Path tempDirBase;

    static Path testSpecificPath;

    @Autowired
    ProductRepositoryJPAImpl productRepositoryJPA;
    @Autowired
        private    ImageStorageProperties imageStorageProperties;


    //Long id1= 1L; // commented cause JPA autoAsignThis

    ProductSpecs product1Specs;
    ProductSpecs product2Specs;
    ProductSpecs product3Specs;

    ProductDomain firstProduct;

    ClassLoader classLoader = getClass().getClassLoader();

    Path imagePath1;
    Path imagePath2;
    Path imagePath3;

    ProductDomainDetails productDomainDetails;
    ProductDomainDetails productDomainDetails2;

    {
        try {
            imagePath1 = Paths.get(classLoader.getResource("static/obra1.jpg").toURI());

            imagePath2 = Paths.get(classLoader.getResource("static/obra2.jpg").toURI());
            imagePath3 = Paths.get(classLoader.getResource("static/obra3.jpg").toURI());

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    @BeforeEach
    public void setUp() throws IOException {

/*        mockedImageProcessor.when(()->ImageProcessor.processImages(anyList(),anyString())).thenReturn(imageDomainSet);
        when(imageDomainSet.size()).thenReturn(4);
        */
        // se crea una path temporal
         String testId = "test-"+ UUID.randomUUID()+ "-"+ Instant.now().toEpochMilli();
         testSpecificPath = tempDirBase.resolve(testId);
         Files.createDirectories(testSpecificPath);
         System.setProperty("app.upload-dir",testSpecificPath.toString());
        imageStorageProperties.init();


        byte[] image1Bytes = Files.readAllBytes(imagePath1);
        byte[] image2Bytes = Files.readAllBytes(imagePath2);
        byte[] image3Bytes = Files.readAllBytes(imagePath3);

        MockMultipartFile mockFile1 = new MockMultipartFile("obra1", "obra1.jpg", "image/jpg", image1Bytes);
        MockMultipartFile mockFile2 = new MockMultipartFile("obra2", "obra2.jpg", "image/jpg", image2Bytes);
        MockMultipartFile mockFile3 = new MockMultipartFile("obra3", "obra3.jpg", "image/jpg", image3Bytes);

        Set<MultipartFile> images = Set.of(mockFile1,mockFile2, mockFile3);

        Map<ClothingSizeEnum, Integer> defaultStockPerSize = new HashMap<>();

        Arrays.stream(ClothingSizeEnum.values()).forEach((clothingSizeEnum) ->
        {
            defaultStockPerSize.put(clothingSizeEnum, 5);
        });


        product1Specs = new ProductSpecs("Nametest1 ", "descripción 1 ", images, new PaintingStockDTO(true, 5, 10), new PaintingPricingDTO(new BigDecimal("2500"), new BigDecimal("500")), ProductTypeEnum.PAINTING, true);

        product2Specs = new ProductSpecs("Nametest2 ", "descripción 2 ", images, new PaintingStockDTO(false, 0, 10), new PaintingPricingDTO(new BigDecimal("2000"), new BigDecimal("600")), ProductTypeEnum.PAINTING, true);

        product3Specs = new ProductSpecs("Nametest3 ", "descripción 3 ", images, new BodyClothingStockDTO(defaultStockPerSize), new SinglePricingDTO(new BigDecimal("1200")), ProductTypeEnum.CLOTHING, false);

        // first value in table
        //firstProduct = productRepositoryJPA.saveProduct(createProduct(product1Specs, productDomainDetails));

        productDomainDetails = new PaintingDomainDetails();
        productDomainDetails2 = new BodyClothingDomainDetails();

        //String uploadDirection = ImageStorageProperties.getUploadDir();

    }

    @Test
    @DisplayName("Test para guardar un producto en la base de datos H2")
    public void shouldReturnAProductDomainAfterSavingIt() {

        ProductDomain painting2 = ProductDomainFactory.createProduct(product2Specs, productDomainDetails);
        ProductDomain savedProduct = productRepositoryJPA.saveProduct(painting2);

        //Assertions
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0L);
        assertThat(savedProduct.getName()).isEqualTo(painting2.getName());
        assertThat(savedProduct.getProductType()).isEqualTo(painting2.getProductType());
        assertThat(savedProduct.getDescription()).isEqualTo(painting2.getDescription());
        assertThat(savedProduct.getImages().stream().findFirst().get()).isInstanceOf(ImageDomain.class);

    }


    @Test
    @DisplayName("Test para recuperar un producto por su id (cuando es seguro que se encontrará)")
    public void shouldRetrieveProductById() {
        ProductDomain product = createProduct(product1Specs, productDomainDetails);
        firstProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(product)));

        assertThat(productRepositoryJPA.retrieveProductById(firstProduct.getId())).isInstanceOf(ProductDomain.class);
    }

    @Test
    @DisplayName("Test para buscar un producto por su id")
    public void shouldReturnAnOptionalSearchingAProductById() {
        firstProduct = toDomain(entityManager.persistAndFlush(toEntity(createProduct(product1Specs, productDomainDetails))));

        Optional<ProductDomain> optionalProduct = productRepositoryJPA.findProductById(firstProduct.getId());
        assertThat(optionalProduct).isNotEmpty();
        ProductDomain foundProduct = optionalProduct.get();
        assertThat(optionalProduct.get().getId()).isEqualTo(firstProduct.getId());
        assertThat(foundProduct.getImages().stream().findFirst().get()).isInstanceOf(ImageDomain.class);

    }

    @Test
    @DisplayName("Test para actualizar un producto en la base de datos H2")
    public void shouldReturnProductUpdated() {
        firstProduct = toDomain(entityManager.persistAndFlush(toEntity(createProduct(product1Specs, productDomainDetails))));
        String newName = "Nombre actualizado";
        String newDescription = "Descripción actualizada";

        PaintingDomain updatedNewPainting = new PaintingDomain(firstProduct.getId(), newName, ((PaintingStockManager) firstProduct.getStockManagerBase()), ((PaintingPriceManager) firstProduct.getPriceManagerBase()), firstProduct.getImages(), (PaintingDomainDetails) firstProduct.getProductDetails(), firstProduct.getProductType(), newDescription, firstProduct.getIsFavorite());


        ProductDomain updatedProduct = productRepositoryJPA.updateProduct(updatedNewPainting);

        assertThat(updatedProduct.getName()).isEqualTo(newName);
        assertThat(updatedProduct.getDescription()).isEqualTo(newDescription);
    }

    @Test
    @DisplayName("Test para eliminar un producto por su id")
    public void shouldDeleteProductById() {
        firstProduct = toDomain(entityManager.persistAndFlush(toEntity(createProduct(product1Specs, productDomainDetails))));

        productRepositoryJPA.deleteProductById(firstProduct.getId());

        Optional<ProductDomain> optionalProduct = productRepositoryJPA.findProductById(firstProduct.getId());

        assertThat(optionalProduct).isEmpty();

    }

    @Test
    @DisplayName("Test para buscar todos los productos en la base de datos H2")
    public void shouldFindAllProducts() {
        firstProduct =ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(createProduct(product1Specs, productDomainDetails))));
        ProductDomain secondProduct = toDomain(entityManager.persistAndFlush(toEntity(createProduct(product2Specs, productDomainDetails)))) ;

        List<ProductDomain> products = productRepositoryJPA.findAllProducts();

        assertThat(products).isNotEmpty();
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.get(0)).isInstanceOf(ProductDomain.class);
        assertThat(products.get(1)).isInstanceOf(ProductDomain.class);
    }

    @Test
    @DisplayName("Test para buscar productos paginados en la base de datos H2")
    public void shouldFindPagedProducts() {
        firstProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(createProduct(product1Specs, productDomainDetails))));
        ProductDomain secondProduct = toDomain(entityManager.persistAndFlush(toEntity( createProduct(product2Specs, productDomainDetails)) ));

        Page<ProductDomain> pagedProducts = productRepositoryJPA.findPagedProducts(PageRequest.of(0, 2));

        assertThat(pagedProducts).isNotEmpty();
        assertThat(pagedProducts.getContent().size()).isEqualTo(2);
        assertThat(pagedProducts.getContent().get(0)).isInstanceOf(ProductDomain.class);
        assertThat(pagedProducts.getContent().get(1)).isInstanceOf(ProductDomain.class);
    }


    @Test
    @DisplayName("Test para encontar todos los productos por tipo de producto")
    public void shouldFindProductsByType() {
        firstProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(createProduct(product1Specs, productDomainDetails))));
        ProductDomain secondProduct = ProductMapper.toDomain( entityManager.persistAndFlush( ProductMapper.toEntity(createProduct(product2Specs, productDomainDetails))));
        ProductDomain thirdProduct =ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(createProduct(product3Specs, productDomainDetails2))));

        List<ProductDomain> paintingProducts = productRepositoryJPA.findProductByType(ProductTypeEnum.PAINTING);

        assertThat(paintingProducts).isNotEmpty();
        assertThat(paintingProducts.size()).isEqualTo(2);
        assertThat(paintingProducts.get(0)).isInstanceOf(ProductDomain.class);
        assertThat(paintingProducts.get(1)).isInstanceOf(ProductDomain.class);
    }


    @Test
    @DisplayName("Test para encontar productos paginados por tipo de producto")
    public void shouldFindProductsByTypePaged() {
        firstProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(Objects.requireNonNull(createProduct(product1Specs, productDomainDetails)))));

        ProductDomain secondProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(Objects.requireNonNull(createProduct(product2Specs, productDomainDetails)))));
        ProductDomain thirdProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(Objects.requireNonNull(createProduct(product3Specs, productDomainDetails2)))));

        Page<ProductDomain> paintingProducts = productRepositoryJPA.findProductByType(ProductTypeEnum.PAINTING, PageRequest.of(0, 2));

        assertThat(paintingProducts).isNotEmpty();
        assertThat(paintingProducts.getContent().size()).isEqualTo(2);
        assertThat(paintingProducts.getContent().get(0)).isInstanceOf(ProductDomain.class);
        assertThat(paintingProducts.getContent().get(1)).isInstanceOf(ProductDomain.class);
    }

    @Test
    @DisplayName("Test para encontar todos los productos disponibles paginados")
    public void shouldFindAvailableProductsPaged() {
        firstProduct = ProductMapper.toDomain (entityManager.persistAndFlush( ProductMapper.toEntity(Objects.requireNonNull(createProduct(product1Specs, productDomainDetails)))));

        ProductDomain secondProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(Objects.requireNonNull(createProduct(product2Specs, productDomainDetails)))));

        ProductDomain thirdProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(createProduct(product3Specs, productDomainDetails2))));

        Page<ProductDomain> availableProducts = productRepositoryJPA.findAvailableProducts(PageRequest.of(0, 2));

        assertThat(availableProducts).isNotEmpty();
        assertThat(availableProducts.getContent().size()).isEqualTo(2);
        assertThat(availableProducts.getContent().get(0)).isInstanceOf(ProductDomain.class);
        assertThat(availableProducts.getContent().get(1)).isInstanceOf(ProductDomain.class);
    }
    @Test
    @DisplayName("Test para encontar todos los productos disponibles por tipo de producto paginados")
    public void shouldFindAvailableProductsByTypePaged() {
        firstProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(Objects.requireNonNull(createProduct(product1Specs, productDomainDetails)))));
        ProductDomain secondProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(Objects.requireNonNull(createProduct(product2Specs, productDomainDetails)))));


        ProductDomain product1 = createProduct(product3Specs, productDomainDetails2);
        Product product = ProductMapper.toEntity(product1);

        Product product2persisted = entityManager.persistAndFlush(product);


        System.out.println(product);
        ProductDomain thirdProduct = ProductMapper.toDomain(product);

        Page<ProductDomain> availablePaintingProducts = productRepositoryJPA.findAvailableProductsByType(ProductTypeEnum.PAINTING, PageRequest.of(0, 2));

        assertThat(availablePaintingProducts).isNotEmpty();
        assertThat(availablePaintingProducts.getContent().size()).isEqualTo(1);
        assertThat(availablePaintingProducts.getContent().get(0)).isInstanceOf(PaintingDomain.class);

    }

    public void shouldFindFavoriteProductsPaged() {
        firstProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(Objects.requireNonNull(createProduct(product1Specs, productDomainDetails)))));

        ProductDomain secondProduct = ProductMapper.toDomain(entityManager.persistAndFlush(ProductMapper.toEntity(Objects.requireNonNull(createProduct(product2Specs, productDomainDetails)))));

        ProductDomain thirdProduct = ProductMapper.toDomain(entityManager.persistAndFlush( ProductMapper.toEntity(Objects.requireNonNull(createProduct(product3Specs, productDomainDetails)))));

        Page<ProductDomain> favoriteProducts = productRepositoryJPA.findFavoriteProducts(PageRequest.of(0, 2));

        assertThat(favoriteProducts).isNotEmpty();
        assertThat(favoriteProducts.getContent().size()).isEqualTo(2);
        assertThat(favoriteProducts.getContent().get(0)).isInstanceOf(ProductDomain.class);
        assertThat(favoriteProducts.getContent().get(0).getIsFavorite()).isTrue();
        assertThat(favoriteProducts.getContent().get(1)).isInstanceOf(ProductDomain.class);
        assertThat(favoriteProducts.getContent().get(1).getIsFavorite()).isTrue();
    }










}






















