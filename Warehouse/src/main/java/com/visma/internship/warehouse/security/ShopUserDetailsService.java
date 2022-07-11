package com.visma.internship.warehouse.security;

import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.repositories.UserRepository;
import com.visma.internship.warehouse.services.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryService userRepositoryService;

    BCryptPasswordEncoder bCrypt;

    public ShopUserDetailsService(BCryptPasswordEncoder bCrypt) {
        this.bCrypt = bCrypt;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ShopUser> shopUser = userRepositoryService.findByName(username);
        if (shopUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(shopUser.get().getRole()));
        UserDetails userDetails = new User(shopUser.get().getName(), bCrypt.encode(shopUser.get().getPassword()), authorities);
        return userDetails;
    }
}
