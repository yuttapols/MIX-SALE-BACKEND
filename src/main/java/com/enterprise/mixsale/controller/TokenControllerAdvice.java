package com.enterprise.mixsale.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.enterprise.mixsale.dto.message.ErrorMessage;
import com.enterprise.mixsale.exception.TokenRefreshException;

@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
        return new ErrorMessage(
            HttpStatus.FORBIDDEN.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
    }
}
