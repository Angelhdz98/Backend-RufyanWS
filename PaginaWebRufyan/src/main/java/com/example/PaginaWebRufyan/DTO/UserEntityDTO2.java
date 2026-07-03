package com.example.PaginaWebRufyan.DTO;


import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;

public record UserEntityDTO2(Long id, FullName fullname, String birthDate,
                             String email, String username) {


 }


