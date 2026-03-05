package com.example.PaginaWebRufyan.Service.ShoppingCartServiceAdapter;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.ShoppingCartUseCase.GetShoppingCartUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ShoppingCartRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetShoppingCartService implements GetShoppingCartUseCase {
    private final CurrentUserService currentUserService;
    private final ShoppingCartRepositoryPort shoppingCartRepositoryPort;
    public GetShoppingCartService(CurrentUserService currentUserService, ShoppingCartRepositoryPort shoppingCartRepositoryPort) {
        this.currentUserService = currentUserService;
        this.shoppingCartRepositoryPort = shoppingCartRepositoryPort;
    }

    @Override
    public ShoppingCartDomain getCart() {
        UserDomain currentUser = currentUserService.getCurrentUser();
       return shoppingCartRepositoryPort.retrieveShoppingCart(currentUser.getId());
    }
}
