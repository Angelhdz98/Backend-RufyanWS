package com.example.PaginaWebRufyan.adapter.in.ProductsController;


import com.example.PaginaWebRufyan.adapter.in.ProductDTO;

import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.CreateProductCommand;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.CreateProductUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/products")
//@PreAuthorize("permitAll()")
@PreAuthorize("hasRole('ADMIN')")
public class CreateProductController {
    private final CreateProductUseCase createProductUseCase;
    private final ProductDTOMapper productDTOMapper;

    public CreateProductController(CreateProductUseCase createProductUseCase, ProductDTOMapper productDTOMapper) {
        this.createProductUseCase = createProductUseCase;
        this.productDTOMapper = productDTOMapper;
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ProductDTO> createProduct(@RequestPart("command") CreateProductCommand command,  @RequestPart("images") List<MultipartFile> images) {
        return ResponseEntity.ok(productDTOMapper.toDTO(
                createProductUseCase.createProduct(command, images)));
    }

}
