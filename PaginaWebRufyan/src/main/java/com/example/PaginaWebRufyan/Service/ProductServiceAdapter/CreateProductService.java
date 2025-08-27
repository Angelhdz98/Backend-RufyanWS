package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomainFactory;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.CreateProductCommand;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.CreateProductUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements CreateProductUseCase {
   private final ProductRepositoryPort productRepositoryPort;

    public CreateProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public ProductDomain createProduct(CreateProductCommand command) {
        return productRepositoryPort.saveProduct(ProductDomainFactory.createProduct(command.productSpecs(),command.productDetails()));
    }
}
