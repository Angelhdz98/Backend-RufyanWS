package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.domain.model.UserDomain;

public class UserEntityMapper {

 public static UserEntityDTO2 toDto(UserDomain userDomain){
    return new UserEntityDTO2(userDomain.getId(),userDomain.getFullname().getFullName(),userDomain.getBirthDate().toString(), userDomain.getEmail(), userDomain.getUsername());
}
}
