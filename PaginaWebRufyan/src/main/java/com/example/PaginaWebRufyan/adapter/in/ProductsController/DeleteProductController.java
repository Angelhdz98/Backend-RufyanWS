package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.DeleteProductByIdUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/{id}")
public class DeleteProductController {
private final DeleteProductByIdUseCase deleteProductByIdUseCase;

    public DeleteProductController(DeleteProductByIdUseCase deleteProductByIdUseCase) {
        this.deleteProductByIdUseCase = deleteProductByIdUseCase;
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        deleteProductByIdUseCase.deleteProduct(id);
       return ResponseEntity.ok().build();
    }

}
