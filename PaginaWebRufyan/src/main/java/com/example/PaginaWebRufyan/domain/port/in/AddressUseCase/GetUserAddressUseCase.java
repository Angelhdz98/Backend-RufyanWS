package com.example.PaginaWebRufyan.domain.port.in.AddressUseCase;

import com.example.PaginaWebRufyan.domain.model.AddressDomain;

import java.util.List;

public interface GetUserAddressUseCase {
   List<AddressDomain>  getUserAddress();
}
