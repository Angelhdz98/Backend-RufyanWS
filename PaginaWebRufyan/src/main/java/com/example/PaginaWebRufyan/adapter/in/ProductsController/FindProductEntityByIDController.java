package com.example.PaginaWebRufyan.adapter.in.ProductsController;


import com.example.PaginaWebRufyan.Products.Repository.ProductsRepository;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@PreAuthorize("permitAll()")
public class FindProductEntityByIDController {
    private final ProductsRepository productRepo;

    public FindProductEntityByIDController(ProductsRepository productRepo) {
        this.productRepo = productRepo;
    }
    @GetMapping("/admin/find-product-entity-by-id/{productId}")
    ResponseEntity<Product> findProductEntityById(@PathVariable Long productId){
        Optional<Product> productById = productRepo.findById(productId);
        if(productById.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productById.get());
    }


}
