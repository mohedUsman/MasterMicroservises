package com.eazybytes.accounts.service;

import com.eazybytes.accounts.DTO.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto featchAccount(String mobileNumber);
}
