package com.example.PaginaWebRufyan.adapter.in.LikeControllers;

import com.example.PaginaWebRufyan.Service.LikesServiceAdapter.FindPagedProductsLikedByUserService;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.adapter.in.ProductDTO;
import com.example.PaginaWebRufyan.domain.port.out.ProductDTOMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetLikedProductsController {
private final CurrentUserService currentUserService;
private final FindPagedProductsLikedByUserService findPagedProductsLikedByUserService;
private final ProductDTOMapper productDTOMapper;

    public GetLikedProductsController(CurrentUserService currentUserService, FindPagedProductsLikedByUserService findPagedProductsLikedByUserService, ProductDTOMapper productDTOMapper) {
        this.currentUserService = currentUserService;
        this.findPagedProductsLikedByUserService = findPagedProductsLikedByUserService;
        this.productDTOMapper = productDTOMapper;
    }

    @GetMapping("/products-liked/{pageNumber}")
    public ResponseEntity<Page<ProductDTO>> getProductLikes(@PathVariable Integer pageNumber){
        PageRequest pageRequest = PageRequest.of(pageNumber, 20);

        Page<ProductDTO> productDTOPage = findPagedProductsLikedByUserService.getPagedProductsLikedByUser(currentUserService.getCurrentUser().getId(), pageRequest).map(productDTOMapper::toDTO);
        return ResponseEntity.ok(productDTOPage);
    }
}
