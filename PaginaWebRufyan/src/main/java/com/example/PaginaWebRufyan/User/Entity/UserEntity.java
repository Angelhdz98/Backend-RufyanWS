package com.example.PaginaWebRufyan.User.Entity;


import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode
@ToString
@Entity
@Table
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
	@Column(unique= true)
	private String username;
	@NotNull
	@Email
    @Column(unique = true, nullable = false)
	private String email;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;


	public UserEntity(Long id, FullName fullName, BirthDate birthDate, String username, String email) {
		this.id = id;
		this.fullName = fullName;
		this.birthDate = birthDate.getBirthDate();
		this.username = username;
		this.email = email;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
        this.stringFullName = fullName.getFullName();
	}

    protected UserEntity(){
         createdAt=LocalDateTime.now();
    }

	@PreUpdate
    @PrePersist
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
