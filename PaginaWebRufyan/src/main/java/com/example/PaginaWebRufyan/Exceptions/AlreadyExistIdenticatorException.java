package com.example.PaginaWebRufyan.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyExistIdenticatorException extends RuntimeException{
 
	public AlreadyExistIdenticatorException(String message) {
		super(message);
	}
}
