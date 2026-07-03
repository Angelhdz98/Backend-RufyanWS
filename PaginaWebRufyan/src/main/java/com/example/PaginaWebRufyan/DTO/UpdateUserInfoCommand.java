package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;

import java.time.LocalDate;

public record UpdateUserInfoCommand( Long id,
                                    FullName fullName,
                                    LocalDate birthDate) {
}
