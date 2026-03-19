package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.UpdateProductCommand;
import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/products")
@PreAuthorize("hasRole('ADMIN')")
public class UpdateProductController {

    private final UpdateProductByIdUseCase updateProductByIdUseCase;

    public UpdateProductController(UpdateProductByIdUseCase updateProductByIdUseCase) {
        this.updateProductByIdUseCase = updateProductByIdUseCase;
    }
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ProductDTO> updateProductById(@RequestPart("updateCommand") UpdateProductCommand updateProductCommand, @RequestPart("addedImages") List<MultipartFile> addedImages){
       return ResponseEntity.ok(ProductDTOMapper.toDTO(updateProductByIdUseCase.updateProductById(updateProductCommand, new HashSet<>(addedImages))));
    }

}
