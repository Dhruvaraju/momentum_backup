package com.alpha.momentum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsersAlreadyExistsException extends RuntimeException {
    public UsersAlreadyExistsException(String message) {
        super(message);
    }
}
