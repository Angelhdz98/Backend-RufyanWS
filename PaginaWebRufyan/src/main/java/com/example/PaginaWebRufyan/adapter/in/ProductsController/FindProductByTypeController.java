package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindProductsByTypeUseCase;

import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/by-type")
public class FindProductByTypeController {
    private final FindProductsByTypeUseCase findProductsByTypeUseCase;

    public FindProductByTypeController(FindProductsByTypeUseCase findProductsByTypeUseCase) {
        this.findProductsByTypeUseCase = findProductsByTypeUseCase;
    }
    @GetMapping
    ResponseEntity<Page<ProductDTO>> findProductsByProductType(@RequestBody ProductTypeEnum productTypeEnum, @RequestBody Pageable pageable){
       return ResponseEntity.ok(
               findProductsByTypeUseCase
                       .findPagedProductsByType(productTypeEnum,pageable)
                       .map(ProductDTOMapper::toDTO));
    }

}
