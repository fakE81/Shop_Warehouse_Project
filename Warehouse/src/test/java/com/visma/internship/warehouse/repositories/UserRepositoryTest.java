package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.ShopUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByName() {
        ShopUser shopUser = new ShopUser("Username","Password","Role");

        userRepository.save(shopUser);
        Assert.assertEquals("Username",userRepository.findByName("Username").get(0).getName());
    }
}