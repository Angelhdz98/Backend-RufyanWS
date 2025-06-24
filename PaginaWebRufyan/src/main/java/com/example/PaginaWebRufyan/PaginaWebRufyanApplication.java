package com.example.PaginaWebRufyan;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
//import java.util.LinkedHashSet;

import com.example.PaginaWebRufyan.Components.OriginalStock;
import com.example.PaginaWebRufyan.Components.PaintingPriceManager;
import com.example.PaginaWebRufyan.DTO.ProductRegisterDTO;
import com.example.PaginaWebRufyan.DTO.ProductUpdateRegisterDTO;
import com.example.PaginaWebRufyan.DTO.UserRegisterDTO;
import com.example.PaginaWebRufyan.Entity.*;
import com.example.PaginaWebRufyan.Repository.*;
import com.example.PaginaWebRufyan.Service.ImageService;
import com.example.PaginaWebRufyan.Service.ProductService;
import com.example.PaginaWebRufyan.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.PaginaWebRufyan.Utils.RoleEnum;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


@SpringBootApplication
public class PaginaWebRufyanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaginaWebRufyanApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository userRepository,
						   RoleRepository roleRepository, PaintingRepository paintingRepository,
						   ProductsCategoryRepository productsCategoryRepository, UserService userService, ImageService imageService, ProductService productService, ProductsRepository productsRepository) {
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
                    .permissionList(new HashSet<>(Set.of(createPermission, readPermission, updatePermission, deletePermission)))
                    .build();

            RoleEntity roleClient = RoleEntity.builder()
                    .roleEnum(RoleEnum.CLIENT)
                    .permissionList(new HashSet<>(Set.of(createPermission, readPermission)))
                    .build();

            RoleEntity roleInvited = RoleEntity.builder()
                    .roleEnum(RoleEnum.INVITED)
                    .permissionList(new HashSet<>(Set.of(readPermission)))
                    .build();

            RoleEntity roleDeveloper = RoleEntity.builder()
                    .roleEnum(RoleEnum.DEVELOPER)
                    .permissionList(new HashSet<>(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission)))
                    .build();

			

            
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
					.roles(new HashSet<>(Set.of(roleAdmin)))
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
					.roles(new HashSet<>(Set.of(roleDeveloper)))
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
					.roles(new HashSet<>(Set.of(roleClient)))
					.favoriteProducts(new HashSet<>())
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
					.roles(new HashSet<>(Set.of(roleClient)))
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
					.roles(new HashSet<>(Set.of(roleClient)))
					.build();
			
			
		
					
			

			
			
			final String HOSTLINK= "http://localhost:8080/static/";
			final String ASSETS= "../java/static/";
			//Agregamos imagenes 
			Image obra1Image = new Image();
			obra1Image.setUrl(HOSTLINK+"obra2.jpg");
			Image obra2Image = new Image();
			obra2Image.setUrl(HOSTLINK+"obra3.jpg");
			Image obra3Image = new Image();
			obra3Image.setUrl(HOSTLINK+"obra3.jpg");
			Image obra4Image = new Image();
			obra4Image.setUrl(HOSTLINK+"obra5.png");
			Image obra5Image = new Image();
			obra5Image.setUrl(HOSTLINK+"obra6.png");
			Image obra6Image = new Image();
			obra6Image.setUrl(HOSTLINK+"obra7.png");
			
			ProductsCategory productoSimple = ProductsCategory.builder().name("products").build();
			ProductsCategory pintura = ProductsCategory.builder().name("paintings").build();
			ProductsCategory arteDigital= ProductsCategory.builder().name("digital").build();
			ProductsCategory prenda = ProductsCategory.builder().name("clothes").build();
			ProductsCategory bordado  = ProductsCategory.builder().name("embriodery").build();
			ProductsCategory accesorio = ProductsCategory.builder().name("accesories").build();
			
			List<ProductsCategory> listaCategorias = List.of(productoSimple, pintura, 	
					arteDigital, prenda, bordado, accesorio );
		    
			productsCategoryRepository.saveAll(listaCategorias);
			
			ProductsCategory categoriaPinturaGuardada = productsCategoryRepository.findByName("paintings").orElseThrow();
			//System.out.println(categoriaPinturaGuardada);

			//agregamos pinturas

			Map<String, Object> stockMapPainting1 = new HashMap<>();
			stockMapPainting1.put("stockCopies",10);
			stockMapPainting1.put("isOriginalAvailable",true);
			stockMapPainting1.put("copiesMade",15);
			stockMapPainting1.put("isInCart",false);
			Map<String, Object> priceMapPainting1 = new HashMap<>();
			priceMapPainting1.put("pricePerCopy", BigDecimal.valueOf(300));
			priceMapPainting1.put("pricePerOriginal", BigDecimal.valueOf(1200));
			Map<String, String> additionalFeaturesPainting1  = new HashMap<>();
			additionalFeaturesPainting1.put("alturaCm", "40");
			additionalFeaturesPainting1.put("largoCm","25");
			additionalFeaturesPainting1.put("medium", "Oil");
			additionalFeaturesPainting1.put("supportMaterial","Canvas");



			ProductRegisterDTO productRegisterPainting1 =
					ProductRegisterDTO.builder()
							.name("Starry Night")
							.type(ProductsEnum.PAINTING)
							.description("A gorgeous painting by me")
							.creationDate(LocalDate.now().minusWeeks(132))
							.style("Surrealism")
							.isFavorite(true)
							//.imageFiles()
							.stock(stockMapPainting1)
							.priceManage(priceMapPainting1)
							.additionalFeatures(additionalFeaturesPainting1)
							.build();
						//whit File it is assuming that the file is in a "brother" directory but when the project
						// is compiled all files are copied and compressed to classpath and aren't available for
                    /*    File to read
					File img1file = new File(ASSETS+"obra4.png");
					File img2file = new File(ASSETS+"obra5.png");
			FileInputStream image1Input = new FileInputStream(img1file);
			FileInputStream image2Input = new FileInputStream(img2file);

                     */
			ClassPathResource resource1 = new ClassPathResource("static/obra4.png");
			ClassPathResource resource2 = new ClassPathResource("static/obra5.png");
			MockMultipartFile multiPartFile1;
			MockMultipartFile multiPartFile2;
			try(InputStream image1 = resource1.getInputStream();
				InputStream image2 = resource2.getInputStream();){
				multiPartFile1 = new MockMultipartFile("image1",resource1.getFilename(),"image/jpeg",image1);
				 multiPartFile2 = new MockMultipartFile("image2",resource2.getFilename(),"image/jpeg",image2);
			}




			List<MultipartFile> painting2Images = List.of(multiPartFile1,multiPartFile2);

			ProductUpdateRegisterDTO productUpdateRegister =
					ProductUpdateRegisterDTO.builder()
							.name("The Persistence of Memory")
							.type(ProductsEnum.PAINTING)
							.description("A Painting I did when I was sad ")
							.creationDate(LocalDate.now().minusWeeks(152))
							.style("Surrealism")
							.isFavorite(true)
							.newImageFiles(painting2Images)
							.stock(stockMapPainting1)
							.priceManage(priceMapPainting1)
							.additionalFeatures(additionalFeaturesPainting1)
							.build();
			//List<Image> images = ;
			productUpdateRegister.setOldImages(imageService.processImages(painting2Images));

			Product painting1 = ProductFactory.createProductFromRegister(productUpdateRegister);

		/*	Painting painting2 = Painting.builder().name("The Scream")
							.description("A Painting I did when I was sad ")
					.creationDate(LocalDate.of(2012, 5,18))
					.style("Expressionism")
					.stockManager(new OriginalStock(10,true,false))
					.priceManager(new PaintingPriceManager(BigDecimal.valueOf(350),BigDecimal.valueOf(1200)))
					.isFavorite(true)
					.image(List.of(obra5Image, obra6Image))
					.favoriteOf(new HashSet<>(Set.of()))
					.cartItems(new HashSet<>(Set.of()))
					.orderItems(new HashSet<>(Set.of()))
					.alturaCm(70)
					.largoCm(50)
					.medium("Oil")
					.supportMaterial("Tempera")
					.build();

Painting painting3= new Painting();
			painting3.setName("The Scream");
			painting3.setDescription("A Painting I did when I was sad ");
			painting3.setCategory(pintura);
			painting3.setAlturaCm(70);
			painting3.setLargoCm(50);
			painting3.setCopiesMade(10);
			painting3.setAvailableCopies(10);
			painting3.setCreationDate(LocalDate.of(2018, 10, 10));
			painting3.setIsFavorite(true);
			painting3.setImage(List.of(obra5Image, obra6Image));
			painting3.setMedium("Oil");
			painting3.setPrice(2000);
			painting3.setPricePerCopy(400);
			painting3.setSupportMaterial("Tempera");
			painting3.setStyle("Expressionism");
			painting3.setIsOriginalAvailable(false);
			//painting3.setFavoriteOf(Set.of(userBaudelioCliente));
			//painting3.setCopyBuyers(List.of(userTerCliente));



			Painting painting1 = new Painting();
			painting1.setName("Starry Night");
			painting1.setDescription("A gorgeous painting by me");
			painting1.setCategory(categoriaPinturaGuardada);
			painting1.setAlturaCm(92);
			painting1.setLargoCm(74);
			painting1.setCopiesMade(5);
			painting1.setAvailableCopies(3);
			painting1.setCreationDate(LocalDate.of(2020, 9, 11));
			painting1.setIsFavorite(true);
			painting1.setImage(List.of(obra1Image,obra2Image));
			painting1.setMedium("Oil");
			painting1.setPrice(2000);
			painting1.setPricePerCopy(400);
			painting1.setSupportMaterial("canvas");
			painting1.setStyle("Surrealismo");
			//painting1.setOriginalOwner(userDonRube);
			//painting1.setCopyBuyers(List.of(userMaria, userBaudelioCliente));



			Painting painting2= new Painting();
			painting2.setName("The Persistence of Memory");
			painting2.setDescription("A gorgeous painting by me when I was 18 ");
			painting2.setCategory(pintura);
			painting2.setAlturaCm(70);
			painting2.setLargoCm(50);
			painting2.setCopiesMade(10);
			painting2.setAvailableCopies(8);
			painting2.setCreationDate(LocalDate.of(2018, 10, 10));
			painting2.setIsFavorite(true);
			painting2.setImage(List.of(obra3Image,obra4Image));
			painting2.setMedium("Oil");
			painting2.setPrice(2000);
			painting2.setPricePerCopy(400);
			painting2.setSupportMaterial("Tempera");
			//painting2.setFavoriteOf(Set.of(userDonRube));
			//painting2.setCopyBuyers(List.of(userBaudelioCliente, userPepe));

 */





			
			
				//userMaria.setCopiesBuyed(List.of(painting1));
			
			//userTerCliente.setCopiesBuyed(List.of(painting3));
			//userDonRube.setOriginalPaintings(Set.of(painting2, (Painting) painting1));

			List<Product> paintings = List.of(painting1);
			paintings.forEach(painting ->{
				productsRepository.save(painting);
			});



			userDonRube.addFavoriteProduct(painting1);
		//	userDonRube.addFavoriteProduct(painting2);


		var userList=	List.of(userRufyan,
					userDonRube,
					userDonaMago,
					userPepe,
					userBaudelioCliente);



		

			//userRepository.saveAll(user);

			for(UserEntity user:userList){
				userService.createUser(new UserRegisterDTO(user));
			}





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

