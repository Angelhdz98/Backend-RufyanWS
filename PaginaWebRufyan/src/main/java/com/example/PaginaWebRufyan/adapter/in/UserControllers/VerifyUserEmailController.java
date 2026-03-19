package com.example.PaginaWebRufyan.adapter.in.UserControllers;

import com.example.PaginaWebRufyan.Exceptions.InvalidTokenException;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CurrentUserService;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.VerifyUserEmailUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyUserEmailController {
      private final VerifyUserEmailUseCase verifyUserEmailUseCase;


    public VerifyUserEmailController(VerifyUserEmailUseCase verifyUserEmailUseCase) {
        this.verifyUserEmailUseCase = verifyUserEmailUseCase;
    }

    @GetMapping("/auth/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token){
        return ResponseEntity.ok(verifyUserEmailUseCase.verifyEmail(token));
      }
}
