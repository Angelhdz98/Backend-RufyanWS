
package com.example.PaginaWebRufyan.Security;

import com.example.PaginaWebRufyan.Exceptions.InvalidTokenException;
import com.example.PaginaWebRufyan.Security.Service.JwtService;
import com.example.PaginaWebRufyan.Security.Service.TokenRepository;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import io.jsonwebtoken.JwtException;
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
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        // Excluir endpoints públicos
        return path.startsWith("/auth/")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui.html");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            final String authHeader = request.getHeader("Authorization");

            // Validar que exista header Authorization con formato Bearer
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            final String jwtToken = authHeader.substring(7);

            // Validar formato JWT básico
            if (!jwtToken.contains(".")) {
                filterChain.doFilter(request, response);
                return;
            }

            // Extraer email (PUEDE LANZAR EXCEPCIÓN)
            String userEmail;
            try {
                userEmail = jwtService.extractEmail(jwtToken);
            } catch (JwtException | IllegalArgumentException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido");
                return;
            }

            if (userEmail == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido");
                return;
            }

            // Si ya hay autenticación, continuar
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                filterChain.doFilter(request, response);
                return;
            }

            // Validar que el token existe en BD y no está revocado
            final Token token = tokenRepository.findByToken(jwtToken).orElse(null);
            if (token == null || token.isExpired() || token.isRevoked()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido");
                return;
            }

            // Cargar detalles del usuario
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            final Optional<UserDomain> user = userRepositoryPort.findUserByEmail(userDetails.getUsername());

            if (user.isEmpty()) {
                throw new InvalidTokenException("Usuario no encontrado");
            }

            // Validar token
            final boolean isTokenValid = jwtService.isTokenValid(jwtToken, user.get());

            if (!isTokenValid) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido");
                return;
            }

            // Autenticar usuario
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);

        } catch (InvalidTokenException e) {
            // Las excepciones de token inválido se manejarán en GlobalExceptionHandler
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido");
            return;
        } catch (Exception e) {
            // Otras excepciones se lanzan para que GlobalExceptionHandler las maneje
            throw new InvalidTokenException("Error en autenticación: " + e.getMessage());
        }
    }
}