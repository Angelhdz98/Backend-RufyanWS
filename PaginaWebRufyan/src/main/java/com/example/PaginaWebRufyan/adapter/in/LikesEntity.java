package com.example.PaginaWebRufyan.adapter.in;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesEntity {
    private  Long likeId;
    private  Long userId;
    private  LocalDateTime likedAt;

}

