package com.enterprise.mixsale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TokenExpireException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public TokenExpireException(String token, String message) {
        super(String.format("Token Expire=> [%s]: %s", token, message));
      }
}
