package com.example.PaginaWebRufyan.Service.LikesServiceAdapter;

import com.example.PaginaWebRufyan.Exceptions.AlreadyExistResource;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.adapter.in.LikeControllers.LikeCommand;
import com.example.PaginaWebRufyan.domain.model.LikeDomain;
import com.example.PaginaWebRufyan.domain.port.in.LikesUseCase.CreateLikeUseCase;
import com.example.PaginaWebRufyan.domain.port.out.LikesRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.ProductRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class MarkAsLikedService implements CreateLikeUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;
    private final LikesRepositoryPort likesRepositoryPort;


    public MarkAsLikedService(UserRepositoryPort userRepositoryPort, ProductRepositoryPort productRepositoryPort, LikesRepositoryPort likesRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
        this.likesRepositoryPort = likesRepositoryPort;
    }


    @Override
    public LikeDomain markAsLiked(LikeCommand likeCommand) {
        if(!productRepositoryPort.existById(likeCommand.productId())  ) throw new ResourceNotFoundException("No existe el producto que se quiere likear");
        if(!userRepositoryPort.existById(likeCommand.userId())) throw new ResourceNotFoundException("No existe el usuario que quiere likear ");
        if(likesRepositoryPort.existsLike(likeCommand.userId(), likeCommand.productId())) throw new AlreadyExistResource("la usuario con id "+likeCommand.userId()+" ya ha indicado que le gusta la producto con id "+ likeCommand.productId() );
        return likesRepositoryPort.markAsLiked(likeCommand);
    }
}
