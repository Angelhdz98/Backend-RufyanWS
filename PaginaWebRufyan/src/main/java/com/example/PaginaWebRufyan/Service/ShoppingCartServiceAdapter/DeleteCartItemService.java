package com.example.PaginaWebRufyan.Service.ShoppingCartServiceAdapter;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.CartItemCommand;
import com.example.PaginaWebRufyan.adapter.in.ShoppingCartController.DeleteCartItemCommand;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CartItemDetails;
import com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase.DeleteCartItemUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.ShoppingCartMapper;
import com.example.PaginaWebRufyan.domain.port.out.ShoppingCartRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteCartItemService implements DeleteCartItemUseCase {

    private final ShoppingCartRepositoryPort shoppingCartRepository;
    private final ProductRepositoryPort productRepositoryPort;

    public DeleteCartItemService(ShoppingCartRepositoryPort shoppingCartRepository, ProductRepositoryPort productRepositoryPort) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepositoryPort = productRepositoryPort;
    }


    @Override
    public ShoppingCartDomain deleteCartItem(DeleteCartItemCommand deleteCartItemCommand) {
        //ProductDomain productDomain = productRepositoryPort.retrieveProductById(cartItemCommand.productId());
        ShoppingCartDomain shoppingCartDomain = shoppingCartRepository.retrieveShoppingCart(deleteCartItemCommand.userId());
        if(!shoppingCartDomain.getItems().removeIf((CartItemDomain cartItemDomain)->{
            return cartItemDomain.getId().equals(deleteCartItemCommand.cartItemId());
        })) throw  new ResourceNotFoundException("No se encuentra el cartItem que deseas borrar");

        return  shoppingCartRepository.updateShoppingCart(shoppingCartDomain.getId(), shoppingCartDomain);
    }
}


