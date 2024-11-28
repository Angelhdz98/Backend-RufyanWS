package com.example.PaginaWebRufyan.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PaginaWebRufyan.Entity.Painting;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findUserByEmail(String email);
	
	Optional<UserEntity> findUserByUsername(String userName);
	@Query("SELECT u FROM UserEntity u JOIN FETCH u.favoriteProducts WHERE u.id = :id")
	Optional<UserEntity> findByIdWithFavoriteProducts(@Param("id") Integer id);
	Optional<UserEntity> findByUsername(String username);
	Optional<UserEntity> findByEmail(String email);
	
	List <UserEntity> findByNameContaining(String namePart);
	List<UserEntity> findByUsernameContaining(String usernamePart);
	List<UserEntity> findByEmailContaining(String emailPart);
	
	
	boolean existByUserName(String username);
	boolean existByEmail(String username);
	
}
