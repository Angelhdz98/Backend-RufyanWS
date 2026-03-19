package com.example.PaginaWebRufyan.adapter.in.ShoppingCartController;


import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase.DeleteCartItemUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class DeleteCartItemController {
    private final DeleteCartItemUseCase deleteCartItemUseCase;
    private final CurrentUserService currentUserService;
    private final ShoppingCartDomainToDTO shoppingCartDomainToDTO;
    public DeleteCartItemController(DeleteCartItemUseCase deleteCartItemUseCase, CurrentUserService currentUserService, ShoppingCartDomainToDTO shoppingCartDomainToDTO) {
        this.deleteCartItemUseCase = deleteCartItemUseCase;
        this.currentUserService = currentUserService;
        this.shoppingCartDomainToDTO = shoppingCartDomainToDTO;
    }
    @PutMapping("/shopping-cart/remove-item/{cartItemId}")
    ResponseEntity<ShoppingCartDTO> deleteCartItem(@PathVariable Long cartItemId ) {
        UserDomain currentUser = currentUserService.getCurrentUser();
        ShoppingCartDomain shoppingCartDomain = deleteCartItemUseCase.deleteCartItem(
                new DeleteCartItemCommand(currentUser.getId(),cartItemId));

        return ResponseEntity.ok(shoppingCartDomainToDTO.toDTO(shoppingCartDomain));
    }

}
