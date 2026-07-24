package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindProductByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("permitAll()")
public class FindProductByIdController {
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final ProductDTOMapper productDTOMapper;

    public FindProductByIdController(FindProductByIdUseCase findProductByIdUseCase, ProductDTOMapper productDTOMapper) {
        this.findProductByIdUseCase = findProductByIdUseCase;
        this.productDTOMapper = productDTOMapper;
    }
    @GetMapping("/products/{id}")
    ResponseEntity<ProductDTO> findProductById(@PathVariable Long id){
        return ResponseEntity.ok(productDTOMapper.toDTO(findProductByIdUseCase.findProductById(id)));
    }
}
