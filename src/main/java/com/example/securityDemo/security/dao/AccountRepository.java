package com.example.securityDemo.security.dao;

import com.example.securityDemo.security.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    private final DemoMapper demoMapper;

    public AccountRepository(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }

    public Optional<Account> findById(String username){
        List<Account> accountsList = demoMapper.findAll(username);
        return !accountsList.isEmpty() ? Optional.of(accountsList.get(0)) : Optional.empty();
    }

    public void save(Account account){
        demoMapper.updateAccount(account);
    }
}
