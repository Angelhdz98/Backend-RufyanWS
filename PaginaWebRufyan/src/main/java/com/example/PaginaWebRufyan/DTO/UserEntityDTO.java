package com.example.PaginaWebRufyan.DTO;


import com.example.PaginaWebRufyan.Entity.CartItem;
import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Entity.UserProfilePicture;
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

    private Set<CartItem> cartProducts = new HashSet<>();


    public UserEntityDTO(UserEntity userEntity){
        this.userName = userEntity.getUsername();
        this.fullName = userEntity.getName() + " " + userEntity.getLastname();
        this.age = (int) ChronoUnit.YEARS.between(userEntity.getBirthDate(),LocalDate.now());// casting
        this.profilePicture = userEntity.getProfilePicture();
        this.favoriteProducts = userEntity.getFavoriteProducts().stream().map(ProductDTO::new).collect(Collectors.toSet());
        this.cartProducts = userEntity.getShoppingCart().getItemList();
    }

}
