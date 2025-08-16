package com.example.PaginaWebRufyan.User.DTO;


import com.example.PaginaWebRufyan.Buys.DTO.CartItemDTO;
import com.example.PaginaWebRufyan.Products.DTO.Product.ProductDTO;
import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.User.Entity.UserProfilePicture;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
public class UserEntityDTO {
    private String fullName;
    private Integer age;
    private String userName;
    @OneToOne
    private UserProfilePicture profilePicture;


    private Set<ProductDTO> favoriteProducts = new HashSet<>();

    private Set<CartItemDTO> cartProducts = new HashSet<>();




}
