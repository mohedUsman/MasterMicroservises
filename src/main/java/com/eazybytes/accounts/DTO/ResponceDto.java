package com.eazybytes.accounts.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ResponceDto is a Data Transfer Object (DTO) that encapsulates the response
 * data for various operations in the application.
 * It contains fields for status code, status message, and a dummy message.
 */
@Data @AllArgsConstructor
public class ResponceDto {

    private String statusCode;

    private String statusMsg;

    //private String dummyMsg;
}
