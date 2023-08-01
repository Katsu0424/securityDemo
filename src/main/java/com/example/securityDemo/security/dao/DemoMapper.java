package com.example.securityDemo.security.dao;

import com.example.securityDemo.security.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoMapper {
    List<Account> findAll(String username);

    void updateAccount(Account account);
}
