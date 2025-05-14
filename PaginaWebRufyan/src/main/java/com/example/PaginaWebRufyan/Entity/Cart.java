package com.example.PaginaWebRufyan.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode
@Entity
public class Cart {
private int id; 
private UserEntity user;
private List<CartItem> cartItems;
private LocalDate createdAt;
private LocalDate updatedAt;

}
