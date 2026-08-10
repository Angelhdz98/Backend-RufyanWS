package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomainFactory;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;

import org.springframework.stereotype.Service;

@Service
public class MarkProductAsFavoriteService {
private final ProductRepositoryPort productRepositoryPort;

    public MarkProductAsFavoriteService(ProductRepositoryPort productRepositoryPort, ProductDomainFactory productDomainFactory) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public Boolean markProductAsFavorite(Long productId){
        ProductDomain productDomain = productRepositoryPort.retrieveProductById(productId);
        productDomain.setIsFavorite(true);
        productRepositoryPort.updateProduct(productDomain);
        return true;
    }
}
