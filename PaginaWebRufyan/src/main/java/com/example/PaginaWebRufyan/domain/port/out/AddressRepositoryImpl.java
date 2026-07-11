package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.adapter.out.persistence.AddressJpaRepository;
import com.example.PaginaWebRufyan.domain.model.AddressDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AddressRepositoryImpl implements AddressRepositoryPort{

    private final AddressJpaRepository addressJpaRepository;
    private final AddressMapper addressMapper;

    public AddressRepositoryImpl(AddressJpaRepository addressJpaRepository, AddressMapper addressMapper) {
        this.addressJpaRepository = addressJpaRepository;

        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDomain save(AddressDomain addressDomain) {

        return addressMapper.toDomain(addressJpaRepository.save(addressMapper.toEntity(addressDomain)));
    }

    @Override
    public AddressDomain findById(Long id) {
        return addressJpaRepository.findById(id)
                .map(addressMapper::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la dirección con el id: " + id));
    }

    @Override
    public List<AddressDomain> findAllByUserId(Long userId) {
        return addressJpaRepository.findAllByUserId(userId).stream().map(addressMapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        addressJpaRepository.deleteById(id);
    }

    }

