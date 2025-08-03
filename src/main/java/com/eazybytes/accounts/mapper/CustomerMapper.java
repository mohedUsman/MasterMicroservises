package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.DTO.CustomerDto;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {

    private static CustomerDto mapToCustomerDto(CustomerDto customerDto, CustomerDto customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
