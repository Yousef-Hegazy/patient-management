package com.pm.patientservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        log.error(e.toString());
        return ErrorResponse
                .builder(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleException(IllegalArgumentException e) {
        return ErrorResponse
                .builder(e, HttpStatus.BAD_REQUEST, e.getMessage())
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleException(MethodArgumentNotValidException e) {
        var res = ErrorResponse
                .builder(e, HttpStatus.BAD_REQUEST, e.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
                .build();

        return res;
    }
}
