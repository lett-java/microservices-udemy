package com.flettieri.cursoudemy.productapi.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthenticationException extends RuntimeException{

	private static final long serialVersionUID = -7863243408781988306L;

	public AuthenticationException(String message) {
		super(message);
	}

}
