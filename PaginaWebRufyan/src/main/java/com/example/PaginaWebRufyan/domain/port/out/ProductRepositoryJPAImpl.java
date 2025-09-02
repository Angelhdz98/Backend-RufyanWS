package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import com.example.PaginaWebRufyan.adapter.out.persistence.SpringDataProductRepository;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomainFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Repository
public class ProductRepositoryJPAImpl implements ProductRepositoryPort{
    private final SpringDataProductRepository springDataProductRepository;

    public ProductRepositoryJPAImpl(SpringDataProductRepository springDataProductRepository) {
        this.springDataProductRepository = springDataProductRepository;
    }

    private  ProductDomain retrieveProduct(Long id){
       return springDataProductRepository.findById(id)
               .map(ProductMapper::toDomain)
               .orElseThrow(()-> new ResourceNotFoundException("No se encontró el product con el id: "+ id));
    }


    @Override
    public ProductDomain saveProduct(ProductDomain product) {
        return ProductMapper.toDomain(springDataProductRepository.save(ProductMapper.toEntity(product))) ;
    }

    @Override
    public ProductDomain updateProduct(ProductDomain product) {
        ProductDomain productDomain = retrieveProduct(product.getId());
        return ProductMapper.toDomain(springDataProductRepository.save(ProductMapper.toEntity(product)));
    }

    @Override
    public void deleteProductById(Long id) {
        ProductDomain productDomain = retrieveProduct(id);
        springDataProductRepository.deleteById(id);
    }

    @Override
    public Optional<ProductDomain> findProductById(Long userId) {
        return springDataProductRepository.findById(userId).map(ProductMapper::toDomain);
    }

    @Override
    public ProductDomain retrieveProductById(Long userId) {
        return retrieveProduct(userId);
    }

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


}
