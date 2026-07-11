package com.example.PaginaWebRufyan.Service.UserAddressServiceAdapter;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.AddressDomain;
import com.example.PaginaWebRufyan.domain.port.in.AddressUseCase.DeleteUserAddressUseCase;
import com.example.PaginaWebRufyan.domain.port.out.AddressRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserAddressService implements DeleteUserAddressUseCase {
    private final AddressRepositoryPort addressRepositoryPort;
    private final CurrentUserService currentUserService;

    public DeleteUserAddressService(AddressRepositoryPort addressRepositoryPort, CurrentUserService currentUserService) {
        this.addressRepositoryPort = addressRepositoryPort;
        this.currentUserService = currentUserService;
    }

    @Override
    public void delete(Long addressId) {
        Long userId = currentUserService.getCurrentUser().getId();
        AddressDomain byId = addressRepositoryPort.findById(addressId);
        if(byId.userId().equals(userId)){
            throw new RuntimeException("You are not authorized to delete this address");
        }
     addressRepositoryPort.deleteById(addressId);
    }
}
