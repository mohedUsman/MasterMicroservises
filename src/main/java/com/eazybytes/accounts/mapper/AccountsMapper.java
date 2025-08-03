package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.DTO.AccountsDto;
import com.eazybytes.accounts.entity.Accounts;

/**
 * Mapper class to convert between Accounts entity and AccountsDto.
 * This class provides methods to map fields from the entity to the DTO and vice versa.
 */
public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }

}