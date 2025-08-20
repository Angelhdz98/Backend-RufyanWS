package com.example.PaginaWebRufyan.adapter.in;

import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.User.Entity.UserProfilePicture;
import com.example.PaginaWebRufyan.domain.model.UserDomain;

public class ConverterUserEntityDomain {

   public static UserEntity convertToEntity(UserDomain userDomain, UserProfilePicture profilePicture){
        return new UserEntity(userDomain.getId(),userDomain.getFullname(),userDomain.getBirthDate(),userDomain.getUsername(),userDomain.getEmail());
    }


    public static UserEntity convertToEntity(UserDomain userDomain){
        return new UserEntity(userDomain.getId(),userDomain.getFullname(),userDomain.getBirthDate(),userDomain.getUsername(),userDomain.getEmail());
    }
    public static UserDomain convertToDomain(UserEntity userEntity, String email,UserProfilePicture profilePicture){
        return new UserDomain(userEntity.getId(),userEntity.getFullName(), userEntity.getBirthDate(),userEntity.getUsername(),userEntity.getEmail());
    }

    public static UserDomain convertToDomain(UserEntity userEntity) {
       return new UserDomain(userEntity.getId(),userEntity.getFullName(), userEntity.getBirthDate(),userEntity.getUsername(),userEntity.getEmail());
    }
}
