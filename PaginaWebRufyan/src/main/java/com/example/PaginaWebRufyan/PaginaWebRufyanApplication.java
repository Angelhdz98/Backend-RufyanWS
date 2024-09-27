package com.example.PaginaWebRufyan;

import java.time.LocalDate;
import java.util.HashSet;
//import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.Painting;
import com.example.PaginaWebRufyan.Entity.PermissionEntity;
import com.example.PaginaWebRufyan.Entity.Product;
import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import com.example.PaginaWebRufyan.Entity.RoleEntity;
import com.example.PaginaWebRufyan.Entity.UserEntity;
import com.example.PaginaWebRufyan.Repository.PaintingRepository;
import com.example.PaginaWebRufyan.Repository.ProductsCategoryRepository;
import com.example.PaginaWebRufyan.Repository.RoleRepository;
import com.example.PaginaWebRufyan.Repository.UserRepository;
import com.example.PaginaWebRufyan.Utils.RoleEnum;

@SpringBootApplication
public class PaginaWebRufyanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaginaWebRufyanApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository userRepository,
			RoleRepository roleRepository, PaintingRepository paintingRepository,
			ProductsCategoryRepository productsCategoryRepository) {
		return args ->{
			
			PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();

            PermissionEntity refactorPermission = PermissionEntity.builder()
                    .name("REFACTOR")
                    .build();

            /* Create ROLES */
            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleClient = RoleEntity.builder()
                    .roleEnum(RoleEnum.CLIENT)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();

            RoleEntity roleInvited = RoleEntity.builder()
                    .roleEnum(RoleEnum.INVITED)
                    .permissionList(Set.of(readPermission))
                    .build();

            RoleEntity roleDeveloper = RoleEntity.builder()
                    .roleEnum(RoleEnum.DEVELOPER)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
                    .build();

			
	/*		PermissionEntity createPermission= new PermissionEntity();
			createPermission.setName("CREATE");
			PermissionEntity readPermission= new PermissionEntity();
			readPermission.setName("READ");
			PermissionEntity updatePermission= new PermissionEntity();
			updatePermission.setName("UPDATE");
			PermissionEntity deletePermission= new PermissionEntity();;
			deletePermission.setName("DELETE");
	*/
			
			//Crear los roles 
			/*RoleEntity roleAdmin = new RoleEntity();
			roleAdmin.setRoleEnum(RoleEnum.ADMIN);
			roleAdmin.setPermissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission));
			
			RoleEntity roleClient = new RoleEntity();
			roleClient.setRoleEnum(RoleEnum.CLIENT);
			roleClient.setPermissionList(Set.of(readPermission ));
			
			RoleEntity roleDeveloper = new RoleEntity();
			roleDeveloper.setRoleEnum(RoleEnum.DEVELOPER);
			roleDeveloper.setPermissionList(Set.of(createPermission,  readPermission, updatePermission, deletePermission));
			
			RoleEntity roleInvited = new RoleEntity();
			roleInvited.setRoleEnum(RoleEnum.INVITED);
			roleInvited.setPermissionList(Set.of(readPermission));
			*/
			
            
            roleRepository.saveAll(List.of(roleAdmin, roleClient, roleDeveloper, roleInvited));
			
			//roleRepository.saveAll(Set.of(roleAdmin, roleClient, roleDeveloper, roleInvited));
			//Crear los usuarios 
			String commonPassword = "yonomascompro";
			//Set.of(null), List.of(null), Set.of(null)
			
			UserEntity userRufyan =UserEntity.builder()
					.name("Rodrigo")
					.lastname("Muñoz Silva")
					.birthDate( LocalDate.of(1994, 5, 17))
					.email("RufyanSilva@gmail.com")
					.username("RufyanAdmin")
					.password("pepeesmipastor")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();
			UserEntity userPepe =UserEntity.builder()
					.name("José Ángel ")
					.lastname("Hernández Torres")
					.birthDate( LocalDate.of(1998, 6, 5))
					.email("hernandeztorresjoseangel@gmail.com")
					.username("RufyanDeveloper")
					.password("yo nomas programo")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();
			UserEntity userDonRube =UserEntity.builder()
					.name("Don Rube")
					.lastname("Salinas")
					.birthDate( LocalDate.of(1954, 2, 19))
					.email("rubensalinas@gmail.com")
					.username("DonRube")
					.password("yonomascompro")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleClient))
					.build();
			UserEntity userDonaMago =UserEntity.builder()
					.name("Dona Mago")
					.lastname("de Salinas")
					.birthDate( LocalDate.of(1958, 5, 22))
					.email("mago_gonzalez@gmail.com")
					.username("MagoSinMangas")
					.password("yonomascompro")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleClient))
					.build();
			UserEntity userBaudelioCliente =UserEntity.builder()
					.name("Baudelio")
					.lastname("Jimenez")
					.birthDate( LocalDate.of(1980, 9, 1))
					.email("BaudelioJ@hotmail.com")
					.username("Baudi")
					.password("yonomascompro")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleClient))
					.build();
			
			
		
					
			
			/*
			UserEntity userRufyan = new UserEntity("Rodrigo", "Muñoz Silva", LocalDate.of(1994, 5, 17),
					"RufyanSilva@gmail.com","RufyanAdmin", "pepeesmipastor",
					true, true, true, true,
					new HashSet<>(List.of(roleAdmin)),
					//Set.of(roleRepository.findById(roleAdmin.getId()).get()),
					new HashSet<>(List.of())
					);			
		
			UserEntity userPepe = new UserEntity("Jose Angel", "Hernandez Torres", LocalDate.of(1998, 6, 5),
					"hernandeztorresjoseangel@gmail.com","PepeDev", "yonomasprogramo",
					true, true, true, true,
					//Set.of(roleRepository.findById(roleDeveloper.getId()).get()),
					new HashSet<>(List.of(roleDeveloper)),
					Set.of());			
			UserEntity userDonRube = new UserEntity("Don Rube", "Salinas", LocalDate.of(1954, 2, 19),
					"rubensalinas@gmail.com","DonRube","yonomascompro",
					true, true, true, true,
					//Set.of(roleRepository.findById(roleClient.getId()).get()),
					new HashSet<>(List.of(roleClient)),
					Set.of());
			UserEntity userDonaMago = new UserEntity("Dona Mago", "de Salinas", LocalDate.of(1958, 5, 22)
					, "mago_gonzalez@gmail.com", "MagoSinMangas", "yonomascompro",
					true, true, true, true,
//					Set.of(roleRepository.findById(roleInvited.getId()).get()),
					new HashSet<>(List.of(roleClient)),
					Set.of());
				UserEntity userBaudelioCliente = new UserEntity("Baudelio", "Jimenez", LocalDate.of(1980, 9, 1),
						"BaudelioJ@hotmail.com","Baudi", "yonomascompro",
						true, true, true, true,
						//Set.of(roleRepository.findById(roleClient.getId()).get()),
						new HashSet<>(List.of(roleClient)),
						Set.of());
						
						*/
			
		/*	UserEntity userTerCliente = new UserEntity("Ter", "Nomas Ter", LocalDate.of(1996, 5, 12), "spamdeTer@gmail.com","TER", "yonomascompro", true, true, true, true, Set.of(roleRepository.findById(roleClient.getId()).get()),null);
			UserEntity userMaria = new UserEntity("Maria", "Lopez Sanchez", LocalDate.of(1990, 3, 15), "maria.lopez@example.com","MariaArtLover", commonPassword, true, true, true, true, Set.of(roleRepository.findById(roleClient.getId()).get()),null
				);
	*/		
			
				
				 // Guardar usuarios
	   //         List<UserEntity> users = List.of(userRufyan, userDonRube, userDonaMago, userPepe, userBaudelioCliente);
	  //          userService.saveAll(users);
			
			
			
	
			//Agregamos imagenes 
			Image obra1Image = new Image();
			obra1Image.setUrl("../../assets/Images/imgObras/obra2.jpg");
			Image obra2Image = new Image();
			obra2Image.setUrl("../../assets/Images/imgObras/obra3.jpg");
			Image obra3Image = new Image();
			obra3Image.setUrl("../../assets/Images/imgObras/obra3.jpg");
			Image obra4Image = new Image();
			obra4Image.setUrl("../../assets/Images/imgObras/obra5.png");
			Image obra5Image = new Image();
			obra5Image.setUrl("../../assets/Images/imgObras/obra6.png");
			Image obra6Image = new Image();
			obra6Image.setUrl("../../assets/Images/imgObras/obra7.png");
			
			ProductsCategory productoSimple = ProductsCategory.builder().name("producto").build();
			ProductsCategory pintura = ProductsCategory.builder().name("pintura").build();
			ProductsCategory arteDigital= ProductsCategory.builder().name("digital").build();
			ProductsCategory prenda = ProductsCategory.builder().name("prenda").build();
			ProductsCategory bordado  = ProductsCategory.builder().name("bordado").build();
			ProductsCategory accesorio = ProductsCategory.builder().name("accesorio").build();
			
			List<ProductsCategory> listaCategorias = List.of(productoSimple, pintura, 	
					arteDigital, prenda, bordado, accesorio );
		    
			productsCategoryRepository.saveAll(listaCategorias);
			
			ProductsCategory categoriaPinturaGuardada = productsCategoryRepository.findByName("pintura").orElseThrow();
			//System.out.println(categoriaPinturaGuardada);
			
			//agregamos pinturas
			Painting painting1 = new Painting();
			painting1.setName("Starry Night");
			painting1.setDescription("A gorgeous painting by me");
			painting1.setCategory(categoriaPinturaGuardada);
			painting1.setAltura_cm(92);
			painting1.setLargo_cm(74);
			painting1.setCopies_made(5);
			painting1.setAvailable_copies(3);
			painting1.setCreation_date(LocalDate.of(2020, 9, 11));
			painting1.setFavorite(true);
			painting1.setImage(List.of(obra1Image,obra2Image));
			painting1.setMedium("Oil");
			painting1.setPrice(2000);
			painting1.setPrice_copy(400);
			painting1.setSupport_material("canvas");
			painting1.setStyle("Surrealismo");
			//painting1.setOriginalOwner(userDonRube);
			//painting1.setCopyBuyers(List.of(userMaria, userBaudelioCliente));

		
			
			Painting painting2= new Painting();
			painting2.setName("The Persistence of Memory");
			painting2.setDescription("A gorgeous painting by me when I was 18 ");
			painting2.setCategory(pintura);
			painting2.setAltura_cm(70);
			painting2.setLargo_cm(50);
			painting2.setCopies_made(10);
			painting2.setAvailable_copies(8);
			painting2.setCreation_date(LocalDate.of(2018, 10, 10));
			painting2.setFavorite(true);
			painting2.setImage(List.of(obra3Image,obra4Image));
			painting2.setMedium("Oil");
			painting2.setPrice(2000);
			painting2.setPrice_copy(400);
			painting2.setSupport_material("Tempera");
			//painting2.setOriginalOwner(userDonRube);
			//painting2.setCopyBuyers(List.of(userBaudelioCliente, userPepe));
			
			
			Painting painting3= new Painting();
			painting3.setName("The Scream");
			painting3.setDescription("A Painting I did when I was sad ");
			painting3.setCategory(pintura);
			painting3.setAltura_cm(70);
			painting3.setLargo_cm(50);
			painting3.setCopies_made(10);
			painting3.setAvailable_copies(10);
			painting3.setCreation_date(LocalDate.of(2018, 10, 10));
			painting3.setFavorite(true);
			painting3.setImage(List.of(obra5Image, obra6Image));
			painting3.setMedium("Oil");
			painting3.setPrice(2000);
			painting3.setPrice_copy(400);
			painting3.setSupport_material("Tempera");
			painting3.setStyle("Expressionism");
			
			//painting3.setFavoriteOf(Set.of(userBaudelioCliente));
			//painting3.setCopyBuyers(List.of(userTerCliente));
			
			
			
			
			/*//	userMaria.setCopiesBuyed(List.of(painting1));
			
			userTerCliente.setCopiesBuyed(List.of(painting3));
			userDonRube.setOriginalBuyed(Set.of(painting2, painting1));
			*/
			
			List<Painting> paintings = List.of(painting1 , painting2, painting3);
			paintings.forEach(painting ->{
			paintingRepository.save(painting);
			});
			
	//		userBaudelioCliente.setCopiesBuyed(List.of(painting1,painting2));
					
					//productRepository.findByName(painting1.getName()).get(),
					//productRepository.findByName(painting2.getName()).get()
					
					/*productService.retrieveProductByName(painting1.getName()),
					productService.retrieveProductByName(painting2.getName())
					*/
					
			//userBaudelioCliente.setFavoriteProducts(new HashSet<Product>(Set.of(painting1)));
//			userBaudelioCliente.addFavoriteProduct(painting3);
					
					//productRepository.findByName(painting3.getName()).get()
					
					//productService.retrieveProductByName(painting3.getName())
					
//			userPepe.setCopiesBuyed(List.of(painting2));
					
					
		var user=	List.of(userRufyan,
					userDonRube,
					userDonaMago,
					userPepe,
					userBaudelioCliente);
				/*	userTerCliente,
					userMaria*/
		
		
			userRepository.saveAll(user);
		
			
			
			/*
			List.of(userRufyan,  userDonRube,userDonaMago, userPepe, userBaudelioCliente, userTerCliente,userMaria ).forEach(user ->{
				userService.save(user);
			});
			*/
			
			
			/**
			 *
			System.out.println(new BCryptPasswordEncoder().encode(commonPassword));
			
			
			System.out.println(new BCryptPasswordEncoder().encode("yonomasprogramo"));
 
			 * 
			 */
						
					
		};
		
	}


	
	
	
	
	
	
	
	
	
	

}