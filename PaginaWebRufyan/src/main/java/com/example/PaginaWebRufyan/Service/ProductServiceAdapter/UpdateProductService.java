package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomainFactory;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductCommand;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductService implements UpdateProductByIdUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public UpdateProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public ProductDomain updateProductById(UpdateProductCommand command) {

        return productRepositoryPort.updateProduct(ProductDomainFactory.updateProduct(command));
    }
}
