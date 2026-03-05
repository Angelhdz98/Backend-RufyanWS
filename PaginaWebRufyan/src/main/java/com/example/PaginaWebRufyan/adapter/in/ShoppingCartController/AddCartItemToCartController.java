package com.example.PaginaWebRufyan.adapter.in.ShoppingCartController;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase.AddCartItemUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AddCartItemToCartController {
    private final AddCartItemUseCase addCartItemUseCase;
    private final CurrentUserService currentUserService;
    private final ShoppingCartDomainToDTO shoppingCartDomainToDTO;
    public AddCartItemToCartController(AddCartItemUseCase addCartItemUseCase, CurrentUserService currentUserService, ShoppingCartDomainToDTO shoppingCartDomainToDTO) {
        this.addCartItemUseCase = addCartItemUseCase;
        this.currentUserService = currentUserService;
        this.shoppingCartDomainToDTO = shoppingCartDomainToDTO;
    }

    @PutMapping("/shopping-cart/add-item")
    ResponseEntity<ShoppingCartDTO> addCartItem(@RequestPart Long productId,
                                                @RequestPart CartItemDetails cartItemDetails){
        UserDomain currentUser = currentUserService.getCurrentUser();
        ShoppingCartDomain shoppingCartDomainUpdated = addCartItemUseCase.addCartItem(new CartItemCommand(currentUser.getId(),productId,cartItemDetails));

        return ResponseEntity.ok( shoppingCartDomainToDTO.toDTO(shoppingCartDomainUpdated));

    }

}
