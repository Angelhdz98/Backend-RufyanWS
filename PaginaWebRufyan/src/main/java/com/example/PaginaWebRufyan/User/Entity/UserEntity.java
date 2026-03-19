package com.example.PaginaWebRufyan.User.Entity;


import com.example.PaginaWebRufyan.Security.Roles.RoleEnum;
import com.example.PaginaWebRufyan.Security.Token;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Entity
@Table(name = "user_entity")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
    @Embedded
	private FullName fullName;
    private String stringFullName;
	@NotNull
	private LocalDate birthDate;

	@NotNull
	@Column(unique= true, nullable = false)
	private String username;
	@NotNull
	@Email
    @Column(unique = true, nullable = false)
	private String email;
    private String password;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Token> tokens;



	public UserEntity(Long id, FullName fullName, BirthDate birthDate, String username, String email, String password) {
		this.id = id;
		this.fullName = fullName;
		this.birthDate = birthDate.getBirthDate();
		this.username = username;
		this.email = email;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
        this.stringFullName = fullName.getFullName();
        this.password = password;
		this.role = RoleEnum.ROLE_CLIENT;
	}

	public UserEntity(Long id, FullName fullName, BirthDate birthDate, String username, String email, String password, RoleEnum role) {
		this.id = id;
		this.fullName = fullName;
		this.birthDate = birthDate.getBirthDate();
		this.username = username;
		this.email = email;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.stringFullName = fullName.getFullName();
		this.password = password;
		this.role = role;
	}


	public UserEntity(){
         createdAt=LocalDateTime.now();

    }


	@PreUpdate
	protected void onUpdate(){
		this.stringFullName = fullName.getFullName();
        this.updatedAt = LocalDateTime.now();
	}



/*
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "profile_picture")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private UserProfilePicture profilePicture;
*/






}
