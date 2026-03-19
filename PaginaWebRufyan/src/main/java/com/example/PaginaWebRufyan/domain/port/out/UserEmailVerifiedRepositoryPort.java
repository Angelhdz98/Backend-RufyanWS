package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.User.Entity.UserEmailVerifiedEntity;

public interface UserEmailVerifiedRepositoryPort {

    UserEmailVerifiedEntity setEmailToNoVerified(Long userId);
    UserEmailVerifiedEntity setEmailToVerified(Long userId);

    UserEmailVerifiedEntity setEmailVerification(Long userId, Boolean verified);

    UserEmailVerifiedEntity createVerification(Long userId);

}
