
package com.example.PaginaWebRufyan.adapter.out.persistence;

import java.time.LocalDate;
import java.util.*;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.PriceManagerPersist;
import com.example.PaginaWebRufyan.adapter.out.StockManager;
import com.example.PaginaWebRufyan.Image.Image;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Product {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private LocalDate creationDate;
	@Embedded
	private PriceManagerPersist priceManagerPersist;
	@Embedded
	private StockManager stockManager;

	private Boolean isAvailable;
	//private PriceManager priceManager;
	private Boolean isFavorite;


	@OneToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Image> image = new HashSet<>();
    private ProductTypeEnum productTypeEnum;
    //private StockManager stock;// a implementation of StockManager and PriceManager will be added on subclass
/*	@ManyToMany(mappedBy = "copiesBuyed",
			cascade = {CascadeType.MERGE, CascadeType.PERSIST}
			)

	@JsonIgnore
	private List<UserEntity> copyBuyers;
	*/

/*
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "category_id")//Nombre de la columna
	private ProductsCategory category;
	*/
//CascadeType.ALL
//	cascade={ CascadeType.PERSIST,	CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH }
 	/*@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REFRESH})
 	@JoinTable(name= "favorite_product_user",
 	joinColumns = @JoinColumn(name= "product_id"),
 	inverseJoinColumns = @JoinColumn(name= "user_id"))
 	@ToString.Exclude
	@EqualsAndHashCode.Exclude
 	@Builder.Default
	@JsonBackReference
	private Set<UserEntity> favoriteOf= new HashSet<>(Set.of()); // Set (no repeated of user mark as favorite a product) // puede que el nombre anterior genere un problema de nomenclatura


 	 */
	/* Se eliminan por alto acoplamiento
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	private Set<CartItem> cartItems = new HashSet<>();

	@OneToMany(mappedBy = "product")
	@EqualsAndHashCode.Exclude
	@JsonManagedReference
	private Set<OrderItem> orderItems = new HashSet<>();
*/
/*
	@ElementCollection
	@CollectionTable(name = "additional",
			joinColumns = @JoinColumn(name = "product_id"))
	@MapKeyColumn(name = "feature_key")
	@Column(name = "feature_value")
	//@Builder.Default
	private Map<String, String> additionalFeatures = new HashMap<>();
 */



/*	public void deleteImage(Image image){
		Set<Image> newImageList = this.getImage();
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


	public BigDecimal getPriceWithDetails(){
		Map<String,String> voidDetails = new HashMap<>();
		return priceManager.getPriceWithDetails(voidDetails);
	}

	public Map<String,BigDecimal> getPriceMap(){
		return priceManager.getPriceMap();
	}
*/
    //public abstract void checkConsistentData(ProductUpdateRegisterDTO productUpdateRegisterDTO);

	}