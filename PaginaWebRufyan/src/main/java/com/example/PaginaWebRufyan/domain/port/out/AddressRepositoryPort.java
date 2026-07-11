package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.domain.model.AddressDomain;

import java.util.List;


public interface AddressRepositoryPort {
    AddressDomain save(AddressDomain addressDomain);
    AddressDomain findById(Long id);
    List<AddressDomain> findAllByUserId(Long userId);
    void deleteById(Long id);
}
