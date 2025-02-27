package com.onlinebanking.impl;

import com.onlinebanking.api.AccountsApi;
import com.onlinebanking.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountsApiImpl implements AccountsApi {
    private static final Map<String, Account> accounts = new HashMap<>();

    static {
        // Dummy Account Data using Setters
        Account account1 = new Account();
        account1.setId(1);
        account1.setUserId(101);
        account1.setAccountNumber("ACC123456");
        account1.setBalance(new BigDecimal("5000.00"));
        account1.setCreatedAt(OffsetDateTime.now());
        accounts.put(account1.getAccountNumber(), account1);

        Account account2 = new Account();
        account2.setId(2);
        account2.setUserId(102);
        account2.setAccountNumber("ACC789012");
        account2.setBalance(new BigDecimal("7500.50"));
        account2.setCreatedAt(OffsetDateTime.now());
        accounts.put(account2.getAccountNumber(), account2);

        Account account3 = new Account();
        account3.setId(3);
        account3.setUserId(103);
        account3.setAccountNumber("ACC345678");
        account3.setBalance(new BigDecimal("3000.00"));
        account3.setCreatedAt(OffsetDateTime.now());
        accounts.put(account3.getAccountNumber(), account3);
    }


    @Override
    public ResponseEntity<Account> accountsIdGet(String accountId) {
        Account account = accounts.get(accountId);
        return (account != null) ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> accountsIdPut(String accountId, Account account) {
        accounts.put(accountId, account);
        return ResponseEntity.ok().build();
    }
}
