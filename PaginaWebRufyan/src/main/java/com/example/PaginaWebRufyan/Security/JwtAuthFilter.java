package com.example.PaginaWebRufyan.Security;
import com.example.PaginaWebRufyan.Security.Service.JwtService;
import com.example.PaginaWebRufyan.Security.Service.TokenRepository;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final  UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui.html");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().contains("/auth")){
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        if(authHeader== null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        final String jwtToken = authHeader.substring(7);
        // No tiene formato JWT → sigo
        if (!jwtToken.contains(".")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String userEmail = jwtService.extractEmail(jwtToken);
        if(userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null){
            filterChain.doFilter(request,response);
            return;
        }
        final Token token = tokenRepository.findByToken(jwtToken).orElse(null);
        if(token == null || token.isExpired() || token.isRevoked()){
            filterChain.doFilter(request,response);
            return;
        }
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        final Optional<UserDomain> user = userRepositoryPort.findUserByEmail(userDetails.getUsername());

        if(user.isEmpty()){
            filterChain.doFilter(request,response);
            return;
        }
        final boolean isTokenValid = jwtService.isTokenValid(jwtToken, user.get());

        if(!isTokenValid){
            filterChain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);


    }
}
