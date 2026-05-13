package com.example.PaginaWebRufyan.adapter.in.LikeControllers;

import com.example.PaginaWebRufyan.adapter.in.GetCommand;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.GetPagedUsersThatLikedUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("permitAll()")
public class GetPagedUsersThatLikedController {

    private final GetPagedUsersThatLikedUseCase getPagedUsersThatLikedUseCase;

    public GetPagedUsersThatLikedController(GetPagedUsersThatLikedUseCase getPagedUsersThatLikedUseCase) {
        this.getPagedUsersThatLikedUseCase = getPagedUsersThatLikedUseCase;
    }

    @GetMapping("/like/users-liked")
    Page<UserDomain> getPagedUsersThatLiked(@RequestBody GetCommand getCommand){
        PageRequest pageRequest = PageRequest.of(getCommand.pageNumber().intValue(), getCommand.pageSize().intValue());
        if(!getCommand.sortBy().isBlank()){
            pageRequest = PageRequest.of(getCommand.pageNumber().intValue(), getCommand.pageSize().intValue(), Sort.by(getCommand.sortBy()));
        }

        return getPagedUsersThatLikedUseCase.getPagedUsersThatLiked(getCommand.productId(), pageRequest);
    }

}
