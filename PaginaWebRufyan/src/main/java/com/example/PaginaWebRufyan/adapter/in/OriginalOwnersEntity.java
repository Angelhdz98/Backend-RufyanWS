package com.example.PaginaWebRufyan.adapter.in;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OriginalOwnersEntity {
    @Id
    @GeneratedValue
    private Long ownerShipId;
    private  Long productId;
    private  Long userId;
    private  LocalDateTime likedAt;


}
