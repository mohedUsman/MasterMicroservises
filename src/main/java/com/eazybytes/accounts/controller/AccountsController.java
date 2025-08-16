package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.DTO.AccountContactInfoDto;
import com.eazybytes.accounts.DTO.CustomerDto;
import com.eazybytes.accounts.DTO.ResponceDto;
import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/*
 * use of @Tag annotation is to group the controller in the swagger documentation
 */
@Tag(
        name = "Accounts Service",
        description = "This controller handles all the operations related to customer accounts, including creation, fetching, updating, and deletion."
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})

// @Validated its tells the Spring framework to validate the request body for all the methods in this controller
@Validated
public class AccountsController {

    //this is for constructor-based dependency injection
    private final IAccountsService accountsService;


    // Constructor injection is used to inject the IAccountsService dependency into the AccountsController
    public AccountsController(IAccountsService accountsService) {
        this.accountsService = accountsService;
    }

    /*
     * this @Value annotation is used to inject the value of the build version from the application.properties file
     * into the buildName variable
     */
    @Value("${build.version}")
    private String buildName;

    /*
     * this Environment is used to access the environment properties it can't be used in side the application.properties file
     * as it exposes the sencitive information
     */
    @Autowired
    private Environment environment;

    //creating a AccountContactInfoDto object to access the contact information from the application.properties file
    @Autowired
    private AccountContactInfoDto accountContactInfoDto;

    /*
     * this @Operation annotation is used to provide additional information about the API endpoint
     */
    @Operation(
            summary = "Create a new customer account",
            description = "This endpoint allows you to create a new customer account by providing the necessary details in the request body."
    )
    /*
        * this @ApiResponse annotation is used to document the response of the API endpoint
     */
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponceDto> createAccount(@Valid  @RequestBody CustomerDto customerDto){
        /**
         * here I created a ResponceDTO in that I have aadded the status code, message and dummy message
         * and returning the response entity with status code 201
         * and I have used the AccountsConstants class to get the status code and message
         * and dummy message.
         */
        accountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponceDto(AccountsConstants.STATUS_201,
                        AccountsConstants.MESSAGE_201));


    }

    @Operation(
            summary = "Fetch customer account details",
            description = "This endpoint retrieves the account details of a customer using their mobile number."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account details fetched successfully"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "^\\d{10}$",
                                                                   message = "Mobile number must be 10 digits")
                                                               String mobileNumber) {
        /**
         * here I am fetching the customer details by mobile number
         * and returning the response entity with status code 200
         * and I have used the AccountsConstants class to get the status code and message
         */
        CustomerDto customerDto = accountsService.featchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update customer account details",
            description = "This endpoint allows you to update the account details of a customer."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account details updated successfully"
    )
    @ApiResponse(
            responseCode = "417",
            description = "Failed to delete account, account not found"
    )
    @PutMapping("/update")
    public ResponseEntity<ResponceDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        /*
         * here if isUpdated is true then I am returning the response entity with status code 200
         * and message 200, otherwise I am returning the response entity with status code 417
         */
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponceDto(AccountsConstants.STATUS_200
                            ,AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponceDto(AccountsConstants.STATUS_417,
                            AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete customer account",
            description = "This endpoint allows you to delete a customer account using their mobile number."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account deleted successfully"
    )
    @ApiResponse(
            responseCode = "417",
            description = "Failed to delete account, account not found"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponceDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "^\\d{10}$",
                                                                 message = "Mobile number must be 10 digits")
                                                         String mobileNumber){
        boolean isDeleted =accountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponceDto(AccountsConstants.STATUS_200,
                            AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR   )
                    .body(new ResponceDto(AccountsConstants.STATUS_417,
                            AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Create a new api to get the build info",
            description = "This endpoint allows you to create a new customer account by providing the necessary details in the request body."
    )
    /*
     * this @ApiResponse annotation is used to document the response of the API endpoint
     */
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error occurred while fetching build info"
    )
    @GetMapping("/builfInfo")
    public ResponseEntity<String> getBuilfInfo(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(buildName);
    }

    @Operation(
            summary = "Get Java Version",
            description = "This endpoint retrieves the Java version used by the application."
    )
    /*
     * this @ApiResponse annotation is used to document the response of the API endpoint
     */
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error occurred while fetching build info"
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getjavaVersion(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Contact Info",
            description = "In case of any issue You can contact to this details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Error occurred while fetching build info"
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDto> getContactInfo(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountContactInfoDto);
    }


}
