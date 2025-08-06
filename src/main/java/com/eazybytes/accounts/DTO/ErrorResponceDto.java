package com.eazybytes.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        description = "Schema for error response containing details about the error that occurred during API processing.",
        title = "ErrorResponse"
)
public class ErrorResponceDto {

    @Schema(
            description = "The API endpoint that caused the error"
    )
    private String apiPath;

    @Schema(
            description = "HTTP status code representing the error type"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Detailed error message explaining the issue"
    )
    private String ErrorMessage;

    @Schema(
            description = "Timestamp when the error occurred"
    )
    private LocalDateTime errorTime;
}
