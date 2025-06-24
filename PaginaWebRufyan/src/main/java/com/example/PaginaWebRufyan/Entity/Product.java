package com.example.PaginaWebRufyan.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import com.example.PaginaWebRufyan.Components.PriceManagerBase;
import com.example.PaginaWebRufyan.Components.StockManagerBase;
import com.example.PaginaWebRufyan.DTO.ProductUpdateRegisterDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Product {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private LocalDate creationDate;
	private String style;
	@Embedded
	private PriceManagerBase priceManager;
	@Embedded
	private StockManagerBase stockManager;
	//private StockManager stock;// a implementation of StockManager and PriceManager will be added on subclass
/*	@ManyToMany(mappedBy = "copiesBuyed",
			cascade = {CascadeType.MERGE, CascadeType.PERSIST}
			)
	
	@JsonIgnore
	private List<UserEntity> copyBuyers;
	*/
	
	
	//private PriceManager priceManager;
	private Boolean isFavorite;
	/*
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "category_id")//Nombre de la columna 
	private ProductsCategory category;
	*/
	@OneToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Image> image = new ArrayList<>();

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
	private Set<UserEntity> favoriteOf= new HashSet<>(Set.of()); // Set (no repeated of user mark as favorite a product) // puede que el nombre anterior genere un problema de nomenclatura

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	private Set<CartItem> cartItems = new HashSet<>();

	@OneToMany(mappedBy = "product")
	@EqualsAndHashCode.Exclude
	private Set<OrderItem> orderItems = new HashSet<>();

	@ElementCollection
	@CollectionTable(name = "additional",
			joinColumns = @JoinColumn(name = "product_id"))
	@MapKeyColumn(name = "feature_key")
	@Column(name = "feature_value")
	//@Builder.Default
	private Map<String, String> additionalFeatures = new HashMap<>();



	//public abstract void increaseStock(CartItem cartItem);

	//public abstract void decreaseStock(CartItem cartItem);

	public void deleteImage(Image image){
		List<Image> newImageList = this.getImage();
		newImageList.remove(image);
		this.setImage(newImageList);
	}

	public void addImage(Image image){
		List<Image> newImageList = this.getImage();
		newImageList.add(image);
		this.setImage(newImageList);
	}



	public void	addToFavoriteOf(UserEntity user) {
		this.favoriteOf.add(user);
		user.getFavoriteProducts().add(this);
		
	}

	public void	removeFavoriteOf(UserEntity user) {
		this.favoriteOf.remove(user);
		user.getFavoriteProducts().remove(this);
		
	}

	public void decreaseStock(Map<String, String> details) {
		stockManager.decreaseStock(this, details);
	}

	public void increaseStock(Map<String, String> details) {
		stockManager.increaseStock(this, details);
	}

	public BigDecimal getPriceWithDetails(Map<String, String> details) {
		return priceManager.getPriceWithDetails(details);
	}

	public BigDecimal getPriceWithDetails(){
		Map<String,String> voidDetails = new HashMap<>();
		return priceManager.getPriceWithDetails(voidDetails);
	}

	public Map<String,BigDecimal> getPriceMap(){
		return priceManager.getPriceMap();
	}

	//public abstract void checkConsistentData(ProductUpdateRegisterDTO productUpdateRegisterDTO);
}