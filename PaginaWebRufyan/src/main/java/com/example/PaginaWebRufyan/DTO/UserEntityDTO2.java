package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
public class UserEntityDTO2 {
    private final Long id;
    private final String fullname;
    private final LocalDate birthDate;
    private final String email;
    private final String username;

 public UserEntityDTO2(UserDomain user){
     this.id = user.getId();
     this.fullname = user.getFullname().getFullName();
     this.birthDate = user.getBirthDate().getBirthDate();
     this.email = user.getEmail();
     this.username= user.getUsername();

 }

}
