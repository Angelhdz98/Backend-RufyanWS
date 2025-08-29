package com.example.PaginaWebRufyan.adapter.in.ShoppingCartController;


import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase.DeleteCartItemUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-cart/remove-item")
public class DeleteCartItemController {
    private final DeleteCartItemUseCase deleteCartItemUseCase;

    public DeleteCartItemController(DeleteCartItemUseCase deleteCartItemUseCase) {
        this.deleteCartItemUseCase = deleteCartItemUseCase;
    }
    @PutMapping
    ResponseEntity<ShoppingCartDTO> deleteCartItem(@RequestBody DeleteCartItemCommand cartItemCommand) {
        ShoppingCartDomain shoppingCartDomain = deleteCartItemUseCase.deleteCartItemUseCase(cartItemCommand);

        return ResponseEntity.ok(ShoppingCartDomainToDTO.toDTO(shoppingCartDomain));
    }

}
