
package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<UserDomain> findUserById(Long userId);
    Optional<UserDomain> findUserByUsername(String username);
    List<UserDomain> findAllUsersByIds(List<Long> userIds);
    Page<UserDomain> findAllUsersWhoLikedProduct(Long productId, Pageable pageable);
    Page<UserDomain> findUsersByUsernameMatch(String usernameParte, Pageable pageable);
    Page<UserDomain> findUsersByNameMatch(String fullNamePart, Pageable pageable);

    UserDomain retrieveUserById(Long userId);
    UserDomain retrieveUserByUsername(String username);
    UserDomain saveUser(UserDomain userDomain);
    void deleteById(Long userId);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    void deleteUser(UserDomain userDomain);


}
