package com.example.PaginaWebRufyan.User.DTO;


import com.example.PaginaWebRufyan.Buys.DTO.CartItemDTO;
import com.example.PaginaWebRufyan.User.Entity.UserProfilePicture;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;


@Getter
@AllArgsConstructor
public class UserEntityDTO {
    private String fullName;
    private Integer age;
    private String userName;
    @OneToOne
    private UserProfilePicture profilePicture;



    private Set<CartItemDTO> cartProducts = new HashSet<>();




}
