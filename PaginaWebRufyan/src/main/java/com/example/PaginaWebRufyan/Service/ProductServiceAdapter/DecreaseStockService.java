package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.DecreaseStockUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DecreaseStockService implements DecreaseStockUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    public DecreaseStockService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public void decreaseStock(CartItemDomain cartItemDomain) {
        ProductDomain productById = productRepositoryPort
                .retrieveProductById(cartItemDomain.getProduct().getId());
        productById.decreaseStock(cartItemDomain);
        productRepositoryPort.updateProduct(productById);

    }
}
