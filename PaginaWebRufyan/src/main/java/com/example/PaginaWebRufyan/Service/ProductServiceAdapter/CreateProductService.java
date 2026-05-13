package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomainFactory;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.CreateProductCommand;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.CreateProductUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class CreateProductService implements CreateProductUseCase {
   private final ProductRepositoryPort productRepositoryPort;
   private final ProductDomainFactory productDomainFactory;

    public CreateProductService(ProductRepositoryPort productRepositoryPort, ProductDomainFactory productDomainFactory) {
        this.productRepositoryPort = productRepositoryPort;
        this.productDomainFactory = productDomainFactory;
    }

    @Override
    public ProductDomain createProduct(CreateProductCommand command, List<MultipartFile> images ) {
        return productRepositoryPort.saveProduct(productDomainFactory.createProduct(command.productSpecs(),command.productDetails(),images));
    }
}
