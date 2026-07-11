package com.example.PaginaWebRufyan.DTO;

public record AddAddressCommand( Long id,
    Long userId,
    String streetName,
    String extNumber,
    String intNumber,
    String neighborhood,
    String city,
    String state,
    String zipCode,
    String country,
    boolean isDefault
) {
}