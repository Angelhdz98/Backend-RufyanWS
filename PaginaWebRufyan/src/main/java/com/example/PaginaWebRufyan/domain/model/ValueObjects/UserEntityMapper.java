package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.DTO.UserEntityDTO2;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.stereotype.Component;

@Component

public class UserEntityMapper {
    public UserEntityMapper (){}
 public  UserEntityDTO2 toDto(UserDomain userDomain){
    return new UserEntityDTO2(userDomain.getId(),
            userDomain.getFullname().getFullName(),
            userDomain.getBirthDate().getBirthDate().toString(),
            userDomain.getEmail(),
            userDomain.getUsername());
}



}
