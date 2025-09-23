package com.example.PaginaWebRufyan.Service.ProductServiceAdapter;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.IncreaseStockUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class IncreaseStockService implements IncreaseStockUseCase {
   private final ProductRepositoryPort productRepositoryPort;

    public IncreaseStockService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public void increaseProductStock(CartItemDomain cartItemDomain) {
        ProductDomain productById = productRepositoryPort.retrieveProductById(cartItemDomain.getProduct().getId());

               /* .findProductById(cartItemDomain.getProduct().getId() )
                .orElseThrow(()->new ResourceNotFoundException("No se encontró el producto con el id: "+ cartItemDomain.getProduct().getId()));
                */
        productById.increaseStock(cartItemDomain);
        productRepositoryPort.updateProduct(productById);
    }
}
