package com.eazybytes.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * If iwant to create a custom exception, I can extend the RuntimeException class.
 *
 * when this exception is thrown, it will return a 400 Bad Request status code
 * to the client.
 * This is done by annotating the class with @ResponseStatus and specifying the HttpStatus.BAD_REQUEST value.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException{

    /**
     * we are writing a constructor that accepts a message
     * as we are extending the RuntimeException class.
     * This message will be passed to the super class constructor
     */
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}

