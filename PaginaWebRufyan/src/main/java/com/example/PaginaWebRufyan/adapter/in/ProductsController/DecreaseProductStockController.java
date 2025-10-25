package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.DecreaseStockUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products/decrease-stock")
public class DecreaseProductStockController {
    private final DecreaseStockUseCase decreaseStockUseCase;

    public DecreaseProductStockController(DecreaseStockUseCase decreaseStockUseCase) {
        this.decreaseStockUseCase = decreaseStockUseCase;
    }

    @PutMapping
    ResponseEntity<Void> decreaseStock(@RequestBody CartItemDomain cartItemDomain){
        decreaseStockUseCase.decreaseStock(cartItemDomain);
        return ResponseEntity.ok().build();
    }


}
