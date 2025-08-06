package com.eazybytes.accounts.DTO;

import com.eazybytes.accounts.entity.Accounts;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
 * @Schema annotation is used to provide additional information about the DTO
 * in the Swagger documentation.
 * It helps in generating better API documentation.
 */
@Schema(
        description = "Customer Data Transfer Object",
        title = "Customer"
)
@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    /*
    * I can also use @Schema annotation to provide additional information about the field
    * in the Swagger documentation.
     */
    @Schema(
        description = "Name of the customer",
        example = "John Doe"
    )
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    // Using @Email annotation to validate email format
    @Email(message = "Email should be valid")
    @Schema(
        description = "Email address of the customer",
        example = "usman@gmail.com")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    @NotEmpty(message = "Mobile number cannot be empty")
    @Schema(
        description = "Mobile number of the customer",
        example = "1234567890"
    )
    private String mobileNumber;

    private AccountsDto accountsDto;
}
