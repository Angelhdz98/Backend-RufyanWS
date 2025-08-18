package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.adapter.out.persistence.SpringDataUserRepository;
import com.example.PaginaWebRufyan.domain.model.UserDomain;

import java.util.Optional;

public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository persistenceRepo;

    public JpaUserRepository(SpringDataUserRepository persistenceRepo){
        this.persistenceRepo= persistenceRepo;
    }

    @Override
    public Optional<UserDomain> findUserById(Long userId) {
        return persistenceRepo.findById(userId);
    }

    @Override
    public Optional<UserDomain> findUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public UserDomain saveUser(UserDomain userDomain) {
        return null;
    }

    @Override
    public void delete(Long userId) {

    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public void deleteUser(UserDomain userDomain) {

    }
}
