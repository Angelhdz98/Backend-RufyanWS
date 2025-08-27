package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.DeleteProductByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductService implements DeleteProductByIdUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public DeleteProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepositoryPort.deleteProductById(id);
    }
}
