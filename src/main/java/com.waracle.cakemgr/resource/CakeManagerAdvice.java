package com.waracle.cakemgr.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CakeManagerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<String> handleAllExceptions(IllegalArgumentException ex, WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
