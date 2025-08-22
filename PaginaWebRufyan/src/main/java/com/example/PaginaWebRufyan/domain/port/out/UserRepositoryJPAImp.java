package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.adapter.in.ConverterUserEntityDomain;
import com.example.PaginaWebRufyan.adapter.out.persistence.SpringDataUserRepository;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryJPAImp implements UserRepository {


    private final SpringDataUserRepository persistenceRepo;

    public UserRepositoryJPAImp(SpringDataUserRepository persistenceRepo)
    {
        this.persistenceRepo= persistenceRepo;
    }

    @Override
    public Optional<UserDomain> findUserById(Long userId) {
         return persistenceRepo.findById(userId).map(ConverterUserEntityDomain::convertToDomain);

    }

    @Override
    public Optional<UserDomain> findUserByUsername(String username) {
        return persistenceRepo.findByUsername(username).map(ConverterUserEntityDomain::convertToDomain);
    }

    @Override
    public List<UserDomain> findAllUsersByIds(List<Long> userIds) {
       return  persistenceRepo.findAllById(userIds)
               .stream()
               .map(ConverterUserEntityDomain::convertToDomain)
               .collect(Collectors.toList());
    }

    @Override
    public Page<UserDomain> findAllUsersWhoLikedProduct(Long productId, Pageable pageable) {
        return persistenceRepo.findUsersWhoLikedPainting(productId,pageable).map(ConverterUserEntityDomain::convertToDomain);
    }


    @Override
    public UserDomain retrieveUserById(Long userId) {
         return ConverterUserEntityDomain
                 .convertToDomain(persistenceRepo.findById(userId)
                 .orElseThrow(()-> new ResourceNotFoundException("usuario no encontrado con el id: "+ userId) ));
    }

    @Override
    public UserDomain retrieveUserByUsername(String username) {
        return ConverterUserEntityDomain.convertToDomain(persistenceRepo.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("No se encontró el usuario con el username: ")));
    }


    @Override
    public UserDomain saveUser(UserDomain userDomain) {
         return ConverterUserEntityDomain.convertToDomain(persistenceRepo.save(ConverterUserEntityDomain.convertToEntity(userDomain)));
    }

    @Override
    public void deleteById(Long userId) {
        persistenceRepo.deleteById(userId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return persistenceRepo.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username)
    {
        return persistenceRepo.existsByUsername(username);
    }

    @Override
    public void deleteUser(UserDomain userDomain) {
        persistenceRepo.delete(ConverterUserEntityDomain.convertToEntity(userDomain));
    }
}
