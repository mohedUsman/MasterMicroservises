package com.eazybytes.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    /**
     * why I am passing resourceName, fieldName and fieldValue?
     * because I want to provide more information about the resource that was not found.
     * For example, if a customer with a specific mobile number is not found,
     * I can provide the resource name as "Customer" or "Account", the field name as "mobileNumber",
     */
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(
                /**
                 * why I am using String.format?
                 * because it allows me to create a formatted string
                 * with placeholders for the resource name, field name, and field value.
                 */
                String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue)
        );
    }
}
