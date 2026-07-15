package com.example.PaginaWebRufyan.adapter.in.AddressControllers;


import com.example.PaginaWebRufyan.DTO.AddAddressCommand;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.AddressDomain;
import com.example.PaginaWebRufyan.domain.port.in.AddressUseCase.AddUserAddressUseCase;
import com.example.PaginaWebRufyan.domain.port.out.AddressMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddAddressController {
    private final AddUserAddressUseCase addUserAddressUseCase;
    private final CurrentUserService currentUserService;
    private final AddressMapper addressMapper;


    public AddAddressController(AddUserAddressUseCase addUserAddressUseCase, CurrentUserService currentUserService, AddressMapper addressMapper) {
        this.addUserAddressUseCase = addUserAddressUseCase;
        this.currentUserService = currentUserService;
        this.addressMapper = addressMapper;
    }

    @PostMapping("/user-address")
    public ResponseEntity<List<AddressDomain>> addAddressToUser(@RequestBody AddAddressCommand addAddressCommand){

        Long userId = currentUserService.getCurrentUser().getId();

        AddAddressCommand newAddAddressCommand =
                new AddAddressCommand(0L,
                        userId,
                        addAddressCommand.streetName(),
                        addAddressCommand.extNumber(),
                        addAddressCommand.intNumber(),
                        addAddressCommand.neighborhood(),
                        addAddressCommand.city(),
                        addAddressCommand.state(),
                        addAddressCommand.zipCode(),
                        addAddressCommand.country(),
                        addAddressCommand.isDefault());

        List<AddressDomain> addressDomains =
                addUserAddressUseCase.addUserAddress(addressMapper.toDomainFromCommand(newAddAddressCommand));

        return ResponseEntity.ok(addressDomains);
    }

}
