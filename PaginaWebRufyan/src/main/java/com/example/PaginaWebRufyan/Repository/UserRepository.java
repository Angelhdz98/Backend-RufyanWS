package com.example.PaginaWebRufyan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.example.PaginaWebRufyan.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findUserByEmail(String email);
	
	Optional<UserEntity> findUserByUsername(String userName);
}
