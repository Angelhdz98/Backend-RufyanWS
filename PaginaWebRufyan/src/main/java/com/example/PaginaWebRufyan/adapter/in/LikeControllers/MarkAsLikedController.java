package com.example.PaginaWebRufyan.adapter.in.LikeControllers;

import com.example.PaginaWebRufyan.adapter.in.ProductLikedDto;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.CreateLikeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/like")
public class MarkAsLikedController {
   private final CreateLikeUseCase createLikeUseCase;
    public MarkAsLikedController(CreateLikeUseCase createLikeUseCase) {
        this.createLikeUseCase = createLikeUseCase;
    }

    @PostMapping
    ResponseEntity<ProductLikedDto> markProductAsLiked(@RequestBody LikeCommand likeCommand){
     return ResponseEntity.ok( new ProductLikedDto(createLikeUseCase.markAsLiked(likeCommand).getProductId()));
    }

}
