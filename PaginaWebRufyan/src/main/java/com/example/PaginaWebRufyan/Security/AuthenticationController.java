package com.example.PaginaWebRufyan.Security;

import com.example.PaginaWebRufyan.Security.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;
/*
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody final LoginCommand loginCommand){
        final TokenResponse token  = authService.login(loginCommand);
        return ResponseEntity.ok(token);
    }
 */

/*
    @PostMapping("/refresh")
    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader){
        return authService.refreshToken(authHeader);
    }
*/
}
