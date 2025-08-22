package com.example.PaginaWebRufyan.Service.LikesServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.LikeDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.GetPagedUsersThatLikedUseCase;
import com.example.PaginaWebRufyan.domain.port.out.LikesRepository;
import com.example.PaginaWebRufyan.domain.port.out.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetPagedUsersThatLikedService implements GetPagedUsersThatLikedUseCase {

    private final LikesRepository likeRepo;
    private final UserRepository usersRepo;

    public GetPagedUsersThatLikedService(LikesRepository likeRepo, UserRepository usersRepo) {
        this.likeRepo = likeRepo;
        this.usersRepo = usersRepo;
    }

    @Override
    public Page<UserDomain> getPagedUsersThatLiked(Long productId, Pageable pageable) {

        return usersRepo.findAllUsersWhoLikedProduct(productId, pageable);

    }
}
