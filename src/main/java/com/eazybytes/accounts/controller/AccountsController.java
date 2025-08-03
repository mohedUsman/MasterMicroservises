package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.DTO.CustomerDto;
import com.eazybytes.accounts.DTO.ResponceDto;
import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {


    private IAccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponceDto> createAccount(@RequestBody CustomerDto customerDto){
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
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        /**
         * here I am fetching the customer details by mobile number
         * and returning the response entity with status code 200
         * and I have used the AccountsConstants class to get the status code and message
         */
        CustomerDto customerDto = accountsService.featchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }



}
