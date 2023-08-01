package com.example.securityDemo.security;

import com.example.securityDemo.security.dao.AccountRepository;
import com.example.securityDemo.security.entity.Account;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Component
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Account account = accountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        int lockingBoundaries = 3;
        LocalDate today = LocalDate.now();
        return User.withUsername(account.userName())
                .password(account.password())
                .roles("USER")
                .disabled(!account.enabled())
                .accountExpired(account.expiration().isBefore(today))
                .credentialsExpired(account.passwordExpiration().isBefore(today))
                .accountLocked(account.loginFailureCount() >= lockingBoundaries)
                .build();
    }

    // ログイン成功時に拾って処理する
    @EventListener
    public void handle(AuthenticationSuccessEvent event){
        String userName = event.getAuthentication().getName();
        accountRepository.findById(userName).ifPresent(account -> {
            accountRepository.save(account.resetLoginFailureCount());
        });
    }

    // ログイン失敗時に拾って処理する
    @EventListener
    public void handle(AuthenticationFailureBadCredentialsEvent event){
        String userName = event.getAuthentication().getName();
        accountRepository.findById(userName).ifPresent(account -> {
            accountRepository.save(account.incrementLoginFailureCount());
        });
    }
}
