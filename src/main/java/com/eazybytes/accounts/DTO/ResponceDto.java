package com.eazybytes.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ResponceDto is a Data Transfer Object (DTO) that encapsulates the response
 * data for various operations in the application.
 * It contains fields for status code, status message, and a dummy message.
 */
@Data @AllArgsConstructor
@Schema(
        description = "Response to hold the status code and message for various operations",
        title = "Response"
)
public class ResponceDto {

    @Schema(
            description = "Status code indicating the result of the operation",
            example = "200",
            allowableValues = {"200", "201", "400", "404", "500"},
            type = "string"
    )
    private String statusCode;

    @Schema(
            description = "Message providing additional information about the operation status",
            example = "Account created successfully",
            type = "string"
    )
    private String statusMsg;

    //private String dummyMsg;
}
