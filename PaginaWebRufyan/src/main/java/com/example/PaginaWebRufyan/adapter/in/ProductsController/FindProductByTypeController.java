package com.example.PaginaWebRufyan.adapter.in.ProductsController;

import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.port.in.ProductUseCase.FindProductsByTypeUseCase;


import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FindProductByTypeController {
    private final FindProductsByTypeUseCase findProductsByTypeUseCase;
    private final ProductDTOMapper productDTOMapper;

    public FindProductByTypeController(FindProductsByTypeUseCase findProductsByTypeUseCase, ProductDTOMapper productDTOMapper) {
        this.findProductsByTypeUseCase = findProductsByTypeUseCase;
        this.productDTOMapper = productDTOMapper;
    }


    @GetMapping("/products/by-type")
    ResponseEntity<Page<ProductDTO>> findProductsByProductType(@ModelAttribute GetProductsByTypeCommand getProductsByTypeCommand){
        System.out.println("getProductBytypeCommand: " + getProductsByTypeCommand );
        Sort sorter;
        if(getProductsByTypeCommand.sorterType().equals("price")) {
            if (getProductsByTypeCommand.sortOrder().equals(SortOrderEnum.DESCENDING.toString().toLowerCase())) {
                sorter =
                        Sort.by(SorterTypeEnum.PRICE_HIGH.getValue()).descending();
            } else {
                sorter =
                        Sort.by(SorterTypeEnum.PRICE_LOW.getValue()).ascending();
            }
        }else {
            sorter =
                    Sort.by(getProductsByTypeCommand.sortOrder(),
                            getProductsByTypeCommand.sorterType());
        }


        PageRequest pageable =
                PageRequest.of(getProductsByTypeCommand.pageNumber(), getProductsByTypeCommand.pageSize() ,sorter);
        Page<ProductDomain> pagedProductsByType = findProductsByTypeUseCase
                .findPagedProductsByType(getProductsByTypeCommand.productType(), pageable);
        List<ProductDomain> content = pagedProductsByType.getContent();
        System.out.println("page response content no mapped: "+ content );
        Page<ProductDTO> productDTOPage  =
                pagedProductsByType.map(productDTOMapper::toDTO);
        return ResponseEntity.ok(productDTOPage);
    }

}
