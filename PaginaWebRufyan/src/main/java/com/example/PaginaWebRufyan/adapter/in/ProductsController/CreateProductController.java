package com.example.PaginaWebRufyan.adapter.in.ProductsController;


import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.CreateProductCommand;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.CreateProductUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class CreateProductController {
    private final CreateProductUseCase createProductUseCase;

    public CreateProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }
    @PostMapping
    ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductCommand command) {
        return ResponseEntity.ok(ProductDTOMapper.toDTO(createProductUseCase.createProduct(command)));

    }

}
