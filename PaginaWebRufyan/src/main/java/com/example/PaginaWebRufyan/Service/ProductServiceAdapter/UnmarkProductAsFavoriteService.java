package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;


import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UnmarkProductAsFavoriteService {
    private final ProductRepositoryPort productRepositoryPort;


    public UnmarkProductAsFavoriteService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public Boolean unmarkProductAsFavorite(Long productId){
        ProductDomain productDomain = productRepositoryPort.retrieveProductById(productId);
        productDomain.setIsFavorite(false);
        productRepositoryPort.updateProduct(productDomain);
        return true;

    }

}
