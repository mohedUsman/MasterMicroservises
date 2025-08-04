package com.eazybytes.accounts.DTO;

import com.eazybytes.accounts.entity.Accounts;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    // Using @Email annotation to validate email format
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    @NotEmpty(message = "Mobile number cannot be empty")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
