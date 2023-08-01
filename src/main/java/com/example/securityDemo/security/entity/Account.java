package com.example.securityDemo.security.entity;

import java.time.LocalDate;

public record Account(String userName, String password, boolean enabled, LocalDate expiration, LocalDate passwordExpiration, Integer loginFailureCount) {

    public Account resetLoginFailureCount(){
        return new Account(userName, password, enabled, expiration, passwordExpiration, 0);
    }


    public Account incrementLoginFailureCount(){
        return new Account(userName, password, enabled, expiration, passwordExpiration, loginFailureCount + 1);
    }

}
