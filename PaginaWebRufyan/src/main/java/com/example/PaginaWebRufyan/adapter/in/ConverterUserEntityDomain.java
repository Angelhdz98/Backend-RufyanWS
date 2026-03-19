package com.example.PaginaWebRufyan.adapter.in;

import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import org.springframework.stereotype.Component;

@Component
public class ConverterUserEntityDomain {

    public ConverterUserEntityDomain(){}

    public  UserEntity convertToEntity(UserDomain userDomain){
        return new UserEntity(userDomain.getId(),userDomain.getFullname(),userDomain.getBirthDate(),userDomain.getUsername(),userDomain.getEmail(), userDomain.getHashedPassword(), userDomain.getRole());
    }

    public  UserDomain convertToDomain(UserEntity userEntity) {
       return new UserDomain(userEntity.getId(),userEntity.getFullName(), new BirthDate(userEntity.getBirthDate()),userEntity.getUsername(),userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole());
    }
    public  UserDomain convertToDomainWihRole(UserEntity userEntity) {
        return new UserDomain(userEntity.getId(),userEntity.getFullName(), new BirthDate(userEntity.getBirthDate()),userEntity.getUsername(),userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole());
    }
}
