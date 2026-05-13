package com.example.PaginaWebRufyan.Products.DTO.Product;

import com.example.PaginaWebRufyan.adapter.out.persistence.Product;

import java.util.List;

public record PageResponse<T>(List<Product> content,
                              int page,
                              int size,
                              long totalElements,
                              int totalPages
) {};
