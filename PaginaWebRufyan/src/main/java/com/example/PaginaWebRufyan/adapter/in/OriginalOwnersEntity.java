package com.example.PaginaWebRufyan.adapter.in;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OriginalOwnersEntity {
    private  Long productId;
    private  Long userId;
    private  LocalDateTime likedAt;


}
