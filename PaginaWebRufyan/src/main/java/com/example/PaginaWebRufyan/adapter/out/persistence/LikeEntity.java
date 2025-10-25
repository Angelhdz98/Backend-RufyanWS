package com.example.PaginaWebRufyan.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Likes")
@Getter
@Setter
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final Long userId;
    private final Long productId;
    private final LocalDateTime likedAt;


    public LikeEntity(Long id, Long userId, Long productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.likedAt = LocalDateTime.now();
    }
    protected  LikeEntity(){
        this.id = 0L;
        this.userId = 0L;
        this.productId= 0L;
        this.likedAt= LocalDateTime.now();

    }

}
