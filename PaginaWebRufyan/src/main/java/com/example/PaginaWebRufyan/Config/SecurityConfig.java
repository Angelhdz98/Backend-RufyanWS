package com.example.PaginaWebRufyan.Config;

import com.example.PaginaWebRufyan.Security.JwtAuthFilter;
import com.example.PaginaWebRufyan.Security.Service.TokenRepository;
import com.example.PaginaWebRufyan.Security.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

     private final JwtAuthFilter jwtAuthFilter;
    private final TokenRepository tokenRepository;




    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return  authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users").hasRole(RoleEnum.ADMIN.toString())
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products").hasRole(RoleEnum.ADMIN.toString())
                        .requestMatchers(HttpMethod.POST,"/like").hasAnyRole(RoleEnum.ADMIN.toString())
                        .anyRequest().authenticated()
                );

        return http.build();
    }
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,  AuthenticationProvider authenticationProvider)throws Exception{
        http.csrf(csrf-> csrf.disable()).cors(cors->cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(req->
                        req.requestMatchers("/auth/**")
                                .permitAll()
                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                ).permitAll()
                                .requestMatchers("/shopping-cart/**", "/shopping-cart")
                                .hasRole("CLIENT")
                                .requestMatchers(HttpMethod.POST,
                                        "/api/products").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,
                                        "/api/products/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE,
                                        "/api/products/**").hasRole("ADMIN")
                                .requestMatchers("/like","/like/")
                                .authenticated()
                                .requestMatchers("/find-users/**").hasRole("ADMIN")
                                .requestMatchers("/user-register").permitAll()
                                .requestMatchers("/users",
                                        "/users/**").hasRole("ADMIN")
                                .requestMatchers("/admin/**",
                                        "/admin").hasRole("ADMIN")
                                .anyRequest().permitAll()



                ).sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/auth/logout")
                        .addLogoutHandler((request, response, authentication)->{
                            final var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                            logout(authHeader);
                        }).logoutSuccessHandler((request, response, authentication)-> SecurityContextHolder.clearContext()
                        ));
        return http.build();
    }

    private void logout( final String token) {
    if(token == null || !token.startsWith("Bearer ")){
        throw  new IllegalArgumentException("Invalid token");

    }
    final String jwtToken = token.substring(7);
    final Token foundToken = tokenRepository.findByToken(jwtToken).orElseThrow(()->new IllegalArgumentException("Invalid Token"));
    foundToken.setExpired(true);
    foundToken.setRevoked(true);
    tokenRepository.save(foundToken);

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://rufyansilva.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
