package com.example.PaginaWebRufyan.Exceptions;

public class WrongUserDataException extends RuntimeException {

	public WrongUserDataException (String message) {
		super(message);
	}
}
