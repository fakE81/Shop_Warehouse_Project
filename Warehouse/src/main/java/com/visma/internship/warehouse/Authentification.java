package com.visma.internship.warehouse;

import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.services.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

    @Autowired
    private UserRepositoryService userRepositoryService;

    private BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

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
        return bCrypt;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(getUserDetails());
    }

    private List<UserDetails> getUserDetails(){
        List<ShopUser> shopUsers = userRepositoryService.getUsers();

        List<UserDetails> usersDetails = new ArrayList<>();
        for(ShopUser shopUser : shopUsers){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(shopUser.getRole()));
            usersDetails.add(new User(shopUser.getName(),passwordEncoder().encode(shopUser.getPassword()),authorities));
        }

        return  usersDetails;
    }
}
