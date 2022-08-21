package com.alpha.momentum.controller;

import com.alpha.momentum.exception.UsersAlreadyExistsException;
import com.alpha.momentum.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@RestController
@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(UsersAlreadyExistsException.class)
    public final ResponseEntity<Object> usersAlreadyExistsException(
            UsersAlreadyExistsException ex, WebRequest request
    ) throws Exception {
        ExceptionResponse response =
                new ExceptionResponse(LocalDateTime.now().toString(), 400, HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ex.getLocalizedMessage(),
                        request.getDescription(false),
                        ex.toString());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
}
