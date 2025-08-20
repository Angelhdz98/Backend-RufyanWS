
package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.domain.model.UserDomain;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDomain> findUserById(Long userId);
    Optional<UserDomain> findUserByUsername(String username);
    UserDomain retrieveUserById(Long userId);
    UserDomain retrieveUserByUsername(String username);
    UserDomain saveUser(UserDomain userDomain);
    void deleteById(Long userId);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    void deleteUser(UserDomain userDomain);


}
