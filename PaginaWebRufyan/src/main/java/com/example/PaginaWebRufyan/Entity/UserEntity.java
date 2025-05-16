package com.example.PaginaWebRufyan.Entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonFormat;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	private String name;
	@NotNull
	private String lastname;
	@NotNull
	@JsonFormat(pattern = "yyyy-M-d")
	private LocalDate birthDate;
	@Email
	@NotNull
	@Column(unique = true)
	private String email;
	@NotNull
	@Column(unique= true)
	private String username;
	private String password;
	
	@Column(name = "is_enabled")
	private boolean isEnabled;
	@Column(name = "account_not_expired")
	private boolean accountNoExpired;
	@Column(name = "account_no_locked")
	private boolean accountNoLocked;
	@Column(name = "credentialNoExpired")
	private boolean credentialNoExpired;

	/*
	@JoinTable(name="user_roles",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name= "role_id"))
	*/
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
	joinColumns = @JoinColumn(name= "user_id"), 
	inverseJoinColumns = @JoinColumn(name= "role_id"))
	@Builder.Default
	private Set<RoleEntity> roles = new HashSet<>(); //ADMIN || CLIENT


	
	////
	
	
	/*@OneToMany()
	private Set<Painting> originalBuyed;
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
	
	@ManyToMany(mappedBy = "favoriteOf")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	@JsonManagedReference
	private Set<Product> favoriteProducts= new HashSet<>();

	@OneToMany(mappedBy = "originalOwner" ,
			fetch = FetchType.EAGER)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Painting> originalPaintings= new HashSet<>();
	@OneToOne
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private UserProfilePicture profilePicture;

	@OneToOne
	private ShoppingCart shoppingCart;


	// Helper methods to manage bidirectional relationship
    public void addFavoriteProduct(Product product) {
        this.favoriteProducts.add(product);
        product.getFavoriteOf().add(this);
    }
    
    public void removeFavoriteProduct(Product product) {
        this.favoriteProducts.remove(product);
        product.getFavoriteOf().remove(this);
    }
	
	/**/
    
	


}
