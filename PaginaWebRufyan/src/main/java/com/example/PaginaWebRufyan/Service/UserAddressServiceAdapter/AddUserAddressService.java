package com.example.PaginaWebRufyan.Service.UserAddressServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.AddressDomain;
import com.example.PaginaWebRufyan.domain.port.in.AddressUseCase.AddUserAddressUseCase;
import com.example.PaginaWebRufyan.domain.port.out.AddressRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddUserAddressService implements AddUserAddressUseCase {

 private final AddressRepositoryPort addressRepositoryPort;

    public AddUserAddressService(AddressRepositoryPort addressRepositoryPort) {
        this.addressRepositoryPort = addressRepositoryPort;
    }

    @Override
 public List<AddressDomain>  addUserAddress(AddressDomain addressDomain) {

     List<AddressDomain> allByUserId = addressRepositoryPort.findAllByUserId(addressDomain.userId());
     if(allByUserId.size() >= 3){
         throw new RuntimeException("No se puede agregar más de 3 direcciones para un usuario");
     }
     addressRepositoryPort.save(addressDomain);
     return addressRepositoryPort.findAllByUserId(addressDomain.userId());
 }
}
