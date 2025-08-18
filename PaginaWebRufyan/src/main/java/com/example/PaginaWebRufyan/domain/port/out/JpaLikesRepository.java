package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.adapter.out.persistence.SpringDataLikesRepository;
import com.example.PaginaWebRufyan.domain.model.LikesDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.data.domain.Example;

import java.util.Optional;
import java.util.Set;

public class JpaLikesRepository implements LikesRepository{

    private final SpringDataLikesRepository springDataLikesRepo;

    public JpaLikesRepository(SpringDataLikesRepository springDataLikesRepo) {
        this.springDataLikesRepo = springDataLikesRepo;
    }


    @Override
    public Set<LikesDomain> findUserLikes(Long userId) {
       return springDataLikesRepo.findByUserId(userId);
    }

    @Override
    public LikesDomain markAsLiked(LikesDomain likesDomain) {
        return springDataLikesRepo.save(likesDomain);
    }

    @Override
    public boolean existsLike(Long userId, Long productId) {
        return springDataLikesRepo.exists(Example.of(new LikesDomain(userId,productId,null)));
    }

    @Override
    public void unmarkAsLiked(Long userId, Long productId) {
        Optional<LikesDomain> optionalLike= springDataLikesRepo.findOne(Example.of(new LikesDomain(userId,productId,null)));
        if(optionalLike.isEmpty()) throw new ResourceNotFoundException("No se ha generado ese like userId: "+ userId+ "productId: "+ productId);
        springDataLikesRepo.delete(optionalLike.get());
    }

    @Override
    public Set<UserDomain> listUsersLiked(Long productId) {
        return springDataLikesRepo.findByProductId(productId);
    }

    @Override
    public Long countLikesFromProduct(Long productId) {
        return 0L;
    }

    @Override
    public Long countUserLikes(Long userId) {
        return 0L;
    }
}
