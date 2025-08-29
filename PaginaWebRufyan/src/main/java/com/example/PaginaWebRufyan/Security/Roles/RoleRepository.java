package com.example.PaginaWebRufyan.Security.Roles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PaginaWebRufyan.Security.Entity.RoleEntity;

//import java.util.Optional;

//import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

		Optional<RoleEntity> findByRoleEnum(RoleEnum role);
	

}