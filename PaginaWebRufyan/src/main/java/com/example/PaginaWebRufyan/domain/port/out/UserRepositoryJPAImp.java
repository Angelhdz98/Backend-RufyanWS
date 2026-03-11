package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.adapter.in.ConverterUserEntityDomain;
import com.example.PaginaWebRufyan.adapter.out.persistence.SpringDataUserRepository;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryJPAImp implements UserRepositoryPort {


    private final SpringDataUserRepository persistenceRepo;
    private final ConverterUserEntityDomain converterUserEntityDomain;

    public UserRepositoryJPAImp(SpringDataUserRepository persistenceRepo, ConverterUserEntityDomain converterUserEntityDomain)
    {
        this.persistenceRepo= persistenceRepo;
        this.converterUserEntityDomain = converterUserEntityDomain;
    }

    @Override
    public Optional<UserDomain> findUserById(Long userId) {
         return persistenceRepo.findById(userId).map(converterUserEntityDomain::convertToDomain);

    }

    @Override
    public Optional<UserDomain> findUserByUsername(String username) {
        return persistenceRepo.findByUsername(username).map(converterUserEntityDomain::convertToDomain);
    }

    @Override
    public Optional<UserDomain> findUserByEmail(String email) {
        return persistenceRepo.findByEmail(email).map(converterUserEntityDomain::convertToDomain);
    }

    @Override
    public List<UserDomain> findAllUsersByIds(List<Long> userIds) {
       return  persistenceRepo.findAllById(userIds)
               .stream()
               .map(converterUserEntityDomain::convertToDomain)
               .collect(Collectors.toList());
    }

    @Override
    public Page<UserDomain> findAllUsersWhoLikedProduct(Long productId, Pageable pageable) {
        return persistenceRepo.findUsersWhoLikedProduct(productId,pageable).map(converterUserEntityDomain::convertToDomain);
    }

    @Override
    public Page<UserDomain> findUsersByUsernameMatch(String usernameParte, Pageable pageable) {
        return persistenceRepo.findByUsernameContainingIgnoreCase(usernameParte, pageable).map(converterUserEntityDomain::convertToDomain);
    }

    @Override
    public Page<UserDomain> findUsersByNameMatch(String fullNamePart, Pageable pageable) {
        return persistenceRepo.findByStringFullNameContainingIgnoreCase(fullNamePart,pageable).map(converterUserEntityDomain::convertToDomain);
    }

    @Override
    public Page<UserDomain> findUsersByEmailMatch(String emailPart, Pageable pageable) {
        return persistenceRepo.findByEmailContainingIgnoreCase(emailPart,pageable).map(converterUserEntityDomain::convertToDomain
        );
    }

    @Override
    public Page<UserDomain> findUsersPaged(Pageable pageable) {
        return persistenceRepo.findAll(pageable).map(converterUserEntityDomain::convertToDomain);
    }


    @Override
    public UserDomain retrieveUserById(Long userId) {
         return converterUserEntityDomain
                 .convertToDomain(persistenceRepo.findById(userId)
                 .orElseThrow(()-> new ResourceNotFoundException("usuario no encontrado con el id: "+ userId) ));
    }

    @Override
    public UserDomain retrieveUserByUsername(String username) {
        return converterUserEntityDomain.convertToDomain(persistenceRepo.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("No se encontró el usuario con el username: ")));
    }

    @Override
    public UserDomain retrieveUserByEmail(String email) {
         return converterUserEntityDomain.convertToDomainWihRole( persistenceRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("No existe el usuario con el email: "+email )));

    }


    @Override
    public UserDomain saveUser(UserDomain userDomain) {
        Optional<UserEntity> byEmail = persistenceRepo.findByEmail(userDomain.getEmail());
        Optional<UserEntity> byUsername = persistenceRepo.findByUsername(userDomain.getUsername());
        if(!userDomain.getBirthDate().isAdult()|| userDomain.getBirthDate().getBirthDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Fecha invalida, no se permite acceso a menores de edad");
        if( byEmail.isPresent() && !byEmail.get().getId().equals(userDomain.getId()) ) throw new AlreadyExistIdenticatorException("El email: "+ userDomain.getEmail()+ " ya está registrado ");
        if(byUsername.isPresent() && !byUsername.get().getId().equals(userDomain.getId())) {
            throw new AlreadyExistIdenticatorException("El username: " + userDomain.getUsername() + " ya está registrado ");
        }
         return converterUserEntityDomain.convertToDomainWihRole(persistenceRepo.save(converterUserEntityDomain.convertToEntity(userDomain)));
    }

    //it does not update Email and password
    @Override
    public UserDomain updateUser(UserDomain userDomain) {

        UserEntity userEntity = persistenceRepo.findById(userDomain.getId()).orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el id: " + userDomain.getId()));
        userEntity.setFullName(userDomain.getFullname());
        if(!userDomain.getBirthDate().isAdult()|| userDomain.getBirthDate().getBirthDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Fecha invalida, no se permite acceso a menores de edad");
        userEntity.setBirthDate(userDomain.getBirthDate().getBirthDate());

        if(!userDomain.getUsername().equals(userEntity.getUsername()) && persistenceRepo.existsByUsername(userDomain.getUsername()) )
            throw new AlreadyExistIdenticatorException("El username: "+ userDomain.getUsername()+ " ya está registrado ");
        userEntity.setUsername(userDomain.getUsername());

        return converterUserEntityDomain.convertToDomain(persistenceRepo.save(userEntity));


    }

    @Override
    public UserDomain updateUserPassword(Long userId, String newHashedPassword) {
        UserEntity userEntity = converterUserEntityDomain.convertToEntity(retrieveUserById(userId));
        userEntity.setPassword(newHashedPassword);
        return converterUserEntityDomain.convertToDomain(persistenceRepo.save(userEntity)) ;
    }

    @Override
    public UserDomain UpdateUserEmail(Long userId, String newEmail) {
        UserEntity userEntity = converterUserEntityDomain.convertToEntity(retrieveUserById(userId));
        userEntity.setEmail(newEmail);
        return converterUserEntityDomain.convertToDomain(persistenceRepo.save(userEntity)) ;
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
    public boolean existById(Long userId) {
        return persistenceRepo.existsById(userId);
    }

    @Override
    public void deleteUser(UserDomain userDomain) {
        persistenceRepo.delete(converterUserEntityDomain.convertToEntity(userDomain));
    }
}
