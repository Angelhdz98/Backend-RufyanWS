package com.example.PaginaWebRufyan.Service;

//import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PaginaWebRufyan.Entity.Painting;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.RoleEntity;
import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.Repository.PaintingRepository;
import com.example.PaginaWebRufyan.Repository.RoleRepository;
import com.example.PaginaWebRufyan.Repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
/*	@Autowired
	private ProductsRepository productsRepository;*/
	@Autowired 
	private PaintingRepository paintingRepository;
	
	
	public List<UserEntity> findAllUsers() {
		List<UserEntity> allUsers =userRepository.findAll();
		return allUsers;   
		
	}


	public Optional<UserEntity> findUserById(Integer id) {
		return userRepository.findByIdWithFavoriteProducts(id);
		
		
	}


	public  UserEntity save(UserEntity userData) {
		// TODO Auto-generated method stub
		/*
		UserEntity updatedUser = new UserEntity();
		updatedUser.setAccountNoExpired(userData.isAccountNoExpired());
		updatedUser.setAccountNoLocked(userData.isAccountNoLocked());
		updatedUser.setBirthDate(userData.getBirthDate());
		updatedUser.setCredentialNoExpired(userData.isCredentialNoExpired());
		updatedUser.setEmail(userData.getEmail());
		updatedUser.setEnabled(userData.isEnabled());
		updatedUser.setId(userData.getId());
		updatedUser.setLastname(userData.getLastname());
		updatedUser.setName(userData.getName());
		HashSet<Painting> originals =new HashSet<>();
		if(userData.getCopiesBuyed()!=null) {
		userData.getOriginalBuyed().forEach(original->{
			
			originals.add(paintingRepository.findById(original.getId()).orElseThrow(()->new ResourceNotFoundException("Painting not found")));
		});
		}
		updatedUser.setOriginalBuyed(originals);
		
		updatedUser.setPassword(userData.getPassword());
		
		updatedUser.setRoles(Set.of(roleRepository.findById(2).orElseThrow(() -> new ResourceNotFoundException("Role not found" ))));
		List<Product> copies = List.of();
		for(Product copy: updatedUser.getCopiesBuyed()) {
			copies.add(productsRepository.findById(copy.getId()).orElseThrow(() ->new ResourceNotFoundException("Product not found" ) ));
		}
		updatedUser.setCopiesBuyed(copies);
		//el numero 2 es el valor del Role Enum en la tabla 
 		updatedUser.setUsername(userData.getUsername());
 		*/
		System.out.println("roles: " + userData.getRoles());
		
		Set<RoleEntity> rolesUser= new HashSet<>();
		if(userData.getRoles()!=null) {
			userData.getRoles().forEach(role->{
			System.out.println("role repo response: "+	roleRepository.findByRoleEnum(
						role.getRoleEnum()).orElseThrow(
						()-> new ResourceNotFoundException("Role not found" + role)));
			
			rolesUser.add(roleRepository.findByRoleEnum(role.getRoleEnum()).orElseThrow());
		});
		}
		
		userData.setRoles(rolesUser);
		
		/*
		List<Product> copies = List.of();
		if(userData.getCopiesBuyed()!=null) {
			for(Product copy: userData.getCopiesBuyed()) {
				copies.add(productsRepository.findById(copy.getId()).orElseThrow(() ->new ResourceNotFoundException("Product not found" ) ));
			}
			userData.setCopiesBuyed(copies);
		}
		*/
		
		
		
		/*HashSet<Painting> originals =new HashSet<>();
		if(userData.getOriginalBuyed()!=null) {
		userData.getOriginalBuyed().forEach(original->{
			
			originals.add(paintingRepository.findById(original.getId()).orElseThrow(()->new ResourceNotFoundException("Painting not found")));
		});
		}
		userData.setOriginalBuyed(originals);
		*/
		
 		return userRepository.save(userData);
	}


	public void saveAll(List<UserEntity> user) {
		
		// lo puedo quitar si no funciona el plan es que se llame a la misma funcion
		user.forEach(onlyUser->{
			this.save(onlyUser);
		});	
	}
	
	@Transactional
	public void deleteUserById(Integer id) {
//		 UserEntity user = userRepository.findById(id).get();
	/*	 for(Product product: user.getFavoriteProducts()) {
			 user.removeFavoriteProduct(product);
			 product.removeFavoriteOf(user);
			 //productsRepository.save(product);// Guardo los cambios en el producto
		 }
		 //user.getFavoriteProducts().clear();
		*/
		 userRepository.deleteById(id);
		 
		/* for(Product copy: useeeer.getCopiesBuyed()) {
			 copy.getCopyBuyers().remove(user);
			 productsRepository.save(copy);// Guardo los cambios en el producto
		 }
		 user.getCopiesBuyed().clear();
		 */
		/* for(Painting painting: user.getOriginalBuyed()) {
			 painting.setOriginalOwner(null);
			 paintingRepository.save(painting);
		 }
		 user.getOriginalBuyed().clear();
		 */
		 
		
		
	}
	

	
	
}