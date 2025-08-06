package com.eazybytes.accounts.DTO;

import com.eazybytes.accounts.entity.Accounts;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        description = "Accounts Data Transfer Object",
        title = "Accounts"
)
public class AccountsDto  {

    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Account number must be 10 digits")
    @Schema(
            description = "Account number of the customer",
            example = "1234567890"
    )
    private Long accountNumber;

    @NotEmpty (message = "Account type cannot be empty")
    @Schema(
            description = "Type of the account (e.g., Savings, Current)",
            example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch address cannot be empty")
    @Schema(
            description = "Address of the branch where the account is held",
            example = "123 Main St, Springfield"
    )
    private String BranchAddress;

}
