package com.example.PaginaWebRufyan.adapter.in.AddressControllers;
import com.example.PaginaWebRufyan.domain.model.AddressDomain;
import com.example.PaginaWebRufyan.domain.port.in.AddressUseCase.GetUserAddressUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetUserAddressController {
    private final GetUserAddressUseCase getUserAddressUseCase;

    public GetUserAddressController(GetUserAddressUseCase getUserAddressUseCase) {
        this.getUserAddressUseCase = getUserAddressUseCase;
    }
    @GetMapping("/user-address")
    public ResponseEntity<List<AddressDomain>> getUserAddress() {
        return ResponseEntity.ok(getUserAddressUseCase.getUserAddress());
    }



}
