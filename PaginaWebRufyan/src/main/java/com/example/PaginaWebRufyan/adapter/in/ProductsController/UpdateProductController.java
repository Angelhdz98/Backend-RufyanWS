package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductCommand;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("/product")
public class UpdateProductController {

    private final UpdateProductByIdUseCase updateProductByIdUseCase;

    public UpdateProductController(UpdateProductByIdUseCase updateProductByIdUseCase) {
        this.updateProductByIdUseCase = updateProductByIdUseCase;
    }
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ProductDomain> updateProductById(@RequestPart("updateCommand") UpdateProductCommand updateProductCommand, Set<MultipartFile> addedImages){
       return ResponseEntity.ok(updateProductByIdUseCase.updateProductById(updateProductCommand, addedImages));
    }

}
