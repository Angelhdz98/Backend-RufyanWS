package com.example.PaginaWebRufyan.Service.LikesServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.LikeDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.GetUsersThatLikedUseCase;
import com.example.PaginaWebRufyan.domain.port.out.LikesRepository;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUsersThatLikedAProductService implements GetUsersThatLikedUseCase {
    private final LikesRepository likesRepo;
    private final UserRepositoryPort usersRepo;

    public GetUsersThatLikedAProductService(LikesRepository likesRepo, UserRepositoryPort usersRepo) {
        this.likesRepo = likesRepo;
        this.usersRepo = usersRepo;
    }

    @Override
    public List<UserDomain> getUsersThatLiked(Long productId) {
       List<Long> userIds= likesRepo.listUsersLiked(productId).stream().map(LikeDomain::getUserId).toList();
       return usersRepo.findAllUsersByIds(userIds);
    }
}
