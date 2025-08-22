package com.example.PaginaWebRufyan.Service.LikesServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.LikeDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.GetUsersThatLikedUseCase;
import com.example.PaginaWebRufyan.domain.port.out.LikesRepository;
import com.example.PaginaWebRufyan.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUsersThatLikedService implements GetUsersThatLikedUseCase {
    private final LikesRepository likesRepo;
    private final UserRepository usersRepo;

    public GetUsersThatLikedService(LikesRepository likesRepo, UserRepository usersRepo) {
        this.likesRepo = likesRepo;
        this.usersRepo = usersRepo;
    }

    @Override
    public List<UserDomain> getUsersThatLiked(Long productId) {
       List<Long> userIds= likesRepo.listUsersLiked(productId).stream().map(LikeDomain::getUserId).toList();
       return usersRepo.findAllUsersByIds(userIds);
    }
}
