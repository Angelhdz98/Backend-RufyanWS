package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;


public record GetProductsByTypeCommand(
                                        ProductTypeEnum productType,
                                        Integer pageNumber,
                                        Integer pageSize,
                                        String sorterType,
                                        String sortOrder) {
}

