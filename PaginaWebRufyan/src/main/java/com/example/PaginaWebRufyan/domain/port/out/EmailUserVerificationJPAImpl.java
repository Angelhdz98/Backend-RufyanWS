package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.User.Entity.UserEmailVerifiedEntity;
import org.springframework.stereotype.Repository;

@Repository
public class EmailUserVerificationJPAImpl implements UserEmailVerifiedRepositoryPort {
    private final UserEmailVerifiedRepository userEmailVerifiedRepository;


    public EmailUserVerificationJPAImpl(UserEmailVerifiedRepository userEmailVerifiedRepository) {
        this.userEmailVerifiedRepository = userEmailVerifiedRepository;
    }
    public UserEmailVerifiedEntity retrieveEmailVerifiedEntityByUserId(Long userId) {
        return userEmailVerifiedRepository.findByUserId(userId).orElseThrow(()->new ResourceNotFoundException("El usuario no ha verificado su correo"));
    }

    @Override
    public UserEmailVerifiedEntity setEmailToNoVerified(Long userId) {
        UserEmailVerifiedEntity byUserId = retrieveEmailVerifiedEntityByUserId(userId);
        byUserId.setIsEmailVerified(false);
        return userEmailVerifiedRepository.save(byUserId);
    }

    @Override
    public UserEmailVerifiedEntity setEmailToVerified(Long userId) {
        UserEmailVerifiedEntity byUserId = retrieveEmailVerifiedEntityByUserId(userId);
        byUserId.setIsEmailVerified(true);
        return userEmailVerifiedRepository.save(byUserId);
    }

    @Override
    public UserEmailVerifiedEntity setEmailVerification(Long userId, Boolean verified) {
        UserEmailVerifiedEntity byUserId = retrieveEmailVerifiedEntityByUserId(userId);
        byUserId.setIsEmailVerified(verified);
        return userEmailVerifiedRepository.save(byUserId);
    }

    @Override
    public UserEmailVerifiedEntity createVerification(Long userId) {
        return userEmailVerifiedRepository.save(new UserEmailVerifiedEntity(0L,userId,false));
    }
}
