package com.eazybytes.accounts.exception;


import com.eazybytes.accounts.DTO.ErrorResponceDto;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * what @ControllerAdvice does is it allows us to handle exceptions globally
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * why I am using WebReques? because we are trying to pass the api path in our error response
     * so that the client can know which API has caused the error.
     * ***
     * * @ExceptionHandler is used to handle specific exceptions for this method.
     * * In this case, it is handling CustomerAlreadyExistsException.
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponceDto> handelCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,WebRequest webRequest){
        /**
         * here I am creating an instance of ErrorResponceDto
         * and passing the api path, error code, error message and error time
         * to the constructor of ErrorResponceDto.
         * and returning the response entity with status code 400
         */
        ErrorResponceDto errorResponceDto = new ErrorResponceDto(
                webRequest.getDescription(true),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponceDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponceDto> handelCustomerAlreadyExistsException(ResourceNotFoundException exception,WebRequest webRequest){

        ErrorResponceDto errorResponceDto = new ErrorResponceDto(
                webRequest.getDescription(true),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponceDto, HttpStatus.BAD_REQUEST);
    }


}
