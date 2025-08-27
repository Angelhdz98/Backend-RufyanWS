package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class UpdateProductController {

    private final UpdateProductByIdUseCase updateProductByIdUseCase;

    public UpdateProductController(UpdateProductByIdUseCase updateProductByIdUseCase) {
        this.updateProductByIdUseCase = updateProductByIdUseCase;
    }
    @PutMapping
    ResponseEntity<ProductDomain> updateProductById(UpdateProductCommand command){
       return ResponseEntity.ok(updateProductByIdUseCase.updateProductById(command));
    }

}
