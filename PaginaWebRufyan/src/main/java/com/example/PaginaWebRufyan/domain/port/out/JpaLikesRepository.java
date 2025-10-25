package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.adapter.in.LikeControllers.LikeCommand;
import com.example.PaginaWebRufyan.adapter.out.persistence.LikeEntity;
import com.example.PaginaWebRufyan.adapter.out.persistence.SpringDataLikesRepository;
import com.example.PaginaWebRufyan.domain.model.LikeDomain;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class JpaLikesRepository implements LikesRepositoryPort {

    private final SpringDataLikesRepository springDataLikesRepo;

    public JpaLikesRepository(SpringDataLikesRepository springDataLikesRepo) {
        this.springDataLikesRepo = springDataLikesRepo;
    }


    @Override
    public Set<LikeDomain> findUserLikes(Long userId) {
       return  springDataLikesRepo.findByUserId(userId).stream().map(LikeMapper::mapToDomain).collect(Collectors.toSet());
    }

    @Override
    public Page<LikeDomain> findUserLikes(Long userId, Pageable pageable) {
        return springDataLikesRepo.findByUserId(userId,pageable).map(LikeMapper::mapToDomain);
    }

    @Override
    public LikeDomain markAsLiked(LikeCommand likeCommand) {

        return LikeMapper.mapToDomain(springDataLikesRepo.save(new LikeEntity(null,likeCommand.userId(),likeCommand.productId()) ));
    }

    @Override
    public boolean existsLike(Long userId, Long productId) {
        return springDataLikesRepo.exists(Example.of(new LikeEntity(null,userId,productId)));
     }

    @Override
    public void unmarkAsLiked(Long userId,Long productId) {
        Optional<LikeDomain> optionalLike= springDataLikesRepo.findOne(Example.of(new LikeEntity(null, userId,productId))).map(LikeMapper::mapToDomain);
        if(optionalLike.isEmpty()) throw new ResourceNotFoundException("No se ha generado ese like userId: "+ userId+ "productId: "+ productId);
        springDataLikesRepo.delete(LikeMapper.mapToEntity(optionalLike.get()));
    }

    @Override
    public Set<LikeDomain> listUsersLiked(Long productId) {
        return springDataLikesRepo.findByProductId(productId).stream().map(LikeMapper::mapToDomain).collect(Collectors.toSet());
    }

    @Override
    public Page<LikeDomain> listUsersLiked(Long productId, Pageable page) {
        return springDataLikesRepo.findByProductId(productId,page).map(LikeMapper::mapToDomain);
    }

    @Override
    public Long countLikesFromProduct(Long productId) {
        return springDataLikesRepo.countLikesByProductId(productId);
    }

    @Override
    public Long countUserLikes(Long userId) {
        return springDataLikesRepo.countLikesByUserId(userId);
    }

}
