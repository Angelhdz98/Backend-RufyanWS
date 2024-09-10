package com.example.PaginaWebRufyan.Entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.loader.ast.spi.CascadingFetchProfile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
@Data
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
	private LocalDate creation_date;
	private String style; 
	
/*	@ManyToMany(mappedBy = "copiesBuyed",
			cascade = {CascadeType.MERGE, CascadeType.PERSIST}
			)
	
	@JsonIgnore
	private List<UserEntity> copyBuyers;
	*/
	
	
	private Integer price;
	private Boolean favorite;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST })
	private ProductsCategory category;
	@OneToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)	
	private List<Image> image;

	
	@JsonIgnore
 	@ManyToMany(fetch = FetchType.LAZY
 		 )
 	@JoinTable(name= "marked_fav_by",
 	joinColumns = @JoinColumn(name= "product_id"),
 	inverseJoinColumns = @JoinColumn(name= "user_id"))
	@Builder.Default
	private Set<UserEntity> favoriteOf = new HashSet<>(); // Set (no repeated of user mark as favorite a product) // puede que el nombre anterior genere un problema de nomenclatura
	
	private LinkedHashMap<String, String> adittionalFeatures;
	
	public void	addToFavoriteOf(UserEntity user) {
		this.favoriteOf.add(user);
		user.getFavoriteProducts().add(this);
		
	}

	public void	removeFavoriteOf(UserEntity user) {
		this.favoriteOf.remove(user);
		user.getFavoriteProducts().remove(this);
		
	}

	
	
	
	
}