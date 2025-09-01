package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.Security.UserIdentificable;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Embeddable
@Getter
@AllArgsConstructor
public class LoggingData {
    private UserIdentificable userIdentificable;
    private String password;

}
