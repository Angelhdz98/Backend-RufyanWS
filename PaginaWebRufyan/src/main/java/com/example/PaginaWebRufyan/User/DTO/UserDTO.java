package com.example.PaginaWebRufyan.User.DTO;


import com.example.PaginaWebRufyan.Buys.DTO.CartItemDTO;
import com.example.PaginaWebRufyan.User.Entity.UserProfilePicture;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;


@Getter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private FullName fullName;
    private BirthDate birthDate;
    private String username;
    private String email;
    //private boolean isEmailVerified;
    //@OneToOne
    //private UserProfilePicture profilePicture;
    //private Set<CartItemDTO> cartProducts;
}
