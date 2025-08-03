package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerID(Long customerID);

    /**
     * * Method to delete an account by customer ID.
     */

    /**
     * when ever we are updating our database using custom methods we need to use @Transactional
     * and @Modifying annotations, this is because we are modifying the database
     * and @Transactional will ensure that the transaction is committed or rolled back
     */
    @Transactional
    @Modifying
    void deleteByCustomerID(Long mobileNumber);
}
