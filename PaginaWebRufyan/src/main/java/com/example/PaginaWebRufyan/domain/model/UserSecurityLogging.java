package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserValidators;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class UserSecurityLogging {

        private final Long id;
        private final String email;
        private final String username;
        private final String password;

        private final LocalDateTime lastLogging;

          static final Integer minCharLength = 6;
        Boolean isValidPassword(){
            return password.length() >= minCharLength && !password.isBlank();
        }


}
