package com.example.PaginaWebRufyan.adapter.out.persistence;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserValidators;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class UserSecurityLoggingEntity {

    @Id
    private final Long id;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    @Min(6)
    private String password;
    private UserValidators userValidators;

}
