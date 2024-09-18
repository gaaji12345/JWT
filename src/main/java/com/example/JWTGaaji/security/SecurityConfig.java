package com.example.JWTGaaji.security;/*  gaajiCode
    99
    18/09/2024
    */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.GET).permitAll() // Allow registration and login
                .anyRequest().authenticated() // Require authentication for any other request
                .and()
                . httpBasic();// Optional: if you want to use form-based login

        return http.build();
    }


    @Bean
    public UserDetailsService users(){
        UserDetails admin= User.builder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        UserDetails user= User.builder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return  new InMemoryUserDetailsManager(admin,user);
    }

}
