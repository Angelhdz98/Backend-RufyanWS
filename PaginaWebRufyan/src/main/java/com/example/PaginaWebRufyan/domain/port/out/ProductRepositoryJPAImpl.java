package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.persistence.SpringDataProductRepository;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Repository
public class ProductRepositoryJPAImpl implements ProductRepositoryPort{


    private final SpringDataProductRepository springDataProductRepository;

    public ProductRepositoryJPAImpl(SpringDataProductRepository springDataProductRepository) {
        this.springDataProductRepository = springDataProductRepository;
    }

    private  ProductDomain retrieveProduct(Long id){
       return findProductById(id)
               .orElseThrow(()-> new ResourceNotFoundException("No se encontró el product con el id: "+ id));
    }


    @Override
    public ProductDomain saveProduct(ProductDomain product) {
        return ProductMapper.toDomain(springDataProductRepository.save(Objects.requireNonNull(ProductMapper.toEntity(product)))) ;
    }

    @Override
    public ProductDomain updateProduct(ProductDomain product) {
        ProductDomain productDomain = retrieveProduct(product.getId());
        return ProductMapper.toDomain(springDataProductRepository.save(ProductMapper.toEntity(product)));
    }

    @Override
    public void deleteProductById(Long id) {
        ProductDomain productDomain = retrieveProductById(id);
        springDataProductRepository.deleteById(id);
    }

    @Override
    public Optional<ProductDomain> findProductById(Long productId) {
        return springDataProductRepository.findById(productId).map(ProductMapper::toDomain);
    }

    @Override
    public ProductDomain retrieveProductById(Long productId) {
        return findProductById(productId).orElseThrow(()->new ResourceNotFoundException("No se encontró el usuario con el Id: "+ productId));
    }


    // Este metodo trae todos los productos, sin paginacion. evitar su uso
    @Override
    public List<ProductDomain> findAllProducts() {
        return springDataProductRepository.findAll().stream().map(ProductMapper::toDomain).toList();
    }

    @Override
    public Page<ProductDomain> findPagedProducts(Pageable pageable) {
       return springDataProductRepository.findAll(pageable).map(ProductMapper::toDomain);
    }

    @Override
    public List<ProductDomain> findProductByType(ProductTypeEnum productType) {
        return springDataProductRepository.findProductByProductTypeEnum(productType).stream().map(ProductMapper::toDomain).toList();
    }

    @Override
    public Page<ProductDomain> findProductByType(ProductTypeEnum productTypeEnum, Pageable pageable) {
        return springDataProductRepository.findProductByProductTypeEnum(productTypeEnum,pageable).map(ProductMapper::toDomain);
    }


    @Override
    public Page<ProductDomain> findAvailableProducts(Pageable page) {
        return springDataProductRepository.findProductByIsAvailable(true, page).map(ProductMapper::toDomain);
    }

    @Override
    public Page<ProductDomain> findAvailableProductsByType(ProductTypeEnum productTypeEnum, Pageable pageable) {
        return springDataProductRepository.findProductByIsAvailableAndProductTypeEnum(true,productTypeEnum ,pageable).map(ProductMapper::toDomain);
    }

    @Override
    public Page<ProductDomain> findProductsLikedByUser(Long userId, Pageable pageable) {
        return springDataProductRepository.findProductsLikedByUser(userId,pageable).map(ProductMapper::toDomain);
    }

    @Override
    public Page<ProductDomain> findFavoriteProducts(Pageable pageable) {
        return springDataProductRepository.findProductByIsFavorite(true, pageable).map(ProductMapper::toDomain);
    }

    @Override
    public boolean existById(Long productId) {
        return springDataProductRepository.existsById(productId);
    }

}
