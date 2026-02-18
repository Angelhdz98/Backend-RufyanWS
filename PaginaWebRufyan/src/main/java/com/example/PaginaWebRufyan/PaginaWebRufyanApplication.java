package com.example.PaginaWebRufyan;

//import java.util.LinkedHashSet;

import com.example.PaginaWebRufyan.Security.Roles.RoleEnum;
import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.adapter.in.ConverterUserEntityDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;


@SpringBootApplication
public class PaginaWebRufyanApplication {
	@Value("spring.datasource.admin-password")
	private String adminPassword;
	public static void main(String[] args) {
		SpringApplication.run(PaginaWebRufyanApplication.class, args);
	}



	@Bean
	CommandLineRunner initAdmin(UserRepositoryPort repo){


		return  args -> {

			var encoder = new BCryptPasswordEncoder();
			if(!repo.existsByUsername("RufyanSilva")){

				//System.out.println("Creando Admin");
				UserEntity adminUser = new UserEntity(0L,
						new FullName("Rodrigo", "", "Silva", ""), new BirthDate(LocalDate.of(1996, 3, 6)),
						"RufyanSilva", "rufyanone@gmail.com", encoder.encode(adminPassword), RoleEnum.ROLE_ADMIN);
				UserDomain saveUser = repo.saveUser(ConverterUserEntityDomain.convertToDomainWihRole(adminUser));

			}
		};
	}

	}
	



