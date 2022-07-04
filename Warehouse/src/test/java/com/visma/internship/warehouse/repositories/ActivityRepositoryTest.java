package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.entities.UserActivity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ActivityRepositoryTest {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Test
    void findLastHourActivities() {
        Item item = new Item();
        ShopUser shopUser = new ShopUser();

        userRepository.save(shopUser);
        itemRepository.save(item);
        activityRepository.save(new UserActivity(shopUser,item));
        activityRepository.save(new UserActivity(shopUser,item));
        activityRepository.save(new UserActivity(shopUser,item));

        Assert.assertEquals(3,activityRepository.findLastHourActivities().size());

        long id = activityRepository.findLastHourActivities().get(0).getId();
        Optional<UserActivity> userActivity = activityRepository.findById(id);
        LocalDateTime localDateTime = LocalDateTime.now();
        userActivity.get().setActivityTime(localDateTime.minusHours(2));
        activityRepository.save(userActivity.get());

        Assert.assertEquals(2,activityRepository.findLastHourActivities().size());
    }

    @Test
    void findActivityByShopUserId() {
        Item item = new Item();
        ShopUser shopUser = new ShopUser("Username","Password","Role");

        itemRepository.save(item);
        userRepository.save(shopUser);
        long id = userRepository.findByName("Username").get(0).getId();
        activityRepository.save(new UserActivity(shopUser,item));

        Assert.assertEquals(id,activityRepository.findByShopUser(id).get(0).getShopUser().getId());
    }
}