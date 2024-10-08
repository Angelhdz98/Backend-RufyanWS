package com.example.PaginaWebRufyan.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Repository.UserRepository;

@Service
public class UserServiceDetails implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		UserEntity userEntity = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("El usuario "+ username +" no existe"));
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		
		userEntity.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())))); //obtenemos los roles y los guardamos en un objeto que spring Security entiende
		
		userEntity.getRoles().stream()
		.flatMap(role-> role.getPermissionList().stream())
		.forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));// obtenemos los permisos y los guardamos en la lista de autoridad  
		
		return new User(userEntity.getUsername(),
				userEntity.getPassword(),
				userEntity.isEnabled(),
				userEntity.isAccountNoExpired(),
				userEntity.isCredentialNoExpired(),
				userEntity.isAccountNoLocked(),
				authorityList);
	}



}