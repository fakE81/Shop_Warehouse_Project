package com.visma.internship.warehouse.services;

import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.repositories.ActivityRepository;
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
class ActivityRepositoryServiceTest {

    @Mock
    ActivityRepository activityRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    ActivityRepositoryService activityRepositoryService;


    @Test
    void findAllActivitiesByUserId() {
        Item item = new Item(3,".",".",15,20);
        ShopUser shopUser1 = new ShopUser(999L,"Username","Password","Role");
        ShopUser shopUser2 = new ShopUser(1000L,"Username","Password","Role");

        List<UserActivity> userActivityList1 = new ArrayList<>();
        List<UserActivity> userActivityList2 = new ArrayList<>();
        userActivityList1.add(new UserActivity(shopUser1,item));
        userActivityList2.add(new UserActivity(shopUser2,item));
        userActivityList2.add(new UserActivity(shopUser2,item));

        Mockito.when(activityRepository.findByShopUser(999L)).thenReturn(userActivityList1);
        Mockito.when(activityRepository.findByShopUser(1000L)).thenReturn(userActivityList2);

        Assert.assertEquals(1,activityRepositoryService.findAllActivitiesByUserId(999L).size());
        Assert.assertEquals(2,activityRepositoryService.findAllActivitiesByUserId(1000L).size());
    }

    @Test
    void findAllActivities() {
        Item item = new Item(3,".",".",15,20);
        ShopUser shopUser1 = new ShopUser(999L,"Username","Password","Role");
        ShopUser shopUser2 = new ShopUser(1000L,"Username","Password","Role");

        List<UserActivity> userActivityList = new ArrayList<>();
        userActivityList.add(new UserActivity(shopUser1,item));
        userActivityList.add(new UserActivity(shopUser1,item));
        userActivityList.add(new UserActivity(shopUser2,item));

        Mockito.when(activityRepository.findAll()).thenReturn(userActivityList);

        Assert.assertEquals(3, activityRepositoryService.findAllActivities().size());
    }

    @Test
    void findLastHourActivities() {
        Item item = new Item(3,".",".",15,20);
        ShopUser shopUser1 = new ShopUser(999L,"Username","Password","Role");
        List<UserActivity> userActivityList = new ArrayList<>();
        userActivityList.add(new UserActivity(shopUser1,item));

        Mockito.when(activityRepository.findLastHourActivities()).thenReturn(userActivityList);

        Assert.assertEquals(1,activityRepositoryService.findLastHourActivities().size());
    }
}