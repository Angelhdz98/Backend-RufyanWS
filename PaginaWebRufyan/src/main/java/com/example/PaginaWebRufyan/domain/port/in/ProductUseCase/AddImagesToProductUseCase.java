package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface AddImagesToProductUseCase {
   Set<ImageDomain> addImagesToProductUseCase(Set<MultipartFile> imageFiles, Long productId);
}
