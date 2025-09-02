package com.example.PaginaWebRufyan.adapter.out.persistence;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataProductRepository extends JpaRepository<Product, Long> {

     Page<Product> findProductByProductTypeEnum(ProductTypeEnum productTypeEnum, Pageable pageable);
    List<Product> findProductByProductTypeEnum(ProductTypeEnum productTypeEnum);

    Page<Product> findProductByIsFavorite(Boolean isFavorite, Pageable pageable);

    Page<Product> findProductByIsAvailable(Boolean availability, Pageable pageable);

    Page<Product> findProductByIsAvailableAndProductTypeEnum(Boolean availability, ProductTypeEnum productTypeEnum, Pageable pageable);


}
