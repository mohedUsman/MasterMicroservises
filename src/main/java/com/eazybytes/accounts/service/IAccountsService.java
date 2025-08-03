package com.eazybytes.accounts.service;

import com.eazybytes.accounts.DTO.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto featchAccount(String mobileNumber);

    /**
     * boolean method to update the account details, this method will tell weather the update was successful or not
     */
    boolean updateAccount(CustomerDto customerDto);
}
