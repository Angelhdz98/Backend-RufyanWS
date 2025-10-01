package com.example.PaginaWebRufyan.serviceTest;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Service.LikesServiceAdapter.*;
import com.example.PaginaWebRufyan.adapter.in.LikeControllers.LikeCommand;
import com.example.PaginaWebRufyan.domain.model.LikeDomain;
import com.example.PaginaWebRufyan.domain.model.ProductDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.out.LikesRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LikesUsersServiceTest {

    @Mock
    LikesRepositoryPort likesRepositoryPort;
    @Mock
    ProductRepositoryPort productRepositoryPort;
    @Mock
    UserRepositoryPort userRepositoryPort;

    @InjectMocks
    MarkAsLikedService markAsLikedService;
    @InjectMocks
    DislikeService dislikeService;
    @InjectMocks
    FindPagedProductsLikedByUserService findPagedProductsLikedByUserService;
    @InjectMocks
    GetPagedUsersThatLikedService getPagedUsersThatLikedService;

    // will be deprecated beacause is better using Paged method
//GetUsersThatLikedAProductService getUsersThatLikedAProductService;
    ProductDomain mockProduct;
    UserDomain mockUser;
    LikeCommand likeCommand;

    LikeDomain mockLikeDomain;

    @BeforeEach
    public void setUp(){

         mockProduct = mock(ProductDomain.class);
        mockUser = mock(UserDomain.class);
        likeCommand = new LikeCommand(13L, 18L);
        mockLikeDomain = mock(LikeDomain.class);
    }
    @Test
    @DisplayName("Test \"dar like \" de manera exitosa")
    public void shouldLikeProductSuccesfully() {


        when(productRepositoryPort.existById(any())).thenReturn(true);
        when(userRepositoryPort.existById(any())).thenReturn(true);
        when(likesRepositoryPort.existsLike(any(),any())).thenReturn(false);
        when(likesRepositoryPort.markAsLiked(likeCommand)).thenReturn(mockLikeDomain);
        LikeDomain newLikeDomain = markAsLikedService.markAsLiked(likeCommand);
        assertThat(newLikeDomain).isNotNull();
        verify(likesRepositoryPort,times(1)).markAsLiked(any());



    }

    @Test
    @DisplayName("Test para arrojar una excepción creando un like cuando el usuario no existe")
    public void shouldThrowExceptionCreatingAlikeForAUserThatDoesNotExist() {
        //probablemente estas 2 no se use
        when(productRepositoryPort.existById(any())).thenReturn(true);
        when(userRepositoryPort.existById(any())).thenReturn(false);


        assertThrows(ResourceNotFoundException.class, ()->{
            LikeDomain newLike = markAsLikedService.markAsLiked(likeCommand);
        });

        verify(likesRepositoryPort, never()).markAsLiked(any());


    }
    @Test
    @DisplayName("Test para arrojar una excepción creando un like cuando el producto no existe")
    public void shouldThrowExceptionCreatingAlikeForAProductThatDoesNotExist() {

        when(productRepositoryPort.existById(any())).thenReturn(false);
        //when(userRepositoryPort.existById(any())).thenReturn(true);

        assertThrows(ResourceNotFoundException.class, ()->{
            LikeDomain newLike = markAsLikedService.markAsLiked(likeCommand);
        });
        verify(likesRepositoryPort, never()).markAsLiked(any());


    }
    @Test
    @DisplayName("Test parar eliminar un like de manera exitosa")
    public void shouldUnmarkLikeSuccessfully(){

        when(likesRepositoryPort.existsLike(any(), any())).thenReturn(true);
        dislikeService.dislike(likeCommand);
        verify(likesRepositoryPort, times(1)).unmarkAsLiked(likeCommand.userId(), likeCommand.productId());

    }
    @Test
    @DisplayName("Test para arrojar una excepción cuando el like no existe")
    public void shouldThrowExceptionTryingToDeleteALikeThatDoesNotExist(){
        when(likesRepositoryPort.existsLike(any(),any())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, ()->{
           dislikeService.dislike(likeCommand);

        });
        verify(likesRepositoryPort, never()).unmarkAsLiked(any(), any());

    }
    @Test
    @DisplayName("Test para encontrar los productos que el usario ha marcado como \"me gusta\" ")
    public void shouldFindPagedProductsLikedByUser(){

        Page<ProductDomain> mockPageProduct = mock(Page.class);

        when(productRepositoryPort.findProductsLikedByUser(any(),any())).thenReturn(mockPageProduct);

        Page<ProductDomain> pagedProductsLikedByUser = findPagedProductsLikedByUserService.getPagedProductsLikedByUser(likeCommand.userId(), PageRequest.of(0, 12));

        assertThat(pagedProductsLikedByUser).isNotNull();
        verify(productRepositoryPort, times(1)).findProductsLikedByUser(any(), any());


    }
    @Test
    @DisplayName("test para encontrar los usuarios que han marcado como \"me gusta\" una obra ")
    public void shouldFindPagedUsersThatLikedAProduct(){

        Page<UserDomain> mockPageUser = mock(Page.class);

        when(userRepositoryPort.findAllUsersWhoLikedProduct(any(), any())).thenReturn(mockPageUser);
        Page<UserDomain> pagedUsersThatLiked = getPagedUsersThatLikedService.getPagedUsersThatLiked(likeCommand.productId(), PageRequest.of(0, 12));
        assertThat(pagedUsersThatLiked).isNotNull();
        verify(userRepositoryPort, times(1)).findAllUsersWhoLikedProduct(any(), any());

    }
}
