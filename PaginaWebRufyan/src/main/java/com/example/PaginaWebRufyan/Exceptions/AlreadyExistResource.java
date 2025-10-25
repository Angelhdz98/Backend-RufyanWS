package com.example.PaginaWebRufyan.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyExistResource extends RuntimeException {
    public AlreadyExistResource(String message) {
        super(message);
    }
}
