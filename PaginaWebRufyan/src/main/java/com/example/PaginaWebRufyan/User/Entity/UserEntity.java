package com.example.PaginaWebRufyan.User.Entity;

import java.util.HashSet;
import java.util.Set;


import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserValidators;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode()
@ToString
@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private FullName fullName;
	@NotNull
	private BirthDate birthDate;
	@Email
	@NotNull
	@Column(unique = true)
	private String email;
	@NotNull
	@Column(unique= true)
	private String username;

	@NotNull
	private String password;
	private UserValidators userValidators;
	// sustituido por userValidators
	/*
	@Column(name = "is_enabled")
	private boolean isEnabled;
	@Column(name = "account_not_expired")
	private boolean accountNoExpired;
	@Column(name = "account_no_locked")
	private boolean accountNoLocked;
	@Column(name = "credentialNoExpired")
	private boolean credentialNoExpired;


	 */

	// sustituido por una clase especial (UserRoles)
	/*
	@JoinTable(name="user_roles",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name= "role_id"))

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
	joinColumns = @JoinColumn(name= "user_id"), 
	inverseJoinColumns = @JoinColumn(name= "role_id"))

	@Builder.Default
	private Set<RoleEntity> roles = new HashSet<>(); //ADMIN || CLIENT
*/



	

	
	
	/*@OneToMany()	private Set<Painting> originalBuyed;
	*/

	
/*	@ManyToMany(fetch=FetchType.EAGER,
				cascade=  {CascadeType.PERSIST,
						CascadeType.MERGE,
						//CascadeType.REFRESH
						 })
	@JoinTable(name= "user_copies",
				joinColumns = @JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name="product_id") )
//	@JsonIgnore
	private List<Product> copiesBuyed;
	*/
	//Favorite products y originalPainting será manejado por una clase especializada
	/*
	@ManyToMany(mappedBy = "favoriteOf")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	@JsonManagedReference
	private Set<Product> favoriteProducts= new HashSet<>(Set.of());


	@OneToMany(mappedBy = "originalOwner" ,
			fetch = FetchType.EAGER)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	private Set<Painting> originalPaintings= new HashSet<>();
	 */

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "profile_picture")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private UserProfilePicture profilePicture;


	/*
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "shopping_cart_id")
	@Builder.Default
	//@JsonManagedReference
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private ShoppingCart shoppingCart = new ShoppingCart();
	 */



	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Set<PurchaseOrder> purchases = new HashSet<>(Set.of());



    
	


}
