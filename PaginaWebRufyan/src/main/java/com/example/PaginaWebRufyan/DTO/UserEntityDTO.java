package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.UserEntity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Getter
@AllArgsConstructor
public class UserEntityDTO {
    private String fullName;
    private Integer age;
    private String userName;
    @OneToOne
    private Image profilePicture;

    public UserEntityDTO(UserEntity userEntity){
        this.userName = userEntity.getUsername();
        this.fullName = userEntity.getName() + " " + userEntity.getLastname();
        this.age = (int) ChronoUnit.YEARS.between(userEntity.getBirthDate(),LocalDate.now());// casting

    }

}
