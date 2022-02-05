package com.example.notesApp.controller;

import com.example.notesApp.api.Response;
import com.example.notesApp.error.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        return new ResponseEntity<>(new Response<>(e), HttpStatus.valueOf(e.getStatusCode()));
    }
}
