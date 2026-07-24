package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindPagedProductsUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@PreAuthorize("permitAll()")
public class FindPagedProductsController {
    private final FindPagedProductsUseCase findPagedProductsUseCase;
    private final ProductDTOMapper productDTOMapper;

    public FindPagedProductsController(FindPagedProductsUseCase findPagedProductsUseCase, ProductDTOMapper productDTOMapper) {
        this.findPagedProductsUseCase = findPagedProductsUseCase;
        this.productDTOMapper = productDTOMapper;
    }
    @GetMapping
    ResponseEntity<Page<ProductDTO>> retrievePagedProducts(Pageable pageable){
        return ResponseEntity.ok( findPagedProductsUseCase.findPagedProducts(pageable).map(productDTOMapper::toDTO));
    }

}