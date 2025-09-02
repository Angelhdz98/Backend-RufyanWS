package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindPagedProductsUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import com.example.PaginaWebRufyan.domain.port.out.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class FindPagedProductsController {
    private final FindPagedProductsUseCase findPagedProductsUseCase;


    public FindPagedProductsController(FindPagedProductsUseCase findPagedProductsUseCase) {
        this.findPagedProductsUseCase = findPagedProductsUseCase;
    }
    @GetMapping
    ResponseEntity<Page<ProductDTO>> retrievePagedProducts(Pageable pageable){

        return ResponseEntity.ok( findPagedProductsUseCase.findPagedProducts(pageable).map(ProductDTOMapper::toDTO));
    }

}