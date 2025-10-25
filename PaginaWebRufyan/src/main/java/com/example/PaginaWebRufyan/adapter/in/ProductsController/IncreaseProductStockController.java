package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.IncreaseStockUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/increase-stock")
public class IncreaseProductStockController {
    private final IncreaseStockUseCase increaseStockUseCase;

    public IncreaseProductStockController(@RequestBody IncreaseStockUseCase increaseStockUseCase) {
        this.increaseStockUseCase = increaseStockUseCase;
    }
    @PutMapping
    public ResponseEntity<Void> increseProductStock(CartItemDomain cartItemDomain){
        increaseStockUseCase.increaseProductStock(cartItemDomain);
        return ResponseEntity.ok().build();
    }

}
