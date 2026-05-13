package com.example.PaginaWebRufyan.domain.port.in.ProductUseCase;

import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindProductEntitiesPagedUseCase {
    Page<Product> getEntitiesPaged();
    Page<Product> getEntitiesPaged(Pageable pageable);

}
