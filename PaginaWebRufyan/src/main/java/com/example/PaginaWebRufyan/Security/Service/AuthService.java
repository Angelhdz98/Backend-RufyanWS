package com.example.PaginaWebRufyan.Security.Service;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.LoginCommand;
import com.example.PaginaWebRufyan.DTO.RegisterUserDTO;
import com.example.PaginaWebRufyan.Security.Token;
import com.example.PaginaWebRufyan.Service.UserServiceAdapter.CreateUserService;
import com.example.PaginaWebRufyan.adapter.in.ConverterUserEntityDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserEntityMapper;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.LoginUserUseCase;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.RefreshTokenUseCase;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.RegisterUserUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements RegisterUserUseCase, LoginUserUseCase, RefreshTokenUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final CreateUserService createUserService;
    private final AuthenticationManager authenticationManager;

    public RegisterUserDTO register(CreateUserCommand createUserCommand){
        UserDomain userDomain = createUserService.createUser(createUserCommand);
        String jwtToken = jwtService.generateToken(userDomain);
        String refreshToken = jwtService.generateRefreshToken(userDomain);
        saveUserToken(userDomain, jwtToken);
        return new RegisterUserDTO(new TokenResponse(jwtToken,refreshToken), UserEntityMapper.toDto(userDomain));
    }

    public TokenResponse login(LoginCommand loginCommand){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginCommand.identificator()
                        ,loginCommand.password())
        );
        UserDomain user = userRepositoryPort.retrieveUserByEmail(loginCommand.identificator());
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        return new TokenResponse(accessToken,refreshToken);

    }

    protected void saveUserToken (UserDomain user, String jwtToken){
        Token token = Token.builder()
                .userEntity(ConverterUserEntityDomain.convertToEntity(user))
                .tokenType(Token.TokenType.BEARER)
                .token(jwtToken)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(final UserDomain userDomain){
        final List<Token> validUserTokens = tokenRepository.findAllByUserEntity_IdAndIsExpiredFalseAndIsRevokedFalse(userDomain.getId());
        if(!validUserTokens.isEmpty()){
            for(final Token token: validUserTokens){
                token.setExpired(true);
                token.setRevoked(true);

            }
            tokenRepository.saveAll(validUserTokens);
        }
    }

    public TokenResponse refreshToken(final String authHeader){
        if(authHeader == null|| !authHeader.startsWith("Bearer ")){
            throw new IllegalArgumentException("Invalid Bearer token ");
        }
        final String refreshToken= authHeader.substring(7);
        final String userEmail = jwtService.extractEmail(refreshToken);
        if(userEmail== null){
            throw new IllegalArgumentException("Invalid refresh token ");
        }
        final UserDomain user = userRepositoryPort.retrieveUserByEmail(userEmail);

        if( ! jwtService.isTokenValid(refreshToken, user)){
          throw new IllegalArgumentException("Invalid refresh Token");
        }
        final String accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,accessToken);
        return new TokenResponse(accessToken,refreshToken);

    }

    @Override
    public RegisterUserDTO registerUserUseCase(CreateUserCommand createUserCommand) {
        return register(createUserCommand);
    }

    @Override
    public TokenResponse authenticate(LoginCommand loginCommand) {
        return login(loginCommand);
    }
}
