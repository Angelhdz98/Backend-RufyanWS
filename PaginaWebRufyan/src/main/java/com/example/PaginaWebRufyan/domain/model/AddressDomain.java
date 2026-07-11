package com.example.PaginaWebRufyan.domain.model;

public record AddressDomain(Long id,
                            Long userId,
                            String street,
                            String city,
                            String state,
                            String zipCode,
                            String country,
                            boolean isDefault) {
}
