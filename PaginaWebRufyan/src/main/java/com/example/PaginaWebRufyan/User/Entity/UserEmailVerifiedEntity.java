package com.example.PaginaWebRufyan.User.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Table(name= "is_user_mail_verified")
@Entity
public class UserEmailVerifiedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final Long userId;
    @Setter
    private Boolean isEmailVerified;

    public UserEmailVerifiedEntity(){
        this.userId=0L;
    }
    public UserEmailVerifiedEntity(Long id, Long userId, Boolean isEmailVerified) {
        this.id = id;
        this.userId = userId;
        this.isEmailVerified = isEmailVerified;
    }

    public UserEmailVerifiedEntity(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
        this.isEmailVerified = false;
    }

}
