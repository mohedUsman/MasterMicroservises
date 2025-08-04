package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.DTO.CustomerDto;
import com.eazybytes.accounts.DTO.ResponceDto;
import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
// @Validated its tells the Spring framework to validate the request body for all the methods in this controller
@Validated
public class AccountsController {


    private IAccountsService accountsService;

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


}
