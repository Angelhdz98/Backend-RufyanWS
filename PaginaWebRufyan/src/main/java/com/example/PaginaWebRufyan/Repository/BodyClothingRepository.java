package com.example.PaginaWebRufyan.Repository;

import com.example.PaginaWebRufyan.Entity.BodyClothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyClothingRepository extends JpaRepository<BodyClothing,Integer> {

}
