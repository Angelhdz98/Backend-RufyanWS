package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserEditableDTO {
    // user data use for make user changes
    private String name;
    private String lastname;
    private LocalDate birthDate;
    private String email;
    private String username;
    //password will hava his own end point to realize changes

    public UserEditableDTO (UserEntity userEntity){

    }
}
