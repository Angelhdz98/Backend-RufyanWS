package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.Service.UserServiceAdapter.IsUsernameAvailableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IsUsernameAvailableController {
    private final IsUsernameAvailableService isUsernameAvailableService;

    public IsUsernameAvailableController(IsUsernameAvailableService isUsernameAvailableService) {
        this.isUsernameAvailableService = isUsernameAvailableService;
    }
    @GetMapping("/user/username-check/{username}")
    public ResponseEntity<Boolean> isUsernameAvailable(@PathVariable String username) {
        return ResponseEntity.ok(isUsernameAvailableService.isUsernameAvailable(username)) ;
    }
}
