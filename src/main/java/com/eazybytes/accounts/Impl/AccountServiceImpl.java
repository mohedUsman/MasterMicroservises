package com.eazybytes.accounts.Impl;

import com.eazybytes.accounts.DTO.AccountsDto;
import com.eazybytes.accounts.DTO.CustomerDto;
import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    /**
     * why I am using @AllArgsConstructor here?
     * Because I want to inject the dependencies of AccountsRepository and CustomerRepository
     * using constructor injection.
     * we can also use @Autowired annotation on the constructor as it is a single const
     */
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    /**
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        // Convert CustomerDto to Customer entity
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("System");
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number: "
                    + customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount (Customer customer) {
        /**
         * this method is called inside the createAccount method
         * and it creates a new account for the customer
         * new account is created with a random account number with customer ID we
         * by this we are essentially linking the account to the customer
         */
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerID(customer.getCustomerID());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        newAccounts.setAccountNumber(randomAccountNumber);


        newAccounts.setAccountType(AccountsConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
        newAccounts.setCreatedAt(LocalDateTime.now());
        newAccounts.setCreatedBy("System");
        return newAccounts;
    }

    @Override
    public CustomerDto featchAccount(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        /**
         * Through  that mobile number I am fetching Account details also, but I have only mobile number
         * * so I am using the customer object to fetch the account details
         */
        Accounts accounts =accountsRepository.findByCustomerID(customer.getCustomerID())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerID", customer.getCustomerID().toString())
                );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }
}
