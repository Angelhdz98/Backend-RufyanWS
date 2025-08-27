package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindProductByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import com.example.PaginaWebRufyan.domain.port.out.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/{id}")
public class FindProductByIdController {
    private final FindProductByIdUseCase findProductByIdUseCase;

    public FindProductByIdController(FindProductByIdUseCase findProductByIdUseCase) {
        this.findProductByIdUseCase = findProductByIdUseCase;
    }
    @GetMapping
    ResponseEntity<ProductDTO> findProductById(@PathVariable Long id){
        return ResponseEntity.ok(ProductDTOMapper.toDTO(findProductByIdUseCase.findProductById(id)));
    }
}
