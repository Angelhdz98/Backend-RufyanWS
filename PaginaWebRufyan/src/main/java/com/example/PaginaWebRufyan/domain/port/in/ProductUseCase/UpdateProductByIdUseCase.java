package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface UpdateProductByIdUseCase {
    ProductDomain updateProductById(UpdateProductCommand command, Optional<List<MultipartFile>> addedImages);
}
