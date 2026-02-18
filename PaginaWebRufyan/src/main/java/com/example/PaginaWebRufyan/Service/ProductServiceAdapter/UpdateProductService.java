package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomainFactory;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductCommand;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
public class UpdateProductService implements UpdateProductByIdUseCase {
    private final ProductRepositoryPort productRepositoryPort;
    private final ProductDomainFactory productDomainFactory;

    public UpdateProductService(ProductRepositoryPort productRepositoryPort, ProductDomainFactory productDomainFactory) {
        this.productRepositoryPort = productRepositoryPort;
        this.productDomainFactory = productDomainFactory;
    }

    @Override
    public ProductDomain updateProductById(UpdateProductCommand command, Set<MultipartFile> addedImages) {

        return productRepositoryPort.updateProduct(productDomainFactory.updateProduct(command, addedImages));
    }
}
