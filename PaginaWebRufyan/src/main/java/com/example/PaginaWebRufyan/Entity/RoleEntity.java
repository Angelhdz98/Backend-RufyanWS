package com.example.PaginaWebRufyan.Entity;

import java.util.HashSet;
import java.util.Set;

import com.example.PaginaWebRufyan.Utils.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "role_name")
	@Enumerated(EnumType.STRING)
	private RoleEnum roleEnum;
	
	@ManyToMany(fetch= FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name= "role_id"), inverseJoinColumns = @JoinColumn(name="permission_id"))
	@JsonIgnore
	Set<PermissionEntity> permissionList = new HashSet<>();

	
	

}