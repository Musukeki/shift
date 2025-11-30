package com.example.p01.config; // 這裡的 package 要對應到你建立的資料夾

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) // 關掉 CSRF (POST JSON 不會被擋)
	            .authorizeHttpRequests(auth -> auth
	                .anyRequest().permitAll() // 所有請求都不驗證
	            );
	        return http.build();
	    }
	
	
	
}

