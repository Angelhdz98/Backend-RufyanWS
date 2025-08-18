package com.example.PaginaWebRufyan.Returns.Repository;

import com.example.PaginaWebRufyan.Returns.Entity.ReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRepository extends JpaRepository<ReturnEntity, Long> {
}
