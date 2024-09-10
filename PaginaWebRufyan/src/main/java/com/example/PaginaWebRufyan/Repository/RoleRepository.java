package com.example.PaginaWebRufyan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PaginaWebRufyan.Entity.RoleEntity;
import com.example.PaginaWebRufyan.Utils.RoleEnum;

//import java.util.Optional;

//import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

		Optional<RoleEntity> findByRoleEnum(RoleEnum role);
	

}