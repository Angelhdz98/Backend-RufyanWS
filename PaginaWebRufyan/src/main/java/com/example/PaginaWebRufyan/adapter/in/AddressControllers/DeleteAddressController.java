package com.example.PaginaWebRufyan.adapter.in.AddressControllers;

import com.example.PaginaWebRufyan.domain.port.in.AddressUseCase.DeleteUserAddressUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteAddressController {
    private final DeleteUserAddressUseCase deleteUserAddressUseCase;

    public DeleteAddressController(DeleteUserAddressUseCase deleteUserAddressUseCase) {
        this.deleteUserAddressUseCase = deleteUserAddressUseCase;
    }

    @DeleteMapping("/user-address/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        deleteUserAddressUseCase.delete(addressId);
        return ResponseEntity.noContent().build();
    }

}
