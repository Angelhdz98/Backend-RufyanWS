package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.DTO.AddAddressCommand;
import com.example.PaginaWebRufyan.User.Entity.AddressEntity;
import com.example.PaginaWebRufyan.domain.model.AddressDomain;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressEntity toEntity(AddressDomain addressDomain){
        return new AddressEntity(addressDomain.id(),
                addressDomain.userId(),
                addressDomain.street(),
                addressDomain.city(),
                addressDomain.state(),
                addressDomain.zipCode(),
                addressDomain.country(),
                addressDomain.isDefault());
    }

    public AddressDomain toDomain(AddressEntity addressEntity){
        return new AddressDomain(addressEntity.getId(),
                addressEntity.getUserId(),
                addressEntity.getStreet(),
                addressEntity.getCity(),
                addressEntity.getState(),
                addressEntity.getZipCode(),
                addressEntity.getCountry(),
                addressEntity.isDefault());
    }

    public AddressDomain toDomainFromCommand(AddAddressCommand addAddressCommand){
        boolean isEmpty = addAddressCommand.intNumber().isEmpty();
        String streetData =
                addAddressCommand.streetName() + " " + addAddressCommand.extNumber() + " " + (isEmpty?"":("int "+ addAddressCommand.intNumber())) + " " + addAddressCommand.neighborhood() + " " + addAddressCommand.city() + " " + addAddressCommand.zipCode() + " " + addAddressCommand.country();
        System.out.println("Street Data: " + streetData);
     return   new AddressDomain(addAddressCommand.id(),addAddressCommand.userId(),
             streetData,
             addAddressCommand.city(),
                addAddressCommand.state(),
             addAddressCommand.zipCode(),
             addAddressCommand.country(),
             addAddressCommand.isDefault());
    }

}
