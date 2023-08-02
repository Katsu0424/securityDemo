package com.example.securityDemo.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Configurationクラスであることを明示
// 従来は@EnableWebSecurityに含まれていたが、削除されたので記載が必要
@Configuration
// Spring Securityの設定を有効にしてカスタム可能にする
@EnableWebSecurity
// 従来:@EnableGlobalMethodSecurit
// 導入:@EnableMethodSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
// Spring Security 5.3以前ではWebSecurityConfigurerAdapter
// を継承したクラスでSecurityFilterChainを定義していたが、Beanとして定義可能に

    @Bean
    PasswordEncoder passwordEncoder() {
        Pbkdf2PasswordEncoder passwordEncoder =
                Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        passwordEncoder.setEncodeHashAsBase64(true);
        return passwordEncoder;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(formLogin -> formLogin
                        .loginProcessingUrl("/login")
                        .loginPage("/login")
                        .defaultSuccessUrl("/logout")
                        .failureUrl("/login?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login"))
                .authorizeHttpRequests(authorize -> authorize
                        // 静的リソースへのアクセスを全て許可
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
