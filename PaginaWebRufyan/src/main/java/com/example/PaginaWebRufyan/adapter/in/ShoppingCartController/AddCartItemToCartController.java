package com.example.PaginaWebRufyan.adapter.in.ShoppingCartController;

import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase.AddCartItemUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-cart/add-item")
public class AddCartItemToCartController {
    private final AddCartItemUseCase addCartItemUseCase;

    public AddCartItemToCartController(AddCartItemUseCase addCartItemUseCase) {
        this.addCartItemUseCase = addCartItemUseCase;
    }

    @PutMapping
    ResponseEntity<ShoppingCartDTO> addCartItem(CartItemCommand cartItemCommand){
        ShoppingCartDomain shoppingCartDomainUpdated = addCartItemUseCase.addCartItem(cartItemCommand);

        return ResponseEntity.ok( ShoppingCartDomainToDTO.toDTO(shoppingCartDomainUpdated));

    }

}
