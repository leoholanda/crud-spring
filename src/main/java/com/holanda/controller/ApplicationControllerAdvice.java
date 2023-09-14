package com.holanda.controller;

import com.holanda.exception.RecordDuplicateException;
import com.holanda.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RecordDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateException(RecordDuplicateException ex) {
        return ex.getMessage();
    }
}
