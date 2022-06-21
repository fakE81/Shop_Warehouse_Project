package com.visma.internship.warehouse;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class Authentification {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        // Sukuriam nauja User.
        List<UserDetails> users = new ArrayList<>();
        List<GrantedAuthority> adminAuthorities = new ArrayList<>();
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        adminAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        userAuthorities.add(new SimpleGrantedAuthority("TEST"));
        users.add(new User("admin",passwordEncoder().encode("admin"),adminAuthorities));
        users.add(new User("test",passwordEncoder().encode("test"),userAuthorities));


        return new InMemoryUserDetailsManager(users);
    }
}
