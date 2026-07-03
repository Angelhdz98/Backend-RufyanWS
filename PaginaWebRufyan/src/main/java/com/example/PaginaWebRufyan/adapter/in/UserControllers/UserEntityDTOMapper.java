package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.Buys.DTO.CartItemDTO;
import com.example.PaginaWebRufyan.Buys.Repository.CartItemRepository;
import com.example.PaginaWebRufyan.User.DTO.UserDTO;
import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.User.Repository.UserRepository;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.port.out.CartItemMapper;
import com.example.PaginaWebRufyan.domain.port.out.ShoppingCartRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class UserEntityDTOMapper {
    private final ShoppingCartRepositoryPort shoppingCartRepositoryPort;

    public UserEntityDTOMapper(ShoppingCartRepositoryPort shoppingCartRepositoryPort, CartItemMapper cartItemMapper, UserRepository userRepository) {
        this.shoppingCartRepositoryPort = shoppingCartRepositoryPort;
    }


    public UserDTO toDTO(UserEntity userEntity) {
        ShoppingCartDomain shoppingCartDomain = shoppingCartRepositoryPort.retrieveShoppingCart(userEntity.getId());

      /*
        List<CartItemDTO> cartItemDTOSList =
                shoppingCartDomain.getItems().stream().map(
                (CartItemDomain cartItemDomain) -> {
                    return new CartItemDTO(cartItemDomain.getProduct().getName(),
                            cartItemDomain.getProduct().getImages().stream().findFirst().orElse(null).url(),
                            cartItemDomain.getDetails().getItemQuantity().getQuantity(),
                            cartItemDomain.getDetails().showDetails(),
                            cartItemDomain.getItemTotalAmount());
                }
        ).toList();
    */
        return new UserDTO(
                userEntity.getId(),
                userEntity.getFullName(),
                new BirthDate(userEntity.getBirthDate()),
                userEntity.getUsername(),
                userEntity.getEmail()
        );
    }

    public UserEntity toEntity(UserDomain userDomain){

        return new UserEntity(userDomain.getId(),
                userDomain.getFullname(),
                userDomain.getBirthDate(),
                userDomain.getUsername(),
                userDomain.getEmail(), userDomain.getHashedPassword());
    }
}
