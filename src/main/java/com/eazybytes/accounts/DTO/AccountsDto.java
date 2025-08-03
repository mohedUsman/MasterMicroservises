package com.eazybytes.accounts.DTO;

import com.eazybytes.accounts.entity.Accounts;
import lombok.Data;

@Data
public class AccountsDto  {

    private Long accountNumber;
    private String accountType;
    private String BranchAddress;

}
