package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface UpdateProductByIdUseCase {
    ProductDomain updateProductById(UpdateProductCommand command, Set<MultipartFile> addedImages);
}
