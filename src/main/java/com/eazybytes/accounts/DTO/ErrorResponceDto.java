package com.eazybytes.accounts.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 *  this call is used to send error response to the client
 *  when an exception occurs in the application.
 *  It contains the API path, error code, error message,
 */
@Data @AllArgsConstructor
public class ErrorResponceDto {

    private String apiPath;

    private HttpStatus errorCode;

    private String ErrorMessage;

    private LocalDateTime errorTime;
}
