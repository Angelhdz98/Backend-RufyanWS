package com.example.PaginaWebRufyan.Entity;

import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@SuperBuilder(toBuilder = true)
@Entity
@EqualsAndHashCode()

@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private LocalDate creationDate;
	private String style; 
	
/*	@ManyToMany(mappedBy = "copiesBuyed",
			cascade = {CascadeType.MERGE, CascadeType.PERSIST}
			)
	
	@JsonIgnore
	private List<UserEntity> copyBuyers;
	*/
	
	
	private Integer price;
	private Boolean isFavorite;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "category_id")//Nombre de la columna 
	private ProductsCategory category;
	@OneToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)	
	private List<Image> image;

	//CascadeType.ALL
//	cascade={ CascadeType.PERSIST,	CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH }
 	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REFRESH})
 	@JoinTable(name= "favorite_product_user",
 	joinColumns = @JoinColumn(name= "product_id"),
 	inverseJoinColumns = @JoinColumn(name= "user_id"))
 	@ToString.Exclude
	@EqualsAndHashCode.Exclude
 	@Builder.Default
	@JsonBackReference
	private Set<UserEntity> favoriteOf= new HashSet<>(); // Set (no repeated of user mark as favorite a product) // puede que el nombre anterior genere un problema de nomenclatura

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	private Set<CartItem> cartItems = new HashSet<>();

	@OneToMany(mappedBy = "product")
	@EqualsAndHashCode.Exclude
	private Set<OrderItem> orderItems = new HashSet<>();
	@ElementCollection
	@CollectionTable(name = "nombre_de_la_tabla_map", joinColumns = @JoinColumn(name = "tu_entidad_id"))
	@MapKeyColumn(name = "clave")
	@Column(name = "valor")
	private Map<String, String> additionalFeatures = new LinkedHashMap<>();


	public void	addToFavoriteOf(UserEntity user) {
		this.favoriteOf.add(user);
		user.getFavoriteProducts().add(this);
		
	}

	public void	removeFavoriteOf(UserEntity user) {
		this.favoriteOf.remove(user);
		user.getFavoriteProducts().remove(this);
		
	}

	
	
	
	
}