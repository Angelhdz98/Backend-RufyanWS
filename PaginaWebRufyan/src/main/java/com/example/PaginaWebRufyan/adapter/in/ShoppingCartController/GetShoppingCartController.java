package com.example.PaginaWebRufyan.adapter.in.ShoppingCartController;

import com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase.GetShoppingCartUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetShoppingCartController {

    private final GetShoppingCartUseCase getShoppingCartUseCase;
    private final ShoppingCartDomainToDTO shoppingCartDomainToDTO;
    public GetShoppingCartController(GetShoppingCartUseCase getShoppingCartUseCase, ShoppingCartDomainToDTO shoppingCartDomainToDTO) {
        this.getShoppingCartUseCase = getShoppingCartUseCase;
        this.shoppingCartDomainToDTO = shoppingCartDomainToDTO;
    }

    @GetMapping("/shopping-cart")
    ResponseEntity<ShoppingCartDTO> getShoppingCart(){
      return ResponseEntity.ok(shoppingCartDomainToDTO.toDTO(getShoppingCartUseCase.getCart()));
    }
}
