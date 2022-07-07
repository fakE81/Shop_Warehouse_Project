package com.visma.internship.warehouse.services;

import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class UserRepositoryServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserRepositoryService userRepositoryService;

    @Test
    void findAllUsers() {
        List<ShopUser> shopUserList = new ArrayList<>();
        shopUserList.add(new ShopUser(999L, "Username", "Password", "Role"));
        shopUserList.add(new ShopUser(1000L, "Username1", "Password1", "Role1"));

        Mockito.when(userRepository.findAll()).thenReturn(shopUserList);

        Assert.assertEquals(shopUserList, userRepositoryService.getUsers());
    }
}