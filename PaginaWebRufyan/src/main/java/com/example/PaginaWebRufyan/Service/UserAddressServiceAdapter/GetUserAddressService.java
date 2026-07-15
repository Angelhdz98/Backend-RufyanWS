package com.example.PaginaWebRufyan.Service.UserAddressServiceAdapter;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.AddressDomain;
import com.example.PaginaWebRufyan.domain.port.in.AddressUseCase.GetUserAddressUseCase;
import com.example.PaginaWebRufyan.domain.port.out.AddressRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserAddressService implements GetUserAddressUseCase {
    private final CurrentUserService currentUserService;
    private final AddressRepositoryPort addressRepositoryPort;

    public GetUserAddressService(CurrentUserService currentUserService, AddressRepositoryPort addressRepositoryPort) {
        this.currentUserService = currentUserService;
        this.addressRepositoryPort = addressRepositoryPort;
    }

    @Override
    public List<AddressDomain>  getUserAddress() {
           Long userId= currentUserService.getCurrentUser().getId();
        return addressRepositoryPort.findAllByUserId(userId);

    }
}
