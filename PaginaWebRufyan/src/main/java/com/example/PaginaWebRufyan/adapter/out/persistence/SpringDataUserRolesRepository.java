package com.example.PaginaWebRufyan.adapter.out.persistence;

import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRolesRepository extends JpaRepository<UserEntity, Long> {

}
