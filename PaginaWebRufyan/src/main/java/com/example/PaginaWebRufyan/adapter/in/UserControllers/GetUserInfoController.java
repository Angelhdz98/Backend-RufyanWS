package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.User.DTO.UserDTO;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
public class GetUserInfoController {
    private final CurrentUserService currentUserService;
    private final UserEntityDTOMapper userMapper;
    public GetUserInfoController(CurrentUserService currentUserService, UserEntityDTOMapper userMapper) {
        this.currentUserService = currentUserService;
        this.userMapper = userMapper;
    }
    @GetMapping("/user-info")
    ResponseEntity<UserDTO> getUserInfo(){
        UserDomain currentUser = currentUserService.getCurrentUser();
        System.out.println("Current User: " + currentUser);
        UserDTO dto = new UserDTO(currentUser.getId(),
                currentUser.getFullname(),
                currentUser.getBirthDate(),
                currentUser.getUsername(),currentUser.getEmail());
        System.out.println("UserDTO: " + dto);
        return ResponseEntity.ok(dto);

    }

}
