package com.example.PaginaWebRufyan.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InconsitentDataException extends RuntimeException{

	public InconsitentDataException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
