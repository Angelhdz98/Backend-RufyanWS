package com.example.PaginaWebRufyan.User.Entity;


import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@Builder
@EqualsAndHashCode()
@ToString
@Entity
@Table
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private FullName fullName;
	@NotNull
	private BirthDate birthDate;

	@NotNull
	@Column(unique= true)
	private String username;
	@NotNull
	@Email
	private String email;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;


	public UserEntity(Long id, FullName fullName, BirthDate birthDate, String username, String email) {
		this.id = id;
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.username = username;
		this.email = email;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate(){
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
