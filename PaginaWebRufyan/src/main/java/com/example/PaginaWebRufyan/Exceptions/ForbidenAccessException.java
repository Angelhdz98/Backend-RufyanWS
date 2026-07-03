package com.example.PaginaWebRufyan.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ForbidenAccessException extends RuntimeException {
    public ForbidenAccessException(String message) {
        super(message);
    }
}
