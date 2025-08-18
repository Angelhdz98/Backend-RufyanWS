package com.example.PaginaWebRufyan.Products.Repository;

import com.example.PaginaWebRufyan.Products.Entity.BodyClothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyClothingRepository extends JpaRepository<BodyClothing,Integer> {

}
