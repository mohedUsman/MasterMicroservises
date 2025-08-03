package com.eazybytes.accounts.service.Impl;

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

    /**
     * In this method I am updating the account by account ID,accId can not be changed once created
     * name, email and so on can be changed,
     * so I am updating the account details by fetching the account by ID
     * and then updating the account details with the new details from the customerDto
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;

       AccountsDto accountsDto = customerDto.getAccountsDto();
       if(accountsDto != null) {
           Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                   () -> new ResourceNotFoundException("Account", "accountNumber", accountsDto.getAccountNumber().toString())
           );
           /*
            * I am getting the value through the account number if I give the different account number
            * it will throw ResourceNotFoundException, so I am not writing the code to handle that
            */
           AccountsMapper.mapToAccounts(accountsDto, accounts);
           accountsRepository.save(accounts);

           Long customerID = accounts.getCustomerID();
           Customer customer = customerRepository.findById(customerID).orElseThrow(
                   () -> new ResourceNotFoundException("Customer", "customerID", customerID.toString())
           );
           CustomerMapper.mapToCustomer(customerDto, customer);
           customerRepository.save(customer);
           /*
            * and finally I am saving the customer details and making isUpdated true
            */
           isUpdated = true;
       }
        return isUpdated;
    }
}
