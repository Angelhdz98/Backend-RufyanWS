package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindProductByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class FindProductByIdService implements FindProductByIdUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public FindProductByIdService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public ProductDomain findProductById(Long id) {
       return productRepositoryPort.retrieveProductById(id);
    }
}
