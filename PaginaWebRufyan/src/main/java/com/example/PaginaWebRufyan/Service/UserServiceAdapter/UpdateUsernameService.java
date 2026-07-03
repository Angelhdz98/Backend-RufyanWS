package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.DTO.UpdateUsernameCommand;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdateUsernameUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateUsernameService implements UpdateUsernameUseCase {
   private final UserRepositoryPort userRepositoryPort;
   private final CurrentUserService currentUserService;
    public UpdateUsernameService(UserRepositoryPort userRepositoryPort, CurrentUserService currentUserService) {
        this.userRepositoryPort = userRepositoryPort;
        this.currentUserService = currentUserService;
    }

    @Override
    public UserDomain updateUsername(UpdateUsernameCommand updateUserCommand) {
        UserDomain currentUser = currentUserService.getCurrentUser();

        if(userRepositoryPort.existsByUsername(updateUserCommand.newUsername())){
            throw new UsernameAlreadyUsedException("Username already exists");
        }

        return userRepositoryPort.updateUser(new UserDomain(currentUser.getId(), currentUser.getFullname(),currentUser.getBirthDate(), updateUserCommand.newUsername(), currentUser.getEmail(),  currentUser.getHashedPassword() ));
    }
}
