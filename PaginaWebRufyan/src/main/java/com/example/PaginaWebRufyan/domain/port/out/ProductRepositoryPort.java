package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
   Optional<ProductDomain> findProductById();

   List<ProductDomain> findAllProducts();
   Page<ProductDomain> findPagedProducts(Pageable pageable);

   List<ProductDomain> findProductByType(ProductTypeEnum productType);
   Page<ProductDomain> findProductByType(ProductTypeEnum productTypeEnum, Pageable pageable);

   List<ProductDomain> findAvailableProducts(); // TODO: don't use this in production, change it for a Pageable
   Page<ProductDomain> findAvailableProducts(Pageable page);

   List<ProductDomain> findAvailableProductsByType();

}
