package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.Products.DTO.Product.PageResponse;
import com.example.PaginaWebRufyan.Products.Repository.ProductsRepository;
import com.example.PaginaWebRufyan.adapter.out.persistence.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@PreAuthorize("hasRole('ADMIN')")
public class FinPagedProductEntitiesController  {
    private final ProductsRepository productRepo;

    public FinPagedProductEntitiesController(ProductsRepository productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/admin/products-paged")
    public Page<Product> getEntitiesPaged() {
        Pageable pageRequest =  PageRequest.of(0,10);
        return productRepo.findAll(pageRequest);

    }

    @GetMapping("/admin/products-paged-custom")
    public PageResponse<Product> getEntitiesPaged(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, @RequestParam String sorterType, @RequestParam String sortOrder) {


        SorterTypeEnum sorter = SorterTypeEnum.fromValue(sorterType);
        SortOrderEnum order = SortOrderEnum.valueOf(sortOrder.toUpperCase());

        Pageable pageRequest = PageRequest.of(pageNumber,pageSize,
                Sort.by(sorter.getValue()).descending());

            if(order == SortOrderEnum.ASCENDING){
                pageRequest = PageRequest.of(pageNumber,pageSize,
                        Sort.by(sorter.getValue()).ascending());
            }
        Page<Product> findedPage = productRepo.findAll(pageRequest);
        return new PageResponse<Product>(findedPage.getContent(), findedPage.getNumber(), findedPage.getSize(), findedPage.getTotalElements(), findedPage.getTotalPages());
    }
}
