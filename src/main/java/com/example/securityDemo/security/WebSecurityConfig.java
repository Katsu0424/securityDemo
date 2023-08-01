package com.example.securityDemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig {

    // エンコード設定
    @Bean
    PasswordEncoder passwordEncoder(){
        Pbkdf2PasswordEncoder passwordEncoder =
                Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        passwordEncoder.setEncodeHashAsBase64(true);
        return passwordEncoder;
    }

    // ログイン、ログアウト画面を作成したHTMLに差し替える
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/logout");

        return http
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/login").permitAll()
                    .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/login").successHandler(successHandler))
                .build();
    }
}
