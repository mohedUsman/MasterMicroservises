package com.eazybytes.accounts.exception;


import com.eazybytes.accounts.DTO.ErrorResponceDto;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * what @ControllerAdvice does is it allows us to handle exceptions globally
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * This method is used to handle MethodArgumentNotValidException
     * which is thrown when a method argument fails validation.
     * It extracts the validation errors from the exception and returns them in a map format.
     * The map contains field names as keys and their corresponding validation error messages as values.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }


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

    /**
     * This method is used to handel the RuntimeException and we are using Exception class
     * to handle all the runtime exceptions
     * this method is used to handle all the exceptions that are not handled by the above methods
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponceDto> handelGlobalException(Exception exception,WebRequest webRequest){

        ErrorResponceDto errorResponceDto = new ErrorResponceDto(
                webRequest.getDescription(true),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponceDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
