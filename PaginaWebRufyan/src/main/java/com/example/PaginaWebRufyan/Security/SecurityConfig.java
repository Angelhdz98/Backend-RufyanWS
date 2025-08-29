
package com.example.PaginaWebRufyan.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.PaginaWebRufyan.Security.Service.UserServiceDetails;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {


	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		 return http.csrf(csrf -> csrf.disable())
				 .build();
/*		For any reason is crashing so I will use a filter chain with "no filters" so first I can ensure to have correct relations and the the auth. 
		return http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth -> {
			//Configuro los endpoints publicos
			auth.requestMatchers(HttpMethod.GET,"/paintings").permitAll();
			auth.anyRequest().denyAll();
			
			//configuros los endpoints privados
		})
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.build();
        
	*/			
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authentificationConfiguration) throws Exception{
		return authentificationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider(UserServiceDetails userServiceDetails ) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userServiceDetails);
		return provider;
	}
	

	@Bean
	    public PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }
	
}
