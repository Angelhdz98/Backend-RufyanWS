package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CreateProductUseCase {
    ProductDomain createProduct(CreateProductCommand command, List<MultipartFile> images);
}
