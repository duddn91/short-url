package com.byw.shorturl.controller;

import com.byw.shorturl.exception.InvalidUrlException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<String> handleInvalidUrlException(InvalidUrlException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
