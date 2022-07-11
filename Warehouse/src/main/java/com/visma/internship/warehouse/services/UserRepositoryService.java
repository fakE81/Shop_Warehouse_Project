package com.visma.internship.warehouse.services;

import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRepositoryService {

    private UserRepository userRepository;

    public UserRepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ShopUser> getUsers() {
        return userRepository.findAll();
    }

    public void saveUser(ShopUser shopUser) {
        userRepository.save(shopUser);
    }

    public Optional<ShopUser> findByName(String name){
        return userRepository.findByName(name).stream().findFirst();
    }
}
