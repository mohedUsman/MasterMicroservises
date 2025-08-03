package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.entity.Customer;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Find a customer by their mobile number.
     * why I need this method?
     * this method is useful of I want create an account for a customer
     * and I want to check if the customer already exists in the database with the given mobile number.
     * * this method is called as Derived Query Method here we are not writing any query for  findByMobileNumber
     * Spring Data JPA will automatically generate the query based on the method name.
     *
     */
    Optional<Customer> findByMobileNumber(String mobileNumber);

}
